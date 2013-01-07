package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import springbook.user.domain.User;

public class UserDao {
	private ConnectionMaker connectionMaker;
	
	public UserDao(ConnectionMaker connectionMaker){
		this.connectionMaker = connectionMaker;
	}
	
	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();

		PreparedStatement ps = c
				.prepareStatement("insert into users(id, name, password) values (?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		ps.executeUpdate();
		ps.close();
		c.close();

	}

	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();

		PreparedStatement ps = c
				.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();

		rs.next();

		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));

		rs.close();
		ps.close();
		c.close();

		return user;
	}

	//public abstract Connection getConnection() throws ClassNotFoundException,SQLException;
	
	
	/*public class NUserDao extends UserDao {
		public Connection getConnection()throws ClassNotFoundException,
		SQLException {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection c = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "mindware", "2101");
			return c;
		}
	}*/
	
}