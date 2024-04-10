package org.example.dao;

import java.sql.Connection;

import org.example.model.Departement;
import org.example.model.Role;
import org.example.model.Salarie;
import org.example.util.ConnexionDB;
import org.example.util.SalarieTableModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalarieDAO {

    private Connection connection;

    private PreparedStatement prepStat;

    public int addSalarie(Salarie salarie) {

        connection = ConnexionDB.getConnection();

        try {
            prepStat = connection.prepareStatement("INSERT INTO `salarie` (`firstname`,`lastname`,`role`,`departement`) values (?,?,?,?)");
            prepStat.setString(1,salarie.getFirstname());
            prepStat.setString(2,salarie.getLastname());
            prepStat.setString(3,salarie.getRole().toString());
            prepStat.setInt(4,salarie.getDeparementId());

            return prepStat.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int rmSalarieById(int idSalarie) {
        connection = ConnexionDB.getConnection();

        try {
            prepStat = connection.prepareStatement("DELETE FROM `salarie` WHERE id_sal = ? ");
            prepStat.setInt(1,idSalarie);

            return prepStat.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Salarie getSalarieById(int idSalarie) {
        connection = ConnexionDB.getConnection();

        try {
            prepStat = connection.prepareStatement("SELECT `id_sal`, `firstname`, `lastname`, `role`, `id_dept`, `name` FROM salarie INNER JOIN departement ON salarie.departement_id = departement.id_dept WHERE id_sal = ? ");
            prepStat.setInt(1,idSalarie);

            ResultSet result = prepStat.executeQuery();

            if (result.next()) {
                Departement dept = new Departement();
                dept.setId(result.getInt("id_dept"));
                dept.setName(result.getString("name"));

                Salarie ret = new Salarie();

                ret.setId(result.getInt("id"));
                ret.setDepartement(dept);
                ret.setFirstname(result.getString("firstname"));
                ret.setLastname(result.getString("lastname"));
                ret.setRole(Role.valueOf(result.getString("role")));

                return ret;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public int updSalarie(Salarie salarie) {
        connection = ConnexionDB.getConnection();

        try {
            prepStat = connection.prepareStatement("UPDATE `salarie` SET `firstname` = ? , `lastname` = ?, `role` = ?, `deparement` = ? WHERE id_sale = ?");
            prepStat.setString(1,salarie.getFirstname());
            prepStat.setString(2, salarie.getLastname());
            prepStat.setString(3,salarie.getRole().toString());
            prepStat.setInt(4,salarie.getDeparementId());
            prepStat.setInt(5,salarie.getId());

            return prepStat.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Salarie> getAllSalaries(){
        connection = ConnexionDB.getConnection();
        List<Salarie> ret = new ArrayList<>();

        try {
            ResultSet result = connection.createStatement().executeQuery("SELECT `id_sal`, `firstname`, `lastname`, `role`, `id_dept`, `name` FROM salarie INNER JOIN departement ON salarie.departement_id = departement.id_dept ");

            while (result.next()) {
                Departement dept = new Departement();
                dept.setId(result.getInt("id_dept"));
                dept.setName(result.getString("name"));

                Salarie sala = new Salarie();

                sala.setId(result.getInt("id"));
                sala.setDepartement(dept);
                sala.setFirstname(result.getString("firstname"));
                sala.setLastname(result.getString("lastname"));
                sala.setRole(Role.valueOf(result.getString("role")));

                ret.add(sala);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ret;

    }



}
