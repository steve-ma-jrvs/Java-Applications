package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataAccessObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Order_itemDAO extends DataAccessObject<Order_item> {

  private static final String GET_ONE = "SELECT order_item_id, order_id, product_id, "
      + "quantity FROM order_item WHERE order_item_id=?";

  private static final String GET_BY_ORDERID = "SELECT order_item_id, order_id, product_id, "
      + "quantity FROM order_item WHERE order_id=?";

  private static final String CHECK_ORDER_ITEMS = "SELECT count(*) FROM order_item WHERE order_id=?";

  public Order_itemDAO(Connection connection) {
    super(connection);
  }

  public int checkOrderItems(long id) {
    int n = 0;
    try (PreparedStatement statement = this.connection.prepareStatement(CHECK_ORDER_ITEMS)) {
      statement.setLong(1, id);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        n = rs.getInt("count");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }

    return n;
  }

  public List<Order_item> findbyOrderId(long id) {

    List<Order_item> order_items = new ArrayList<>();
    try (PreparedStatement statement = this.connection.prepareStatement(GET_BY_ORDERID)) {
      statement.setLong(1, id);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        Order_item order_item = new Order_item();
        order_item.setId(rs.getLong(1));
        order_item.setOrder_id(rs.getLong(2));
        order_item.setProduct_id(rs.getLong(3));
        order_item.setQuantity(rs.getInt(4));
        order_items.add(order_item);
      }

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    return order_items;
  }

  @Override
  public Order_item findById(long id) {
    Order_item order_item = new Order_item();
    try (PreparedStatement statement = this.connection.prepareStatement(GET_ONE)) {
      statement.setLong(1, id);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        order_item.setId(rs.getLong("order_item_id"));
        order_item.setOrder_id(rs.getLong("order_id"));
        order_item.setProduct_id(rs.getLong("product_id"));
        order_item.setQuantity(rs.getInt("quantity"));
      }

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    return order_item;
  }

  @Override
  public List<Order_item> findAll() {
    return null;
  }

  @Override
  public Order_item update(Order_item dto) {
    return null;
  }

  @Override
  public Order_item create(Order_item dto) {
    return null;
  }

  @Override
  public void delete(long id) {

  }
}
