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
import model.Produto;

public class ProdutoService {
private final String url = "http://localhost:8080/scl/produtos";
	
	private final Client client = ClientBuilder.newClient(); 
	
	public List<Produto> listAll(){
		try {
            WebTarget target = client.target(url);
            String json = target.request().get(String.class);
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Produto>> mapType = new TypeReference<List<Produto>>() {
            };
            List<Produto> lista = mapper.readValue(json, mapType);
            return lista;
        } catch (IOException ex) {
            Logger.getLogger(BairroService.class.getName()).log(Level.SEVERE, null, ex);
        }
		return null;
	}
	
	// buscando apenas produtos com ingredientes principais disponiveis no estoque
	public List<Produto> listAllDisponiveis(){
		try {
            WebTarget target = client.target(url + "/findDisponiveis");
            String json = target.request().get(String.class);
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Produto>> mapType = new TypeReference<List<Produto>>() {
            };
            List<Produto> lista = mapper.readValue(json, mapType);
            return lista;
        } catch (IOException ex) {
            Logger.getLogger(BairroService.class.getName()).log(Level.SEVERE, null, ex);
        }
		return null;
	}
	
	public List<Produto> listAllProdutosMaiorQue(Double valor){
		try {
            WebTarget target = client.target(url + "/findPrecoMaiorQue/" + valor.toString());
            String json = target.request().get(String.class);
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Produto>> mapType = new TypeReference<List<Produto>>() {
            };
            List<Produto> lista = mapper.readValue(json, mapType);
            return lista;
        } catch (IOException ex) {
            Logger.getLogger(BairroService.class.getName()).log(Level.SEVERE, null, ex);
        }
		return null;
	}
	
	public String insert(Produto produto) {
		try {
            WebTarget target = client.target(url);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(produto);
            Response response = target.request().post(Entity.entity(json, "application/json;charset=UTF-8"));
            if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
                String stringError = response.readEntity(String.class);
                StandardError standardError = mapper.readValue(stringError, StandardError.class);
                return constru????oStandardError(standardError);
            }
        } catch (IOException ex) {
            Logger.getLogger(BairroService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
	}
	
	public String update(Produto produto) {
        try {
            WebTarget target = client.target(url);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(produto);
            Response response = target.request().put(Entity.entity(json, "application/json;charset=UTF-8"));
            if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                String stringError = response.readEntity(String.class);
                StandardError standardError = mapper.readValue(stringError, StandardError.class);
                return constru????oStandardError(standardError);
            }
        } catch (IOException ex) {
            Logger.getLogger(BairroService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
	
	public String delete(Integer id) {
        try {
            WebTarget target = client.target(url + "/" + id);
            ObjectMapper mapper = new ObjectMapper();
            Response response = target.request().delete();
            if (response.getStatus() != Response.Status.NO_CONTENT.getStatusCode()) {
                String stringError = response.readEntity(String.class);
                StandardError standardError = mapper.readValue(stringError, StandardError.class);
                return constru????oStandardError(standardError);
            }
        } catch (IOException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
	
	public String constru????oStandardError(StandardError standardError) {
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
