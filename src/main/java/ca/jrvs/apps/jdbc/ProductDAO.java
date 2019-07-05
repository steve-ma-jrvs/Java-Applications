package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataAccessObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDAO extends DataAccessObject<Product> {

  private static final String GET_ONE = new StringBuilder()
      .append("SELECT product_id, code, name, ")
      .append("size, variety, price, status FROM product WHERE product_id=?").toString();

  public ProductDAO(Connection connection) {
    super(connection);
  }

  @Override
  public Product findById(long id) {
    Product product = new Product();
    try (PreparedStatement statement = this.connection.prepareStatement(GET_ONE)) {
      statement.setLong(1, id);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        product.setId(rs.getLong("product_id"));
        product.setCode(rs.getString("code"));
        product.setName(rs.getString("name"));
        product.setSize(rs.getInt("size"));
        product.setVariety(rs.getString("variety"));
        product.setPrice(rs.getDouble("price"));
        product.setStatus(rs.getString("status"));
      }

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    return product;
  }

  @Override
  public List<Product> findAll() {
    return null;
  }

  @Override
  public Product update(Product dto) {
    return null;
  }

  @Override
  public Product create(Product dto) {
    return null;
  }

  @Override
  public void delete(long id) {

  }

}
