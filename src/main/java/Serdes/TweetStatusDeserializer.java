package Serdes;

import org.apache.kafka.common.serialization.Deserializer;
import twitter4j.Status;

import java.util.Map;

public class TweetStatusDeserializer implements Deserializer<Status> {
  public void configure(Map<String, ?> map, boolean b) {

  }

  public Status deserialize(String s, byte[] bytes) {
    return null;
  }

  public void close() {

  }
}
