package br.com.livro.domain;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDAO {

    public BaseDAO() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost/livro";
        Connection conn = DriverManager.getConnection(url, "livro", "OkComputer@7545");
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        BaseDAO db = new BaseDAO();
        Connection conn = db.getConnection();
        System.out.println(conn);
    }

}
