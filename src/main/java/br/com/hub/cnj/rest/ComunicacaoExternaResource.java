package br.com.hub.cnj.rest;

import java.math.BigInteger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.hub.cnj.persistence.dao.ComunicacaoExternaDAO;
import br.com.hub.cnj.persistence.dto.ComunicacaoExternaDTO;
import br.com.hub.cnj.persistence.models.Pessoa;

@Path("/comunicacao/externa")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ComunicacaoExternaResource {

    @Inject
    ComunicacaoExternaDAO comunicacaoExternaDAO;

    @GET
    @Path("/pesquisar/{id}")
    @APIResponse(responseCode = "200", description = "Buscar Comunicação Externa", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class, type = SchemaType.ARRAY)) })
    public Response pesquisar(final @PathParam("id") BigInteger id)
            throws JsonMappingException, JsonProcessingException {

        ComunicacaoExternaDTO comunicacaoExterna = comunicacaoExternaDAO.listar(id);

        // PERCORRENDO OBJETOS DO JSON
        //id
        //id Comunicacao
        //timestamp
        //comunicacao (JSON - Objeto)
        //numeroProcesso

        ObjectMapper mapper = new ObjectMapper();

        //Pegando JSON NODE da String
        JsonNode dados = mapper.readTree(comunicacaoExterna.getComunicacao());

        //Quantidade de objetos da String
        System.out.println(dados.size());

        //Objeto do atributo page do JSON
        System.out.println(dados.get("page"));

        //Objeto do atributo data do JSON
        //System.out.println(dados.get("data"));

        //Pegando JSON NODE do objeto do atributo data do JSON
        JsonNode comunicacao = dados.get("data");
        
        //Quantidade de objetos do atributo data do JSON
        System.out.println(comunicacao.size());

        //Exibindo objetos da lista do atributo data do JSON
        for (JsonNode value : comunicacao) {
             System.out.println(value);
        }

        return Response.ok(comunicacaoExterna).build();
    }

}
