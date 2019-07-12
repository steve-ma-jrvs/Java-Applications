package ca.jrvs.apps.twitter.dto;

import java.util.List;


public class Coordinates {

  private String type;
  private List<Double> coordinates = null;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<Double> getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(List<Double> coordinates) {
    this.coordinates = coordinates;
  }

}
