package ca.jrvs.apps.twitter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(
    {
        "media",
        "urls",
        "symbols",
        "polls",
        "place",
    }
)
public class Entities {

  private List<Hashtag> hashtags = null;
  private List<UserMention> userMentions = null;

  public List<Hashtag> getHashtags() {
    return hashtags;
  }

  public void setHashtags(List<Hashtag> hashtags) {
    this.hashtags = hashtags;
  }

  public List<UserMention> getUserMentions() {
    return userMentions;
  }

  public void setUserMentions(List<UserMention> userMentions) {
    this.userMentions = userMentions;
  }
}
