package ca.jrvs.apps.twitter.example.JsonParser.dto;

public class Financial {

  public String reportDate;
  public long grossProfit;
  public long costOfRevenue;
  public long operatingRevenue;
  public long totalRevenue;
  public long operatingIncome;
  public long netIncome;

  @Override
  public String toString() {
    return "Financial{" +
        "reportDate='" + reportDate + '\'' +
        ", grossProfit=" + grossProfit +
        ", costOfRevenue=" + costOfRevenue +
        ", operatingRevenue=" + operatingRevenue +
        ", totalRevenue=" + totalRevenue +
        ", operatingIncome=" + operatingIncome +
        ", netIncome=" + netIncome +
        '}';
  }
}
