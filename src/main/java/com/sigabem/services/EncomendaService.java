package com.sigabem.services;

import com.sigabem.entities.ConsulEncomenda;
import com.sigabem.exception.ResourceNotException;
import com.sigabem.models.DadosEntrega;
import com.sigabem.models.DadosEnvio;
import com.sigabem.repositories.EncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;


@Service
public class EncomendaService {
	@Autowired
    private EncomendaRepository repository;

    @Autowired
    private OperacoesFrete operaçõesFrete;

    /** Persistido no banco de dados as informações da encomenda. **/
    public DadosEntrega SalvarConsulta(DadosEnvio dadosEnvio) throws ResourceNotException {

        ConsulEncomenda consultaEncomenda = new ConsulEncomenda();

        consultaEncomenda.setNomeDestinatario(dadosEnvio.getNomeDestinatario());

        consultaEncomenda.setCepOrigem(dadosEnvio.getCepOrigem());

        consultaEncomenda.setCepDestino(dadosEnvio.getCepDestino());

        consultaEncomenda.setPeso(dadosEnvio.getPeso());

        consultaEncomenda.setDataConsulta(LocalDate.now());

        consultaEncomenda.setVlTotalFrete(operaçõesFrete.TotalFrete(dadosEnvio));

        consultaEncomenda.setDataPrevistaEntrega(operaçõesFrete.calcularPrevisaoDeEntrega(dadosEnvio, consultaEncomenda.getDataConsulta()));

        return responseDadosEntrega((ConsulEncomenda) repository.save(consultaEncomenda));
    }

    /** Retorna os dados de entrega. **/
    private DadosEntrega responseDadosEntrega(ConsulEncomenda consultaEncomenda){
        DadosEntrega responseConsultaDTO = new DadosEntrega();

        responseConsultaDTO.setCepOrigem(consultaEncomenda.getCepOrigem());
        responseConsultaDTO.setCepDestino(consultaEncomenda.getCepDestino());
        responseConsultaDTO.setVlTotalFrete(consultaEncomenda.getVlTotalFrete());
        responseConsultaDTO.setDataPrevistaEntrega(consultaEncomenda.getDataPrevistaEntrega());

        return responseConsultaDTO;
    }

    /** Consultar todas as informações contidas no banco de dados.**/
    @GetMapping
    public List<ConsulEncomenda> getAllEncomendas() {
        return repository.findAll();
    }


}
