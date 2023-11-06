package br.com.fiap.postech.parquimetro.schedulingtasks;

import br.com.fiap.postech.parquimetro.dominio.Estacionamento;
import br.com.fiap.postech.parquimetro.dominio.TempoDecorrido;
import br.com.fiap.postech.parquimetro.service.EstacionamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private EstacionamentoService estacionamentoService;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

    @Scheduled(fixedRate = 60000)
    public void notificarCondutor() {

        LocalDateTime agora = LocalDateTime.now();

        List<Estacionamento> estacionamentos = estacionamentoService.consultarAtivos();
        estacionamentos.forEach(estacionamento -> {
            int duracaoContratadaEmHoras = estacionamento.getDuracaoContratadaEmHoras();
            if (duracaoContratadaEmHoras > 0) {
                // envia notificação se a diferença entre a hora atual e o término
                // da hora de expiração é menor do que 15 minutos.
                LocalDateTime momentoDeExpiracao =
                        estacionamento.getEntrada().plusHours(duracaoContratadaEmHoras);
                long diferencaMinutos = ChronoUnit.MINUTES.between(agora, momentoDeExpiracao);
                if(diferencaMinutos < 15) {
                    log.info("Prezado " +
                            estacionamento.getVeiculo().getCondutor().getNome()
                            + ", o estacionamento do veículo, de placa "
                            + estacionamento.getVeiculo().getPlaca()
                            + " expirará em " + momentoDeExpiracao);
                } else {
                    log.info("Nenhuma notificação a enviar.");
                }
            } else {
                // envia notificação se tiver completado 45 minutos de estacionamento
                // (ou seja, faltar 15 minutos para completar a próxima hora)
                TempoDecorrido tempoDecorrido = estacionamento.calcularTempoDecorrido();
                if (tempoDecorrido.minutos() == 45) {
                    log.info("Prezado " +
                            estacionamento.getVeiculo().getCondutor().getNome()
                            + ", o estacionamento do veículo, de placa "
                            + estacionamento.getVeiculo().getPlaca()
                            + " se estenderá por mais uma hora em "
                            + estacionamento.getEntrada().plusHours(1));
                } else {
                    log.info("Nenhuma notificação a enviar.");
                }

            }
        });
    }
}
