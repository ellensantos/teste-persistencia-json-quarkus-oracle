package br.com.hub.cnj.rest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.hub.cnj.persistence.dao.ComunicacaoDAO;
import br.com.hub.cnj.persistence.dao.ComunicacaoExternaDAO;
import br.com.hub.cnj.persistence.dto.ComunicacaoCompletaDTO;
import br.com.hub.cnj.persistence.dto.ComunicacaoDTO;
import br.com.hub.cnj.persistence.dto.ComunicacaoExternaDTO;
import br.com.hub.cnj.persistence.models.Comunicacao;
import br.com.hub.cnj.persistence.models.ComunicacaoPK;

@Path("/comunicacao")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ComunicacaoResource {

    @Inject
    ComunicacaoExternaDAO comunicacaoExternaDAO;

    @Inject
    ComunicacaoDAO comunicacaoDAO;

    @POST
    @Path("/listar")
    @APIResponse(responseCode = "200", description = "Lista de Comunicações", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ComunicacaoDTO.class, type = SchemaType.ARRAY)) })
    public Response listar() throws JsonMappingException, JsonProcessingException {

        List<ComunicacaoDTO> listaComunicacao = comunicacaoDAO.listar();
        List<ComunicacaoCompletaDTO> listaComunicacaoCompletaDTO = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        //FIX - Convertendo cada atributo da String Json para Objeto Node para deserializar.
        //Serializado, a resposta vai em formato string e escapa contra barra no arquivo JSON.
        for (ComunicacaoDTO comunicacaoDTO : listaComunicacao) {
            // System.out.println(comunicacaoDTO.getComunicacaoJson());
            ComunicacaoCompletaDTO comunicacaoCompletaDTO = new ComunicacaoCompletaDTO();
            comunicacaoCompletaDTO.setId(comunicacaoDTO.getId());
            comunicacaoCompletaDTO.setIdComunicacao(comunicacaoDTO.getIdComunicacao());
            comunicacaoCompletaDTO.setNumeroProcesso(comunicacaoDTO.getNumeroProcesso());
            comunicacaoCompletaDTO.setTimestampInclusao(comunicacaoDTO.getTimestampInclusao());

            ObjectNode obj = mapper.createObjectNode();
            JsonNode dados = mapper.readTree(comunicacaoDTO.getComunicacaoJson());
            Iterator<Map.Entry<String, JsonNode>> fields = dados.fields();

            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                String fieldName = field.getKey();
                JsonNode fieldValue = field.getValue();
                // FIX - System.out.println("LOGGG:: " + fieldName);
                if (fieldValue.isArray() || fieldValue.isObject()) {
                    obj.putPOJO(fieldName, fieldValue);
                    //FIX - obj.putArray(fieldName).add(fieldValue); //FUNCIONA mas coloca [] a mais.
                } else if (fieldValue.isTextual()) {
                    obj.put(fieldName, fieldValue.asText());
                } else if (fieldValue.isInt()) {
                    obj.put(fieldName, fieldValue.asInt());
                } else if (fieldValue.isBoolean()) {
                    obj.put(fieldName, fieldValue.asBoolean());
                } else if (fieldValue.isDouble()) {
                    obj.put(fieldName, fieldValue.asDouble());
                }
            }

            comunicacaoCompletaDTO.setComunicacaoJson(obj);
            listaComunicacaoCompletaDTO.add(comunicacaoCompletaDTO);
        }

        //return Response.ok(listaComunicacao).build();
        return Response.ok(listaComunicacaoCompletaDTO).build();
    }

    @POST
    @Path("/inserir")
    @APIResponse(responseCode = "200", description = "Inserir Comunicação", content = {
            @Content(mediaType = "application/json") })
    public Response inserir() throws JsonProcessingException {

        ComunicacaoExternaDTO comunicacaoExterna = comunicacaoExternaDAO.listar(BigInteger.valueOf(21L));

        ObjectMapper mapper = new ObjectMapper();
        // Pegando JSON NODE da String
        JsonNode jsonCompleto = mapper.readTree(comunicacaoExterna.getComunicacao());
        JsonNode comunicacaoJson = jsonCompleto.get("data");
        List<Comunicacao> listaComunicacao = new ArrayList<>();

        for (JsonNode obj : comunicacaoJson) {
            Comunicacao comunicacao = new Comunicacao();
            ComunicacaoPK comunicacaoPK = new ComunicacaoPK();
            comunicacaoPK.setIdComunicacao(obj.get("numeroComunicacao").asText());
            comunicacao.setComunicacaoPK(comunicacaoPK);
            comunicacao.setTimestampInclusao(new Date());
            comunicacao.setNumeroProcesso(obj.get("numeroProcesso").asText());
            comunicacao.setComunicacaoJson(mapper.writeValueAsString(obj));
            listaComunicacao.add(comunicacao);

        }

        comunicacaoDAO.inserir(listaComunicacao);

        return Response.ok(new StringBuilder("Comunicação inserida com sucesso.")).build();
    }

}
