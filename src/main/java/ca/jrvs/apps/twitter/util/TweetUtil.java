package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.dto.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;

public class TweetUtil {

    private final static Double MAX_LAT = 90d;
    private final static Double MIN_LAT = -90d;
    private final static Double MAX_LON = 180d;
    private final static Double MIN_LON = -180d;

    //140 UTF-8 encoded characters. Each UTF-8 char is 4 bytes. Each byte = 8 bits.
    private final static Integer MAX_TWEET_CHAR = 140;

    public static void validatePostTweet(String text, Double latitude, Double longitude) {
        if (StringUtil.isEmpty(text) || text.toCharArray().length > MAX_TWEET_CHAR) {
            throw new IllegalArgumentException("Invalid Tweet");
        }
        if (latitude < MIN_LAT || latitude > MAX_LAT || longitude < MIN_LON
                || longitude > MAX_LON) {
            throw new IllegalArgumentException("Invalid latitude or longitude value");
        }
    }
    

    public static void printTweet(Tweet tweet) {
        try {
            System.out.println(JsonUtil.toPrettyJson(tweet));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to convert tweet object to string", e);
        }
    }

}
