package ca.jrvs.apps.twitter.dto;

import java.util.Arrays;

public class Coordinates {

  private String type;
  private float[] coordinates;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public float[] getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(float[] coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  public String toString() {
    return "Coordinates{" +
        "type='" + type + '\'' +
        ", coordinates=" + Arrays.toString(coordinates) +
        '}';
  }
}
