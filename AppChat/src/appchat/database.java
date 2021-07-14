/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appchat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Hieu
 */
public class database {

    public static Connection lienketsql() throws ClassNotFoundException, SQLException {
        String hostName = "127.0.0.1";
        String sqlInstanceName = "DESKTOP-CVVAVE6";
        String database = "login";
        String userName = "sa";
        String password = "12345";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // Cấu trúc URL Connection dành cho SQLServer
        // Ví dụ:
        // jdbc:sqlserver://ServerIp:1433/SQLEXPRESS;databaseName=simplehr
        String connectionURL = "jdbc:sqlserver://" + hostName + ":1433"
                + ";instance=" + sqlInstanceName + ";databaseName=" + database;

        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;

    }

    public static boolean truyvanidpass(String id, String pass) throws ClassNotFoundException, SQLException {
        boolean login = false;
        Connection conn = lienketsql();
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        String sql = "Select id, pass from users";

        // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
        ResultSet rs = statement.executeQuery(sql);

        // Duyệt trên kết quả trả về.
        while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
            if(id.equals(rs.getString(1)) && pass.equals(rs.getString(2))){
                login = true;
                break;
            }
        }
        // Đóng kết nối
        conn.close();
        return login;
//        String sql=(id,password)

    }

    public static void insert(String us, String pass) throws ClassNotFoundException, SQLException {
        Connection connection = lienketsql();

        Statement statement = connection.createStatement();

        String sql = "Insert into users (id, pass) values ('" + us + "', '" + pass + "')";

        // Thực thi câu lệnh.
        // executeUpdate(String) sử dụng cho các loại lệnh Insert,Update,Delete.
        int rowCount = statement.executeUpdate(sql);

        // In ra số dòng được trèn vào bởi câu lệnh trên.
        System.out.println("Row Count affected = " + rowCount);
    }
}
