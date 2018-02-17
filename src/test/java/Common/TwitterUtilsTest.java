package Common;

import org.junit.Assert;
import org.junit.Test;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.List;

public class TwitterUtilsTest {

  @Test
  public void shouldSearchTweets() throws TwitterException {
    TwitterUtils twitterUtils = TwitterUtils.getInstance();
    QueryResult indian_army = twitterUtils.executeQuery("India");
    List<Status> tweets = indian_army.getTweets();
    Assert.assertNotNull(tweets);
    Assert.assertTrue(tweets.size() > 0);
  }

}