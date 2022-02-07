/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backendf1.model.imp;

import com.mycompany.backendf1.model.DaoCoches;
import com.mycompany.backendf1.model.beans.Coche;
import com.mycompany.backendf1.model.beans.Piloto;
import com.mycompany.backendf1.model.exceptions.F1Exception;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;

/**
 *
 * @author Carlos
 */
@ApplicationScoped
public class ImpDaoCoches implements DaoCoches {

    @Resource(name = "jdbc/database")
    private DataSource dataSource;

    @Override
    public List<Coche> selectAllCars() throws F1Exception {

        final String sql = " SELECT ID, MARCA, MODELO, CV, VMAX FROM COCHES";

        List<Coche> listaCoche = new ArrayList<>();

        try (Connection conexion = dataSource.getConnection();
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {

                do {

                    listaCoche.add(new Coche(rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5)
                    ));

                } while (rs.next());

            }

        } catch (SQLException ex) {
            throw new F1Exception(ex);
        }

        return listaCoche;
    }

    @Override
    public Optional<Coche> selectCar(String ref) throws F1Exception {

        final String sql = " SELECT ID, MARCA, MODELO, CV, VMAX FROM COCHES WHERE ID = ? ";

        try (Connection conexion = dataSource.getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, ref);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return Optional.of(new Coche(rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5)
                    ));

                }

                return Optional.empty();

            }

        } catch (SQLException ex) {
            throw new F1Exception(ex);
        }

    }

    @Override
    public Coche insertCar(Coche coche) throws F1Exception {
        final String SQL = "INSERT INTO COCHES(ID, MARCA, MODELO, CV, VMAX) VALUES(SEQ_COCHES.NEXTVAL, ?, ?, ?, ?)";
        String[] columna = new String[]{"ID"};
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(SQL, columna)) {

            ps.setString(1, coche.getMarca());
            ps.setString(2, coche.getModelo());
            ps.setString(3, coche.getCv());
            ps.setString(4, coche.getvMax());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                coche.setId(rs.getString(1));
                return coche;
            }

        } catch (SQLException ex) {
            throw new F1Exception(ex);
        }
    }

    @Override
    public Optional<Coche> updateCar(String id, String modelo, String cv, String vmax) throws F1Exception {
        final String SQL = "UPDATE COCHES SET MODELO = ?, CV = ?, VMAX = ?"
                + "WHERE ID = ? ";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(SQL)) {
            
            ps.setString(1, modelo);
            ps.setString(2, cv);
            ps.setString(3, vmax);
            ps.setString(4, id);

            int comp = ps.executeUpdate();

            if (comp != 0) {

                return selectCar(id);

            }

            return Optional.empty();

        } catch (SQLException ex) {

            throw new F1Exception(ex);

        }
    }

    @Override
    public boolean deleteCar(String id) throws F1Exception {
        
         String SQL_DELETE = "DELETE COCHES WHERE ID = ? ";

         String deleteFromEscuderia = " DELETE ESCUDERIAS WHERE ID = ? ";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(deleteFromEscuderia)) {
            ps.setString(1, id);
            ps.executeUpdate();

            try (PreparedStatement ps2 = connection.prepareStatement(SQL_DELETE)) {
                ps2.setString(1, id);
                int count = ps2.executeUpdate();
                if (count == 0) {
                    return false;
                }
                return true;
            }
        } catch (SQLException sqlException) {
            throw new F1Exception(sqlException);
        }
    }

}
