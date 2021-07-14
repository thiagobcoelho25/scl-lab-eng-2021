package service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import exception.StandardError;
import model.Bairro;
import model.Funcionario;

public class FuncionarioService {
	private final String url = "http://localhost:8080/scl/funcionario";
	
	private final Client client = ClientBuilder.newClient();
	
	
	public List<Funcionario> listAll() {
		try {
            WebTarget target = client.target(url);
            String json = target.request().get(String.class);
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Funcionario>> mapType = new TypeReference<List<Funcionario>>() {
            };
            List<Funcionario> lista = mapper.readValue(json, mapType);
            return lista;
        } catch (IOException ex) {
            Logger.getLogger(BairroService.class.getName()).log(Level.SEVERE, null, ex);
        }
		return null;
	}
	
	public String insert(Funcionario funcionario) {
		try {
            WebTarget target = client.target(url);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(funcionario);
            Response response = target.request().post(Entity.entity(json, "application/json;charset=UTF-8"));
            if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                String stringError = response.readEntity(String.class);
                StandardError standardError = mapper.readValue(stringError, StandardError.class);
                return standardError.getMessage();
            }
        } catch (IOException ex) {
            Logger.getLogger(BairroService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
	}
	
	

}
