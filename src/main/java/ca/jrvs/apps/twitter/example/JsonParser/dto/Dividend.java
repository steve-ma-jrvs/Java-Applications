package ca.jrvs.apps.twitter.example.JsonParser.dto;

public class Dividend {

  public String exDate;
  public String paymentDate;
  public String recordDate;
  public String declaredDate;
  public double amount;

  @Override
  public String toString() {
    return "Dividend{" +
        "exDate='" + exDate + '\'' +
        ", paymentDate='" + paymentDate + '\'' +
        ", recordDate='" + recordDate + '\'' +
        ", declaredDate='" + declaredDate + '\'' +
        ", amount=" + amount +
        '}';
  }
}
