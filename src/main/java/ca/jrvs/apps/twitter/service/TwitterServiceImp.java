package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import static ca.jrvs.apps.twitter.util.TweetUtil.*;

@Service
public class TwitterServiceImp implements TwitterService {

    private CrdDao dao;

    @Autowired
    public TwitterServiceImp(CrdDao dao) {
        this.dao = dao;
    }

    @Override
    public Tweet postTweet(String text, Double latitude, Double longitude) {
        //Build Tweet
        Tweet postTweet = buildTweet(text, longitude, latitude);
        //Validate Tweet
        validatePostTweet(postTweet);

        try {
            Tweet responseTweet = (Tweet) dao.create(postTweet);
            printTweet(responseTweet);
        } catch (Exception e) {
            throw new RuntimeException("Fail to post tweet");
        }

        return postTweet;
    }

    @Override
    public Tweet showTweet(String id, String[] fields) {
        Tweet tweet;

        //validate id
        if(!validId.test(id)){
            throw new IllegalArgumentException("ID must be number");
        }

        //Get tweet and Print
        tweet = (Tweet) dao.findById(id);
        printTweet(tweet);

        return tweet;
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        List<Tweet> tweets = new ArrayList<>();
        for (String id : ids) {
            if(!validId.test(id)){
                System.out.println(id);
                throw new IllegalArgumentException("ID must be valid");
            } else {
                Tweet tweet = (Tweet) dao.deleteById(id);
                printTweet(tweet);
                tweets.add(tweet);
            }
        }
        return tweets;
    }


}
