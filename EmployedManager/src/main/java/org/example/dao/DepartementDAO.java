package org.example.dao;

import org.example.model.Departement;
import org.example.model.Role;
import org.example.model.Salarie;
import org.example.util.ConnexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                Departement dept = new Departement();
                dept.setId(result.getInt("id_dept"));
                dept.setName(result.getString("name"));

                Salarie ret = new Salarie();

                ret.setId(result.getInt("id"));
                ret.setDepartement(dept);
                ret.setFirstname(result.getString("firstname"));
                ret.setLastname(result.getString("lastname"));
                ret.setRole(Role.valueOf(result.getString("role")));


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;

    }



}
