/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backendf1.model.imp;

import com.mycompany.backendf1.model.DaoPilotos;
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
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

/**
 *
 * @author Carlos
 */
@ApplicationScoped
public class ImpDaoPilotos implements DaoPilotos {

    @Resource(name = "jdbc/database")
    private DataSource dataSource;

    @Override
    public List<Piloto> selectAllPilots() throws F1Exception {

        final String SQL_SELECT = " SELECT REF, NOMBRE, APELLIDOS, TITULOS,  MARCA "
                + " FROM PILOTOS ";

        List<Piloto> pilotos = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_SELECT)) {
            if (resultSet.next()) {
                do {
                    Piloto piloto = new Piloto();

                    piloto.setRef(resultSet.getString(1));
                    piloto.setNombre(resultSet.getString(2));
                    piloto.setApellidos(resultSet.getString(3));
                    piloto.setTitulos(resultSet.getString(4));
                    piloto.setMarca(resultSet.getString(5));
                    pilotos.add(piloto);

                } while (resultSet.next());

                return pilotos;
            }

            return pilotos;

        } catch (SQLException sqlException) {
            throw new F1Exception(sqlException);
        }

    }

    @Override
    public Optional<Piloto> selectAllPilot(String ref) throws F1Exception {
        final String SQL = "SELECT REF, MARCA, NOMBRE , APELLIDOS, TITULOS "
                + "  FROM PILOTOS                               "
                + " WHERE REF = ?                      ";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, ref);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Piloto piloto = new Piloto();
                    piloto.setRef(rs.getString(1));
                    piloto.setMarca(rs.getString(2));
                    piloto.setNombre(rs.getString(3));
                    piloto.setApellidos(rs.getString(4));
                    piloto.setTitulos(rs.getString(5));

                    return Optional.of(piloto);
                } else {
                    return Optional.empty();
                }
            }

        } catch (SQLException sqlException) {
            throw new F1Exception(sqlException);
        }
    }

    @Override
    public Piloto insertPilot(Piloto piloto) throws F1Exception {
        final String SQL = "INSERT INTO PILOTOS(REF, MARCA, NOMBRE, APELLIDOS, TITULOS) VALUES(?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(SQL)) {

            ps.setString(1, piloto.getRef());
            ps.setString(2, piloto.getMarca());
            ps.setString(3, piloto.getNombre());
            ps.setString(4, piloto.getApellidos());
            ps.setString(5, piloto.getTitulos());
            ps.executeUpdate();

            return piloto;

        } catch (SQLException ex) {
            throw new F1Exception(ex);
        }
    }

    @Override
    public Optional<Piloto> updatePilot(String ref, String nombre, String apellidos, String titulos) throws F1Exception {

        final String SQL = "UPDATE PILOTOS SET NOMBRE = ?, APELLIDOS = ?, TITULOS = ?"
                + "WHERE REF = ? ";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(SQL)) {
            
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, titulos);
            ps.setString(4, ref);

            int comp = ps.executeUpdate();

            if (comp != 0) {
                
                return selectAllPilot(ref);

            }

            return Optional.empty();

        } catch (SQLException ex) {

            throw new F1Exception(ex);

        }
    }

    @Override
    public boolean deletePilot(String ref) throws F1Exception {
        final String SQL_DELETE = "DELETE PILOTOS       "
                + " WHERE REF = ? ";
        final String deleteFromEscuderia = " DELETE ESCUDERIAS WHERE REF = ? ";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(deleteFromEscuderia)) {
            ps.setString(1, ref);
            ps.executeUpdate();

            try (PreparedStatement ps2 = connection.prepareStatement(SQL_DELETE)) {
                ps2.setString(1, ref);
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
