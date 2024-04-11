package org.example.dao;

import org.example.model.Departement;
import org.example.model.Role;
import org.example.model.Salarie;
import org.example.util.ConnexionDB;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartementDAO {

    private Connection connection;
    private PreparedStatement prepStat;

    public int addDept(Departement departement) {
        connection = ConnexionDB.getConnection();

        try {
            prepStat = connection.prepareStatement("INSERT INTO `departement` (`name`) values (?)");
            prepStat.setString(1,departement.getName());

            return prepStat.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int rmDepartementById(int idDept) {
        connection = ConnexionDB.getConnection();

        try {
            prepStat = connection.prepareStatement("DELETE FROM `departement` WHERE id_dept = ? ");
            prepStat.setInt(1,idDept);

            return prepStat.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Departement getDepartementById(int idDept) {
        connection = ConnexionDB.getConnection();
        Departement ret = new Departement();

        try {
            prepStat = connection.prepareStatement("SELECT `id_sal`, `firstname`, `lastname`, `role`, `id_dept`, `name` FROM salarie INNER JOIN departement ON salarie.departement_id = departement.id_dept WHERE id_dept = ? ");
            prepStat.setInt(1,idDept);

            ResultSet result = prepStat.executeQuery();

            if (result.next()) {
                if (ret.getSalaries() == null) {
                    ret.setId(result.getInt("id_dept"));
                    ret.setName(result.getString("name"));
                    ret.setSalaries(new ArrayList<>());
                }

                Salarie sala = new Salarie();

                sala.setId(result.getInt("id"));
                sala.setDepartement(ret);
                sala.setFirstname(result.getString("firstname"));
                sala.setLastname(result.getString("lastname"));
                sala.setRole(Role.valueOf(result.getString("role")));

                ret.getSalaries().add(sala);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }


    public int updateDepartement(Departement departement) {
        connection = ConnexionDB.getConnection();

        try {
            prepStat = connection.prepareStatement("UPDATE `departement` SET `name` = ? WHERE id_dept = ?");
            prepStat.setString(1,departement.getName());
            prepStat.setInt(2,departement.getId());

            return prepStat.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Departement> getAllDepartement() {
        connection = ConnexionDB.getConnection();
        List<Departement> ret = new ArrayList<>();

        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT d.name, d.id_dept, COUNT(s.id_sal) AS nb_salaries FROM departement d LEFT JOIN salarie s ON d.id_dept = s.departement_id GROUP BY d.name, d.id_dept");

            while (rs.next()) {
                Departement departement = new Departement();
                departement.setId(rs.getInt("id_dept"));
                departement.setName(rs.getString("name"));
                departement.setNbSalaries(rs.getInt("nb_salaries"));

                ret.add(departement);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ret;
    }


}
