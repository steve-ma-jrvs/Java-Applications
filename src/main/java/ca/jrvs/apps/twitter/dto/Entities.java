package ca.jrvs.apps.twitter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Arrays;

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

  public Hashtag[] hashtags;
  public UserMention[] user_mentions;

  @Override
  public String toString() {
    return "Entities{" +
        "hashtags=" + Arrays.toString(hashtags) +
        ", user_mentions=" + Arrays.toString(user_mentions) +
        '}';
  }
}
