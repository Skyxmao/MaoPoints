package me.xmao.MaoPoints.SQL;

import java.sql.*;
import me.xmao.MaoPoints.Utils.Tools;

public class SqlHelper
{
    protected Connection conn;
    protected Statement stmt;
    public Boolean isShow;
    protected String dbType;
    
    public SqlHelper(final String dbFileName) {
        this.conn = null;
        this.stmt = null;
        this.isShow = false;
        this.dbType = "sqlite";
        try {
            Class.forName("org.sqlite.JDBC");
        }
        catch (ClassNotFoundException e) {
            Tools.Feedback(e, this.isShow, "\u627e\u4e0d\u5230sqlite\u7c7b\u9a71\u52a8");
        }
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:" + dbFileName);
        }
        catch (SQLException e2) {
            Tools.Feedback(e2, this.isShow, "\u65e0\u6cd5\u52a0\u8f7d\u6587\u4ef6" + dbFileName);
        }
    }
    
    public SqlHelper(final String host, final String user, final String pass, final String db) {
        this.conn = null;
        this.stmt = null;
        this.isShow = false;
        this.dbType = "mysql";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            Tools.Feedback(e, this.isShow, "\u627e\u4e0d\u5230mysql\u7c7b\u9a71\u52a8");
        }
        final String url = "jdbc:mysql://" + host + "/" + db;
        try {
            this.conn = DriverManager.getConnection(url, user, pass);
        }
        catch (SQLException e2) {
            Tools.Feedback(e2, this.isShow, "\u8fde\u63a5mysql\u6570\u636e\u5e93\u5931\u8d25,\u8bf7\u68c0\u67e5\u8fde\u63a5\u5730\u5740\u4fe1\u606f!");
        }
    }
    
    public PreparedStatement query(final String sql) throws SQLException {
        final PreparedStatement stmt = this.conn.prepareStatement(sql);
        return stmt;
    }
    
    public void setShow(final boolean isShow) {
        this.isShow = isShow;
    }
}
