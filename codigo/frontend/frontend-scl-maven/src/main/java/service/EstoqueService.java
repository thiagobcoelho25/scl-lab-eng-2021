package service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import exception.StandardError;
import model.Estoque;

public class EstoqueService {
	private final String url = "http://localhost:8080/scl/estoques";
	
	private final Client client = ClientBuilder.newClient();
	
	
	public List<Estoque> listAll() {
		try {
            WebTarget target = client.target(url)	;
            String json = target.request().get(String.class);
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Estoque>> mapType = new TypeReference<List<Estoque>>() {
            };
            List<Estoque> lista = mapper.readValue(json, mapType);
            return lista;
        } catch (IOException ex) {
            Logger.getLogger(EstoqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
		return null;
	}
	
	public Estoque find(Integer id) {
		try {
            WebTarget target = client.target(url + "/" + id)	;
            String json = target.request().get(String.class);
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<Estoque> mapType = new TypeReference<Estoque>() {
            };
            return mapper.readValue(json, mapType);
        } catch (IOException ex) {
            Logger.getLogger(EstoqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
		return null;
	}
	
	public String diminuirQtdEstoque(Integer quantidade, Integer ingredienteID) {
		try {
			System.out.println("QTd = " + quantidade + " ID = " + ingredienteID);
            WebTarget target = client.target(url + "/diminuirQtdEstoque/" + quantidade + "/" + ingredienteID);
            ObjectMapper mapper = new ObjectMapper();
            Response response = target.request().get();
            if (response.getStatus() != Response.Status.NO_CONTENT.getStatusCode()) {
                String stringError = response.readEntity(String.class);
                StandardError standardError = mapper.readValue(stringError, StandardError.class);
                return construçãoStandardError(standardError);
            }
        } catch (IOException ex) {
            Logger.getLogger(EstoqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
		return null;
	}
	
	public String construçãoStandardError(StandardError standardError) {
		return standardError.getMessage() == null ? standardError.getTimestamp() + "\n"
				+ standardError.getStatus() + "\n"
				+ standardError.getError() + "\n"
				+ standardError.getCampos() + "\n" : standardError.getTimestamp() + "\n"
				+ standardError.getMessage() + "\n"
				+ standardError.getPath() + "\n"
				+ standardError.getStatus() + "\n"
				+ standardError.getError() + "\n";
	}

}
