package br.com.hub.cnj.rest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.hub.cnj.persistence.dao.PessoaDAO;
import br.com.hub.cnj.persistence.dto.AmigosDTO;
import br.com.hub.cnj.persistence.dto.PessoaCompletaDTO;
import br.com.hub.cnj.persistence.dto.PessoaDTO;
import br.com.hub.cnj.persistence.dto.RequisicaoDTO;
import br.com.hub.cnj.persistence.dto.RequisicaoUpdateDTO;
import br.com.hub.cnj.persistence.models.Pessoa;

@Path("/pessoa")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class PessoaResource {

        @Inject
        PessoaDAO pessoaDAO;

        @POST
        @Path("/listarPessoas")
        @APIResponse(responseCode = "200", description = "Lista de Pessoas", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = PessoaDTO.class, type = SchemaType.ARRAY)) })
        public Response getPessoas() throws JsonMappingException, JsonProcessingException {

                List<PessoaDTO> listaPessoa = pessoaDAO.listar();
                List<PessoaCompletaDTO> listaPessoaCompleta = new ArrayList<>();
                ObjectMapper objectMapper = new ObjectMapper();

                //Convertendo Amigo (String - JSON) para objeto do tipo JAVA correto(AmigoDTO).
                for (PessoaDTO pessoaDTO : listaPessoa) {
                        PessoaCompletaDTO pessoaCompletaDTO = new PessoaCompletaDTO();
                        pessoaCompletaDTO.setId(pessoaDTO.getId());
                        pessoaCompletaDTO.setNome(pessoaDTO.getNome());
                        List<AmigosDTO> listaAmigos = objectMapper.readValue(pessoaDTO.getAmigos(),
                                        new TypeReference<List<AmigosDTO>>() {
                                        });
                        pessoaCompletaDTO.setAmigos(listaAmigos);
                        listaPessoaCompleta.add(pessoaCompletaDTO);
                }

                return Response.ok(listaPessoaCompleta).build();
        }

        @POST
        @Path("/inserir")
        @APIResponse(responseCode = "200", description = "Inserir Pessoa", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = PessoaDTO.class, type = SchemaType.ARRAY)) })
        public Response inserir(final RequisicaoDTO requisicao) throws JsonProcessingException {
                Pessoa pessoa = new Pessoa();
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonAmigos = objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(requisicao.getAmigos());
                pessoa.setNome(requisicao.getNome());
                pessoa.setAmigos(jsonAmigos);
                pessoaDAO.inserir(pessoa);
                return Response.ok(new StringBuilder("Pessoa inserida com sucesso.")).build();
        }

        @PUT
        @Path("/atualizar")
        @APIResponse(responseCode = "200", description = "Atualizar Pessoa", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = PessoaDTO.class, type = SchemaType.ARRAY)) })
        public Response atualizar(final RequisicaoUpdateDTO requisicao) throws JsonProcessingException {
                Pessoa pessoa = new Pessoa();
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonAmigos = objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(requisicao.getAmigos());
                pessoa.setNome(requisicao.getNome());
                pessoa.setAmigos(jsonAmigos);
                pessoa.setId(requisicao.getId());
                pessoaDAO.atualizar(pessoa);
                return Response.ok(
                                new StringBuilder("Pessoa {id = " + requisicao.getId() + "} atualizada com sucesso."))
                                .build();
        }

        @GET
        @Path("/pesquisar/{id}")
        @APIResponse(responseCode = "200", description = "Buscar Pessoa", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class, type = SchemaType.ARRAY)) })
        public Response pesquisar(final @PathParam("id") BigInteger id)
                        throws JsonMappingException, JsonProcessingException {
                Pessoa pessoa = pessoaDAO.pesquisar(id);

                /*
                 * //CONVERTENDO JSON PARA OBJETO
                 * ObjectMapper objectMapper = new ObjectMapper();
                 * List<AmigosDTO> listaAmigos = objectMapper.readValue(pessoa.getAmigos(), new
                 * TypeReference<List<AmigosDTO>>(){});
                 * 
                 * for (AmigosDTO amigosDTO : listaAmigos) {
                 * System.out.println(amigosDTO.getNome());
                 * System.out.println(amigosDTO.getIdade());
                 * }
                 */

                // PERCORRENDO ATRIBUTOS DO JSON
                /*
                 * ObjectMapper mapper = new ObjectMapper();
                 * JsonNode dados = mapper.readTree(pessoa.getAmigos());
                 * 
                 * for (JsonNode value : dados) {
                 * System.out.println(value.get("idade").asText());
                 * }
                 */

                // PERCORRENDO OBJETOS DO JSON
                /*
                 * ObjectMapper mapper = new ObjectMapper();
                 * JsonNode dados = mapper.readTree(pessoa.getAmigos());
                 * System.out.println(dados.size());
                 * for (JsonNode value : dados) {
                 * System.out.println(value);
                 * }
                 */

                return Response.ok(pessoa).build();
        }

        @DELETE
        @Path("/excluir/{id}")
        @APIResponse(responseCode = "200", description = "Remover Pessoa", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class, type = SchemaType.ARRAY)) })
        public Response remover(final @PathParam("id") BigInteger id) {
                pessoaDAO.excluir(id);
                return Response.ok(new StringBuilder("Pessoa {id = " + id + "} excluída com sucesso.")).build();
        }

}
