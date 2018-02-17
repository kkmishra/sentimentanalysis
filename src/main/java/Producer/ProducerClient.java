package Producer;

import Common.TwitterUtils;
import Serdes.TweetStatusSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.LongSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static Common.Constants.*;

public class ProducerClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProducerClient.class);

  public static void publish(int number) throws InterruptedException, TwitterException {
    KafkaProducer<Long, Status> producer = createProducer();
    TwitterUtils twitterUtils = TwitterUtils.getInstance();
    QueryResult queryResult = twitterUtils.executeQuery(QUERY);
    for (Status status : queryResult.getTweets()) {
      ProducerRecord<Long, Status> record = new ProducerRecord<Long, Status>(TOPIC, status.getId(), status);
      try {
        RecordMetadata metadata = producer.send(record).get();
        LOGGER.info("Message sent: key={}, value={}, offset={}, partition={}", record.key(),
          record.value(), metadata.offset(), metadata.partition());
      } catch (InterruptedException e) {
        LOGGER.error(e.getMessage());
        throw e;
      } catch (ExecutionException e) {
        LOGGER.error(e.getMessage());
      }
    }
    producer.close();
  }


  private static KafkaProducer<Long, Status> createProducer() {
    Properties properties = new Properties();
    properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
    properties.setProperty(ProducerConfig.CLIENT_ID_CONFIG, ProducerClient.class.getSimpleName());
    properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, TweetStatusSerializer.class.getName());
    return new KafkaProducer<Long, Status>(properties);
  }
}
