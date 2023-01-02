package com.example.demo.schedule;

import com.example.demo.models.Pauta;
import com.example.demo.service.KafkaProducerService;
import com.example.demo.service.PautaService;
import com.example.demo.service.ResultService;
import com.example.demo.dto.ResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.example.demo.util.Constants.FECHADA;
import static com.example.demo.util.JsonUtil.toJson;

@Component
@EnableScheduling
public class PautaSchedule {

    private static final Logger logger = LoggerFactory.getLogger(PautaSchedule.class);

    private final PautaService pautaService;
    private final ResultService resultService;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public PautaSchedule(PautaService pautaService,
                         ResultService resultService,
                         KafkaProducerService kafkaProducerService) {
        this.pautaService = pautaService;
        this.resultService = resultService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Scheduled(fixedDelay = 1000)
    public void closeRoutesCaseTrue() {
        pautaService.consultarPautasAbertas().stream()
                .filter(Pauta::estahFechadaIhNaoFoiEnviada).forEach(pauta -> {
            ResultDTO resultDTO = resultService.getResult(pauta.getId());
            atulizarResultado(resultDTO);
            logger.info("Enviando resultado :" + resultDTO);
            kafkaProducerService.writeMessage(toJson(resultDTO));

            logger.info("salvando pauta fechada :" + pauta);
            sinalizarEnvioPauta(pauta);
            pautaService.atualizarPauta(pauta);
        });
    }

    private void atulizarResultado(ResultDTO resultDTO) {
        resultDTO.setStatus(FECHADA);
    }

    private void sinalizarEnvioPauta(Pauta pauta) {
        pauta.setEnviadoKafka(true);
    }
}
