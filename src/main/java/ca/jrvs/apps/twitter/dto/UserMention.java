package ca.jrvs.apps.twitter.dto;

import java.util.Arrays;

public class UserMention {

  public long id;
  public String id_str;
  public int[] indices;
  public String name;
  public String screen_name;

  @Override
  public String toString() {
    return "UserMention{" +
        "id=" + id +
        ", id_str='" + id_str + '\'' +
        ", indices=" + Arrays.toString(indices) +
        ", name='" + name + '\'' +
        ", screen_name='" + screen_name + '\'' +
        '}';
  }
}
