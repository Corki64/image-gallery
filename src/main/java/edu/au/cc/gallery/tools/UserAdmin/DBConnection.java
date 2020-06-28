// package edu.au.cc.gallery.tools.UserAdmin;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// import org.json.JSONObject;

// public class DBConnection {


//    private static final String dbURL = "jdbc:postgresql://image-gallery.cqxj5v5xjbzr.us-east-2.rds.amazonaws.com/image_gallery";
//    public static Connection connection;

//    String secretID = "admins";

//    private JSONObject getSecret() {
// 		Secrets obj = new Secrets();
// 		String s = obj.getSecretString(secretID);
// 		return new JSONObject(s);
// 	}

// 	private String getPassword(JSONObject secret) {
// 		return secret.getString("password");
// 	}

// 	public void connect() throws SQLException {
// 		try {
// 			Class.forName("org.postgresql.Driver");
// 			JSONObject secret = getSecret();
// 			connection = DriverManager.getConnection(dbURL, "admins", getPassword(secret));
// 		} catch (ClassNotFoundException e) {
// 			e.printStackTrace();
// 			System.exit(1);
// 		}
//    }
   
//    	public void close() throws SQLException {
// 		connection.close();
//    }
   
//    public ResultSet prepStatement(String statementIn) throws SQLException {
// 		PreparedStatement stmt = connection.prepareStatement(statementIn);
// 		ResultSet rs = stmt.executeQuery();
// 		return rs;
//    }
   
//    public void execute(String query, String[] values) throws SQLException {
//       PreparedStatement stmt = connection.prepareStatement(query);
//       for (int i = 0; i < values.length; i++) {
//           stmt.setString(i + 1, values[i]);
//       }
//       stmt.execute();
//   }
   
   
// }