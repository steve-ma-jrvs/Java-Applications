package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dto.Tweet;
import java.util.List;

public interface TwitterService {


  /**
   * Post a Tweet along with a geo location.
   * Print Tweet JSON which returned by the Twitter API
   *
   * @param text tweet text
   * @param latitude geo latitude
   * @param longitude geo longitude
   * @return Tweet object which is returned by the Twitter API
   *
   * @throws IllegalArgumentException if text exceed max number of allowed characters
   *                                  or lat/long out of range
   */
  Tweet postTweet(String text, Double latitude, Double longitude);


  /**
   * Search a Tweet by id and print Tweet Object which returned by the Twitter API
   *
   * @param id tweet id
   * @param fields print Tweet fields from this param. Print all fields if empty
   * @return Tweet object which is returned by the Twitter API
   *
   * @throws IllegalArgumentException if id or fields param is invalid
   */
  Tweet showTweet(String id, String[] fields);

  /**
   * Delete Tweet(s) by id(s).
   * Print Tweet object(s) which returned by the Twitter API
   *
   * @param ids tweet IDs which will be deleted
   * @return Tweet objects that were deleted through the Twitter API
   *
   * @throws IllegalArgumentException if one of the IDs is invalid.
   */
  List<Tweet> deleteTweets(String[] ids);


}
