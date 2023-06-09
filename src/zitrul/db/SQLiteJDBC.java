package zitrul.db;

import zitrul.domain.UserInfo;

import java.sql.*;

public class SQLiteJDBC {
    public static void Create() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:login.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE AUTH " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " LOGIN           TEXT    NOT NULL, " +
                    " PASSWORD          TEXT    NOT NULL," +
                    " IMG                TEXT  NOT NULL," +
                    " ST1                TEXT  NOT NULL," +
                    " ST2              TEXT   NOT NULL) ";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public static String write(int id, String login, String password, String img, String st1, String st2) {
        Connection c = null;
        PreparedStatement stmt = null;
        try {


            /*PreparedStatement stmt1;
            Connection conn = DriverManager.getConnection("jdbc:sqlite:login.db");
            stmt = null;
            String sql = "SELECT * FROM AUTH WHERE LOGIN = '" + login + "';";
            stmt1= conn.prepareStatement(sql);
            ResultSet rs1 = stmt1.executeQuery(sql);

            while (rs1.next()) {
                return "LOGIN ALlREADY IN USE";
            }


            stmt1.close();
            conn.close();
        */

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:login.db");
            System.out.println("Opened database successfully");

            String sql = "INSERT INTO AUTH (ID,LOGIN,PASSWORD,IMG,ST1,ST2) " +
                    "VALUES (?,?,?,?,?,?)";
            ;
            stmt = c.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, login);
            stmt.setString(3, password);
            stmt.setString(4, img);
            stmt.setString(5, st1);
            stmt.setString(6,st2);
            stmt.executeUpdate();
            stmt.close();
            c.close();
            System.out.println(login);
            System.out.println(password);
            return "OK";
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
        return "ERROR";
    }

    public static ResultSet exec(String command) {
        Connection c = null;
        Statement stmt = null;
        ResultSet r1 = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:login.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = command;
            ResultSet rs = stmt.executeQuery(sql);

            r1 = rs;
            rs.close();
            stmt.close();
            c.close();
            return r1;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return r1;
    }

    public static String check_password(String login) {
        Connection c = null;
        Statement stmt = null;
        String password = "";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:login.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "SELECT * FROM AUTH WHERE LOGIN = '" + login + "';";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("LOGIN");
                password = rs.getString("PASSWORD");

                break;
            }
            rs.close();
            stmt.close();
            c.close();
            return password;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return "";
    }

    public static Integer getid(String login) {
        Connection c = null;
        Statement stmt = null;
        String password = "";
        int id = -1;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:login.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "SELECT * FROM AUTH WHERE LOGIN = '" + login + "';";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                id = rs.getInt("ID");
                String name = rs.getString("LOGIN");
                password = rs.getString("PASSWORD");

                break;
            }
            rs.close();
            stmt.close();
            c.close();
            return id;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return -1;
    }
    public static UserInfo getUserInfo(int id){
        Connection c = null;
        Statement stmt = null;
        String password = "";

        UserInfo ui = new UserInfo("","","","");
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:login.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "SELECT * FROM AUTH WHERE ID = '" + id + "';";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                id = rs.getInt("ID");
                String name = rs.getString("LOGIN");
                String tg = rs.getString("TG");
                String email = rs.getString("EMAIL");
                String phone = rs.getString("PHONE");
                ui = new UserInfo(name,tg,email,phone);

                break;
            }
            rs.close();
            stmt.close();
            c.close();
            return ui;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return ui;

    }
    public static int get_lastid(){
        Connection c = null;
        Statement stmt = null;
        int id = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:login.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "SELECT MAX(ID) FROM AUTH";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                id = rs.getInt(1);
                break;
            }
            System.out.println(id);
            rs.close();
            stmt.close();
            c.close();
            return id;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return 0;
    }
}