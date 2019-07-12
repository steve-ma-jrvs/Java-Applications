package ca.jrvs.apps.twitter.dto;


import java.util.List;

public class Hashtag {

  private List<Long> indices = null;
  private String text;

  public List<Long> getIndices() {
    return indices;
  }

  public void setIndices(List<Long> indices) {
    this.indices = indices;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
