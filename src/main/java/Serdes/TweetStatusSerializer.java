package Serdes;

import org.apache.kafka.common.serialization.Serializer;
import twitter4j.Status;

import java.util.Map;

public class TweetStatusSerializer implements Serializer<Status> {
  public void configure(Map map, boolean b) {

  }

  public byte[] serialize(String topic, Status o) {
    return new byte[0];
  }

  public void close() {

  }
}
