package com.sigabem.services;

import com.sigabem.models.InfoCep;
import com.sigabem.exception.ResourceNotException;
import com.sigabem.models.DadosEnvio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class OperacoesFrete {
	@Autowired
    private CepConsultaApi cepConsulta;

    /** Retorna a data prevista para a entrega. **/
    public LocalDate calcularPrevisaoDeEntrega(DadosEnvio dadosEnvio, LocalDate data) throws ResourceNotException {

        InfoCep cepOrigem = cepConsulta.infCep(dadosEnvio.getCepOrigem());
        InfoCep cepDestino = cepConsulta.infCep(dadosEnvio.getCepDestino());
        int PrazoDeEntregaEmDias = PrazoEntrega(cepOrigem, cepDestino);

        return data.plusDays(PrazoDeEntregaEmDias);
    }

    /**Retorna o valor total do frete com desconto. **/
    public Double TotalFrete(DadosEnvio dadosEnvio) throws ResourceNotException {

        InfoCep origem = cepConsulta.infCep(dadosEnvio.getCepOrigem());
        InfoCep destino = cepConsulta.infCep(dadosEnvio.getCepDestino());

        return  desconto(origem, destino, dadosEnvio.getPeso());
    }

    /** Retorna o prazo de entrega em dias. **/
    public Integer PrazoEntrega(InfoCep origin, InfoCep destino) {

        if (origin.getDdd().equalsIgnoreCase(destino.getDdd()))  return 1;

        if (origin.getUf().equalsIgnoreCase(destino.getUf())) return 3;

        return 10;
    }

    /** Recebe CEPs destino, origem  e retorna o valor tatal com desconto. **/
    public Double desconto(InfoCep origin, InfoCep destino, Double peso) {

        if (origin.getDdd().equalsIgnoreCase(destino.getDdd())) {
            return calcularDesconto(peso, 50D);
        }

        if (origin.getUf().equalsIgnoreCase(destino.getUf())) {
            return calcularDesconto(peso, 75D);
        }

        return peso;
    }

    /** Calcula o desconto do envío. **/
    private Double calcularDesconto(Double valor, Double desconto) {

        Double descontoTotal =  (valor * desconto) / 100;

        return valor - descontoTotal;
    }
}
