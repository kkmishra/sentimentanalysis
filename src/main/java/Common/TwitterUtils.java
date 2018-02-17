package Common;

import twitter4j.*;

public class TwitterUtils {

  private static Twitter twitter;
  private static TwitterStream twitterStream;
  private static TwitterUtils twitterUtils;

  private TwitterUtils() {
    twitter = getTwitter();
    twitterStream = getTwitterStream();
  }

  public static TwitterUtils getInstance() {
    if (twitterUtils == null) {
      twitterUtils = new TwitterUtils();
    }
    return twitterUtils;
  }

  private Twitter getTwitter() {
    return new TwitterFactory().getInstance();
  }

  private TwitterStream getTwitterStream() {
    return new TwitterStreamFactory().getInstance();
  }

  public QueryResult executeQuery(String queryString) throws TwitterException {
    Query query = new Query(queryString);
    return twitter.search(query);
  }

  public void getStreamOfTweets() {
//    twitterStream.addConnectionLifeCycleListener();
  }
}
