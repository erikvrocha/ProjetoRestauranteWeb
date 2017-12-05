package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
* CLASSE QUE DEVERÁ REALIZAR CONEXÃO COM O BANCO DE DADOS
*/
public class ConnectionFactory {
   // -----------------------------------------------------------
   // DRIVER JDBC
   //
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
   // -----------------------------------------------------------
   //FAZENDO CONEXAO COM BD
   public static Connection makeConnection() throws SQLException {
      return DriverManager.getConnection (
         "jdbc:mysql://localhost/Restaurante?user=root&password=123456"
      );
   }
}