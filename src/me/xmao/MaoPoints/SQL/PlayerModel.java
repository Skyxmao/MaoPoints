package me.xmao.MaoPoints.SQL;

import java.sql.*;
import java.util.*;

import me.xmao.MaoPoints.Utils.Tools;


public class PlayerModel extends SqlHelper
{
    public PlayerModel(final String dbFileName) {
        super(dbFileName);
        this.createDB();
    }
    
    public boolean createDB() {
        try {
            final DatabaseMetaData dbMetaData = this.conn.getMetaData();
            final String[] types = { "TABLE" };
            final ResultSet tabs = dbMetaData.getTables(null, null, "player", types);
            if (tabs.next()) {
                return true;
            }
            final String sql = "CREATE TABLE `player` (`name` varchar(255)  PRIMARY KEY,`amount` int(11) DEFAULT NULL)";
            this.query(sql).execute();
        }
        catch (SQLException e) {
            Tools.Feedback(e, true, "\u521d\u59cb\u5316\u5931\u8d25!");
        }
        return false;
    }
    public boolean createValue(String name) {
        final String sql = "INSERT INTO player (name,amount) values (?,0);";
        try {
            final PreparedStatement pre = this.query(sql);
            pre.setString(1, name);
            if (pre.executeUpdate() == 0) {
                return false;
            }
        }
        catch (SQLException e) {
            Tools.Feedback(e, this.isShow, "\u521b\u5efa\u5931\u8d25!");
            return false;
        }
        return true;
    }
    
    public Map getValue(String playername) {
        final String sql = "SELECT * FROM player WHERE name = ?";
        ResultSet rs = null;
        final Map commandInfo = new HashMap();
        try {
            final PreparedStatement pre = this.query(sql);
            pre.setString(1, playername);
            rs = pre.executeQuery();
            while (rs.next()) {
                commandInfo.put("name", rs.getString("name"));
                commandInfo.put("amount", rs.getInt("amount"));
            }
        }
        catch (SQLException e) {
            Tools.Feedback(e, this.isShow, "\u83b7\u53d6\u5931\u8d25!");
            return null;
        }
        return commandInfo;
    }
    
   public boolean removeValue(String playername) {
        final String sql = "DELETE FROM player WHERE name = ?";
        try {
            final PreparedStatement pre = this.query(sql);
            pre.setString(1, playername);
            if (pre.executeUpdate() == 0) {
                return false;
            }
        }
        catch (SQLException e) {
            Tools.Feedback(e, this.isShow, "\u5220\u9664\u5931\u8d25");
        }
        return true;
    }
    
   public boolean addNumber(String playername,int amount) {
       final String sql = "UPDATE player SET amount=amount+? WHERE name = ?";
       try {
           final PreparedStatement pre = this.query(sql);
           pre.setInt(1, amount);
           pre.setString(2,playername);
           if (pre.executeUpdate() == 0) {
               return false;
           }
       }
       catch (SQLException e) {
       	e.printStackTrace();
           Tools.Feedback(e, this.isShow, "\u8bbe\u7f6e\u5ba0\u7269\u7b49\u7ea7\u5931\u8d25");
       }
       return true;
   }
   public boolean setNumber(String playername,int amount) {
       final String sql = "UPDATE player SET amount=? WHERE name = ?";
       try {
           final PreparedStatement pre = this.query(sql);
           pre.setInt(1, amount);
           pre.setString(2,playername);
           if (pre.executeUpdate() == 0) {
               return false;
           }
       }
       catch (SQLException e) {
       	e.printStackTrace();
           Tools.Feedback(e, this.isShow, "\u8bbe\u7f6e\u5ba0\u7269\u7b49\u7ea7\u5931\u8d25");
       }
       return true;
   }
   public boolean subNumber(String playername,int amount) {
       final String sql = "UPDATE player SET amount=amount-? WHERE name = ?";
       try {
           final PreparedStatement pre = this.query(sql);
           pre.setInt(1, amount);
           pre.setString(2,playername);
           if (pre.executeUpdate() == 0) {
               return false;
           }
       }
       catch (SQLException e) {
       	e.printStackTrace();
           Tools.Feedback(e, this.isShow, "\u8bbe\u7f6e\u5ba0\u7269\u7b49\u7ea7\u5931\u8d25");
       }
       return true;
   }
}
