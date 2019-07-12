package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCExecutor {


  public static void main(String... args) {
    DatabaseConnectionManager dcm = new DatabaseConnectionManager(
        "localhost", "hplussport", "postgres",
        "password");

    // The Repository Pattern Approach
    /*
    try {
      Connection connection = dcm.getConnection();
      CustomerDAO customerDAO = new CustomerDAO(connection);
      OrdersDAO ordersDAO = new OrdersDAO(connection);
      ProductDAO productDAO = new ProductDAO(connection);
      SalespersonDAO salespersonDAO = new SalespersonDAO(connection);
      Order_itemDAO order_itemDAO = new Order_itemDAO(connection);
      Orders orders = ordersDAO.findById(1000);
      System.out.println(orders);

      List<Order_item> order_items = order_itemDAO.findbyOrderId(orders.getId());
      Customer customer = customerDAO.findById(orders.getCustomer_id());
      System.out.println(customer);
      Salesperson salesperson = salespersonDAO.findById(orders.getSalesperson_id());
      System.out.println(salesperson);
      for (Order_item order_item : order_items) {
        Product product = productDAO.findById(order_item.getProduct_id());
        System.out.println(order_item);
        System.out.println(product);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
     */

    try {
      Connection connection = dcm.getConnection();
      OrderDAO orderDAO = new OrderDAO(connection);
      Order order = orderDAO.findById(1000);
      System.out.println(order);

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

}
