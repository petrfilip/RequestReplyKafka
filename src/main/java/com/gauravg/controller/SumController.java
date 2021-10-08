// package com.gauravg.controller;
//
// import com.gauravg.model.Model;
// import java.util.concurrent.ExecutionException;
// import org.apache.kafka.clients.consumer.ConsumerRecord;
// import org.apache.kafka.clients.producer.ProducerRecord;
// import org.apache.kafka.common.header.internals.RecordHeader;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.MediaType;
// import org.springframework.kafka.requestreply.KafkaReplyTimeoutException;
// import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
// import org.springframework.kafka.requestreply.RequestReplyFuture;
// import org.springframework.kafka.support.KafkaHeaders;
// import org.springframework.kafka.support.SendResult;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.RestController;
//
// @RestController
// public class SumController {
//
//   private final Logger logger = LoggerFactory.getLogger(SumController.class);
//
//   private final ReplyingKafkaTemplate<String, Model, Model> kafkaTemplate;
//
//   @Value("${kafka.topic.request-topic}")
//   private String requestTopic;
//
//   @Value("${kafka.topic.requestreply-topic}")
//   private String requestReplyTopic;
//
//   public SumController(ReplyingKafkaTemplate<String, Model, Model> kafkaTemplate) {
//     this.kafkaTemplate = kafkaTemplate;
//   }
//
//   @ResponseBody
//   @PostMapping(value = "/sum", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//   public Model sum(@RequestBody Model request) throws InterruptedException, ExecutionException {
//
//     // create producer record
//     ProducerRecord<String, Model> record = new ProducerRecord<>(requestTopic, request);
//     // set reply topic in header
//     record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, requestReplyTopic.getBytes()));
//     // post in kafka topic
//     RequestReplyFuture<String, Model, Model> sendAndReceive = kafkaTemplate.sendAndReceive(record);
//
//     // confirm if producer produced successfully
//     SendResult<String, Model> sendResult = sendAndReceive.getSendFuture().get();
//
//     logger.info("---------------- new request -------------------");
//
//     // get consumer record
//     ConsumerRecord<String, Model> consumerRecord = null;
//     try {
//       consumerRecord = sendAndReceive.get();
//       logger.info("Running success ACK");
//       return consumerRecord.value(); // success
//     } catch (InterruptedException e) {
//       e.printStackTrace();
//     } catch (ExecutionException e) {
//       if (e.getCause() instanceof KafkaReplyTimeoutException) {
//         logger.warn("Sending technical ACK");
//       } else {
//         e.printStackTrace();
//       }
//     }
//     // return consumer value
//     return new Model(); // success ACK
//   }
//
// }
