package ca.jrvs.apps.twitter.dto;

import java.util.List;

public class UserMention {

  private long id;
  private String id_str;
  private List<Long> indices = null;
  private String name;
  private String screen_name;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getId_str() {
    return id_str;
  }

  public void setId_str(String id_str) {
    this.id_str = id_str;
  }

  public List<Long> getIndices() {
    return indices;
  }

  public void setIndices(List<Long> indices) {
    this.indices = indices;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getScreen_name() {
    return screen_name;
  }

  public void setScreen_name(String screen_name) {
    this.screen_name = screen_name;
  }
}