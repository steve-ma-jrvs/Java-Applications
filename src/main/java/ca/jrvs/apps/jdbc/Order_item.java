package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataTransferObject;

public class Order_item implements DataTransferObject {

  private long id;
  private long order_id;
  private long product_id;
  private long quantity;

  @Override
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getOrder_id() {
    return order_id;
  }

  public void setOrder_id(long order_id) {
    this.order_id = order_id;
  }

  public long getProduct_id() {
    return product_id;
  }

  public void setProduct_id(long product_id) {
    this.product_id = product_id;
  }

  public long getQuantity() {
    return quantity;
  }

  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "Order_item{" +
        "id=" + id +
        ", order_id=" + order_id +
        ", product_id=" + product_id +
        ", quantity=" + quantity +
        '}';
  }
}
