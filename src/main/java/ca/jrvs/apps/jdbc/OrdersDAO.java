package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataAccessObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrdersDAO extends DataAccessObject<Orders> {

  private static final String GET_ONE = "SELECT order_id, creation_date, total_due, "
      + "status, customer_id, salesperson_id FROM orders WHERE order_id=?";

  public OrdersDAO(Connection connection) {
    super(connection);
  }

  @Override
  public Orders findById(long id) {
    Orders orders = new Orders();
    try (PreparedStatement statement = this.connection.prepareStatement(GET_ONE)) {
      statement.setLong(1, id);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        orders.setId(rs.getLong("order_id"));
        orders.setCreation_date(rs.getString("creation_date"));
        orders.setTotal_due(rs.getDouble("total_due"));
        orders.setStatus(rs.getString("status"));
        orders.setCustomer_id(rs.getLong("customer_id"));
        orders.setSalesperson_id(rs.getLong("salesperson_id"));
      }

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    return orders;
  }

  @Override
  public List<Orders> findAll() {
    return null;
  }

  @Override
  public Orders update(Orders dto) {
    return null;
  }

  @Override
  public Orders create(Orders dto) {
    return null;
  }

  @Override
  public void delete(long id) {

  }

}
