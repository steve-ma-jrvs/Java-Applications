package ca.jrvs.apps.twitter.dto;


import java.util.Arrays;

public class Hashtag {

  public int[] indices;
  public String text;

  @Override
  public String toString() {
    return "Hashtag{" +
        "indices=" + Arrays.toString(indices) +
        ", text='" + text + '\'' +
        '}';
  }
}
