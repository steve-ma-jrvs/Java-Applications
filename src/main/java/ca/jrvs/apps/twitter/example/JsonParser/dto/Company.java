package ca.jrvs.apps.twitter.example.JsonParser.dto;

import java.util.Arrays;

public class Company {

  public String symbol;
  public String companyName;
  public String exchange;
  public String description;
  public String CEO;
  public String sector;
  public Financial[] financials;
  public Dividend[] dividends;

  @Override
  public String toString() {
    return "Company{" +
        "symbol='" + symbol + '\'' +
        ", companyName='" + companyName + '\'' +
        ", exchange='" + exchange + '\'' +
        ", description='" + description + '\'' +
        ", CEO='" + CEO + '\'' +
        ", sector='" + sector + '\'' +
        ", financials=" + Arrays.toString(financials) +
        ", dividends=" + Arrays.toString(dividends) +
        '}';
  }
}
