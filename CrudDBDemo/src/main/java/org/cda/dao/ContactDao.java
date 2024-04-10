package org.cda.dao;

import org.cda.connexion.ConnectionUtil;
import org.cda.model.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {

    private Connection con;

    private PreparedStatement ps;

    public int addContact(Contact contact) {

        con = ConnectionUtil.getConnection();

        try {
            ps = con.prepareStatement("INSERT INTO `contact` (`name`,`number`) values (?,?)");
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getNumber());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer[] getUsersIds() {

        con = ConnectionUtil.getConnection();

        List<Integer> retList = new ArrayList<>();

        try {
           ResultSet rs = con.createStatement().executeQuery("SELECT `id` FROM `contact`");

           while (rs.next()){
               int nextId = rs.getInt("id");
               retList.add(nextId);
           }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return retList.toArray(new Integer[0]);
    }

    public int rmUserById(int id) {
        con = ConnectionUtil.getConnection();

        try {
            //ps = con.prepareStatement("DELETE FROM `contact` WHERE id = ?");
            ps=con.prepareStatement("Delete From `contact` where id=?");
            ps.setInt(1, id);
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Contact getContactById(int id) {
        con = ConnectionUtil.getConnection();
        Contact ret = new Contact();

        try {
            ps=con.prepareStatement("SELECT `name`,`number` FROM `contact` WHERE `id` = ? ");
            ps.setInt(1,id);

            ResultSet result = ps.executeQuery();
            if (result.next()) {
                ret.setName(result.getString("name"));
                ret.setNumber(result.getString("number"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ret;

    }


    public int updContact(Contact updContact) {

        con = ConnectionUtil.getConnection();

        try {
            ps = con.prepareStatement("UPDATE `contact` SET `name` = ? , `number` = ? WHERE `id` = ?");
            ps.setString(1, updContact.getName());
            ps.setString(2, updContact.getNumber());
            ps.setInt(3,updContact.getId());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Contact> getAllContact() {
        con = ConnectionUtil.getConnection();
        List<Contact> ret = new ArrayList<>();

        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT `id`, `name`,`number` FROM `contact`");

            while (rs.next()) {
                Contact nxtCntct = new Contact();
                nxtCntct.setId(rs.getInt("id"));
                nxtCntct.setName(rs.getString("name"));
                nxtCntct.setNumber(rs.getString("number"));

                ret.add(nxtCntct);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ret;
    }
}
