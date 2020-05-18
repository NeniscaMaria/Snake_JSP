package mvcIntelliJIdea.model;

import java.sql.*;
import java.time.LocalTime;

public class DBManager {
    private Statement stmt;
    private Connection con;
    public DBManager() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost/snakedatabase", "root", "");
            stmt = con.createStatement();
        } catch(Exception ex) {
            System.out.println("eroare la connect:"+ex.getMessage());
            ex.printStackTrace();
        }
    }

    public String authenticate(String username, String password) {
        ResultSet rs;
        String result = "error";
        System.out.println(username+" "+password);
        try {
            rs = stmt.executeQuery("select * from users where username='"+username+"' and password='"+password+"'");
            if (rs.next()) {
                logTimeStart(rs.getInt("UID"));
                result = "success";
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void logTimeStart(int uid) {
        LocalTime start = LocalTime.now();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("select TID from timelog where UID='"+uid+"'");
            if(rs.next()){ //this user is already logged in
                String query = "update timelog set start=?, end=? where UID=?";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setTime   (1, Time.valueOf(start));
                preparedStmt.setTime(2, Time.valueOf(start));
                preparedStmt.setInt(3,uid);
                preparedStmt.executeUpdate();
            }
            else{
                String query = "insert into timelog (UID,start,end) values (?,?,?)";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setTime(3, Time.valueOf(start));
                preparedStmt.setTime(2, Time.valueOf(start));
                preparedStmt.setInt(1,uid);
                preparedStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void logTimeEnd(User user) {
        ResultSet rs;
        try {
            rs = stmt.executeQuery("select * from users where username='" + user.getUsername() + "' and password='" + user.getPassword() + "'");
            if (rs.next()) {
                int uid = rs.getInt("UID");
                LocalTime end = LocalTime.now();
                String query = "update timelog set end=? where UID=?";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setTime(1, Time.valueOf(end));
                preparedStmt.setInt(2, uid);
                preparedStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addMoveToUser(User user, int move){
        ResultSet rs;
        try {
            rs = stmt.executeQuery("select * from users where username='" + user.getUsername() + "' and password='" + user.getPassword() + "'");
            if (rs.next()) {
                int uid = rs.getInt("UID");
                String query = "insert into usermoves (UID,move) values (?,?)";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setInt(2, move);
                preparedStmt.setInt(1, uid);
                preparedStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}