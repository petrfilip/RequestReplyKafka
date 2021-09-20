package com.gauravg.producer;

import java.util.Collection;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.GenericMessageListenerContainer;
import org.springframework.kafka.requestreply.CorrelationKey;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

public class EgwReplyingKafkaTemplate<K, V, R> extends ReplyingKafkaTemplate<K, V, Collection<ConsumerRecord<K, R>>> {

  private Logger logger = LoggerFactory.getLogger(EgwReplyingKafkaTemplate.class);

  public EgwReplyingKafkaTemplate(ProducerFactory<K, V> producerFactory,
      GenericMessageListenerContainer<K, Collection<ConsumerRecord<K, R>>> replyContainer) {
    super(producerFactory, replyContainer);
  }

  public EgwReplyingKafkaTemplate(ProducerFactory<K, V> producerFactory,
      GenericMessageListenerContainer<K, Collection<ConsumerRecord<K, R>>> replyContainer, boolean autoFlush) {
    super(producerFactory, replyContainer, autoFlush);
  }

  @Override
  protected void logLateArrival(ConsumerRecord<K, Collection<ConsumerRecord<K, R>>> record, CorrelationKey correlationId) {
    logger.warn("Late arrival - Invoke outgoing configuration for {} ", correlationId);
  }
}
