package com.sigabem.services;
import com.sigabem.models.InfoCep;
import com.sigabem.exception.ResourceNotException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class CepConsultaApi {
	
	 /** Consulta informações em um determinado CEP **/
    public InfoCep infCep(String cep) throws ResourceNotException {
        RestTemplate template = new RestTemplate();

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https").host("viacep.com.br").path("ws/"+cep+"/json").build();

        ResponseEntity<InfoCep> response = template.getForEntity(uri.toUriString(), InfoCep.class);

        if(response.getBody().getCep() == null){
            throw new ResourceNotException("CEP inválido: "+cep);
        }

        return response.getBody();
    }
}
