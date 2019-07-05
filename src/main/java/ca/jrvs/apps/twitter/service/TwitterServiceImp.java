package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dto.Tweet;

public class TwitterServiceImp implements TwitterService {

  @Override
  public void postTweet(String text, Double latitude, Double longitude) {

    Tweet tweet = new Tweet();
    //Coordinates coordinates = new Coordinates();
    tweet.setText(text);
    //tweet.setCoordinates(coordinates.setCoordinates(););

    TwitterRestDao twitterRestDao = new TwitterRestDao();

    System.out.println(twitterRestDao.save(tweet));


  }

  @Override
  public void showTweet(String id) {

    //, String[] fields

    TwitterRestDao twitterRestDao = new TwitterRestDao();

    Tweet c = twitterRestDao.findById(id);

    System.out.println(c);

  }

  @Override
  public void deleteTweets(String[] ids) {

    TwitterRestDao twitterRestDao = new TwitterRestDao();

    for (String id : ids) {
      System.out.println(twitterRestDao.deleteById(id));
    }


  }
}
