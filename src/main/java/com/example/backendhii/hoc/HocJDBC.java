package com.example.backendhii.hoc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

class OOO {
    public Long id;
    public String name;

    public OOO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "OOO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

public class HocJDBC {
    public Connection getConnection() {
        final String url = "jdbc:mysql://localhost:3306/test";
        final String user = "root";
        final String password = "231020";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception ignored) {
        }
        return null;
    }

    public List<OOO> getAll() {
        List<OOO> list = new ArrayList<>();
        try {
            String sql = "select * from ooo";
            Connection conn = getConnection();
            PreparedStatement sta = conn.prepareStatement(sql);
            ResultSet rs = sta.executeQuery();
            while (rs.next()) {
                list.add(new OOO(rs.getLong(1), rs.getString(2)));
            }
            rs.close();
            sta.close();
            conn.close();
        } catch (Exception ignored) {
        }
        return list;
    }
    public void addOOO(long id, String name) {
        String sql = "INSERT INTO `test`.`ooo` (`id`, `name`) VALUES (?, ?);";
        try {
            Connection conn = getConnection();
            PreparedStatement sta = conn.prepareStatement(sql);
            sta.setLong(1, id);
            sta.setString(2, name);
            int rs2 = sta.executeUpdate();
            sta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editOOO(long id, String name) {
        String sql = "UPDATE `test`.`ooo` SET `name` = ? WHERE (`id` = ?);";
        try {
            Connection conn = getConnection();
            PreparedStatement sta = conn.prepareStatement(sql);
            sta.setLong(2, id);
            sta.setString(1, name);
            int rs2 = sta.executeUpdate();
            sta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HocJDBC hocJDBC = new HocJDBC();
//        if (hocJDBC.getConnection() != null) {
//            System.out.println("OK");
//        } else {
//            System.out.println("Fail");
//        }
//        hocJDBC.addOOO(3,"wwwwwwwwwwwwwww");
        hocJDBC.editOOO(3,"qqqqqqqqqqqqqqqqqqq");
        for (OOO ooo : hocJDBC.getAll()) {
            System.out.println(ooo);
        }
    }
}
