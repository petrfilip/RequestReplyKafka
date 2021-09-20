package com.gauravg.consumer;

import com.gauravg.model.Model;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;


@Component
public class ReplyingKafkaConsumer {

  private final Random random = new Random();

  Logger logger = LoggerFactory.getLogger(ReplyingKafkaConsumer.class);

  @KafkaListener(topics = "${kafka.topic.request-topic}")
  @SendTo
  public Model listen(Model request) throws InterruptedException {
    if (random.nextBoolean()) {
      logger.warn("With delay");
      Thread.sleep(6000);
    } else {
      logger.debug("Without delay");
    }
    int sum = request.getFirstNumber() + request.getSecondNumber();
    request.setAdditionalProperty("sum", sum);
    return request;
  }


}
