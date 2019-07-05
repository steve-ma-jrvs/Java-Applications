package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataTransferObject;

public class Orders implements DataTransferObject {

  private long id;
  private String creation_date;
  private double total_due;
  private String status;
  private long customer_id;
  private long salesperson_id;

  @Override
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCreation_date() {
    return creation_date;
  }

  public void setCreation_date(String creation_date) {
    this.creation_date = creation_date;
  }

  public double getTotal_due() {
    return total_due;
  }

  public void setTotal_due(double total_due) {
    this.total_due = total_due;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public long getCustomer_id() {
    return customer_id;
  }

  public void setCustomer_id(long customer_id) {
    this.customer_id = customer_id;
  }

  public long getSalesperson_id() {
    return salesperson_id;
  }

  public void setSalesperson_id(long salesperson_id) {
    this.salesperson_id = salesperson_id;
  }

  @Override
  public String toString() {
    return "Orders{" +
        "id=" + id +
        ", creation_date='" + creation_date + '\'' +
        ", total_due=" + total_due +
        ", status='" + status + '\'' +
        ", customer_id=" + customer_id +
        ", salesperson_id=" + salesperson_id +
        '}';
  }
}
