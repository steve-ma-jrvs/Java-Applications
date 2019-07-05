package ca.jrvs.apps.twitter;


import ca.jrvs.apps.twitter.service.TwitterServiceImp;

public class TwitterCLIRunner {

  public static void main(String[] args) throws Exception {

    TwitterServiceImp twitterServiceImp = new TwitterServiceImp();

    //twitterServiceImp.showTweet("1146766931573301248");

    //twitterServiceImp.postTweet("GoodDay!July5thTest2", 1.12, 2.12);

    String[] ids = {"1147176481539448832"};
    twitterServiceImp.deleteTweets(ids);

  }

}
