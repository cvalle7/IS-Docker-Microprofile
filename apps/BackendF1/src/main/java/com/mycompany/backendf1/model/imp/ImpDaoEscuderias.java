/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backendf1.model.imp;

import com.mycompany.backendf1.model.DaoCoches;
import com.mycompany.backendf1.model.DaoEscuderia;
import com.mycompany.backendf1.model.DaoPilotos;
import com.mycompany.backendf1.model.beans.Coche;
import com.mycompany.backendf1.model.beans.Escuderia;
import com.mycompany.backendf1.model.beans.Piloto;
import com.mycompany.backendf1.model.exceptions.DistintaMarcaException;
import com.mycompany.backendf1.model.exceptions.F1Exception;
import com.mycompany.backendf1.model.exceptions.ValoresNoExistentesException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
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
public class ImpDaoEscuderias implements DaoEscuderia {

    @Resource(name = "jdbc/database")
    private DataSource dataSource;

    @Inject
    private DaoCoches daoCoches;
    @Inject
    private DaoPilotos daoPilotos;

    @Override
    public HashMap<String, Escuderia> selectAllTeams() throws F1Exception {

        final String sql = " SELECT MARCA, REF, ID FROM ESCUDERIAS ";

        HashMap<String, Escuderia> listaEscuderia = new HashMap<>();

        try (Connection conexion = dataSource.getConnection();
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {

                do {
                    String marca = rs.getString(1);
                    Piloto p = daoPilotos.selectAllPilot(rs.getString(2)).get();
                    Coche c = daoCoches.selectCar(rs.getString(3)).get();

                    if (listaEscuderia.containsKey(marca)) {
                        Escuderia escuderia = listaEscuderia.get(marca);
                        escuderia.addCoches(c);
                        escuderia.addPilotos(p);
                        listaEscuderia.replace(marca, escuderia);
                    } else {

                        List<Piloto> pilotos = new ArrayList();
                        List<Coche> coches = new ArrayList();

                        pilotos.add(p);
                        coches.add(c);

                        Escuderia escuderia = new Escuderia();
                        escuderia.setMarca(marca);
                        escuderia.setPilotos(pilotos);
                        escuderia.setCoches(coches);

                        listaEscuderia.put(marca, escuderia);
                    }

                } while (rs.next());

                return listaEscuderia;

            }

            return listaEscuderia;

        } catch (SQLException ex) {
            throw new F1Exception(ex);
        }

    }

    @Override
    public Optional<HashMap<String, Escuderia>> selectTeams(String marca) throws F1Exception {

        final String sql = " SELECT MARCA, REF, ID FROM ESCUDERIAS WHERE MARCA = ? ";

        HashMap<String, Escuderia> listaEscuderia = new HashMap<>();

        try (Connection conexion = dataSource.getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, marca);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    do {
                        String marc = rs.getString(1);
                        Piloto p = daoPilotos.selectAllPilot(rs.getString(2)).get();
                        Coche c = daoCoches.selectCar(rs.getString(3)).get();

                        if (listaEscuderia.containsKey(marc)) {
                            Escuderia escuderia = listaEscuderia.get(marc);
                            escuderia.addCoches(c);
                            escuderia.addPilotos(p);
                            listaEscuderia.replace(marc, escuderia);
                        } else {

                            List<Piloto> pilotos = new ArrayList();
                            List<Coche> coches = new ArrayList();

                            pilotos.add(p);
                            coches.add(c);

                            Escuderia escuderia = new Escuderia();
                            escuderia.setMarca(marca);
                            escuderia.setPilotos(pilotos);
                            escuderia.setCoches(coches);
                            listaEscuderia.put(marc, escuderia);
                        }

                    } while (rs.next());

                    return Optional.of(listaEscuderia);

                }

                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new F1Exception(ex);
        }
    }

    @Override
    public HashMap<String, Escuderia> insertTeam(String marca, String ref, String id) throws F1Exception {
        final String sql = " INSERT INTO ESCUDERIAS(MARCA, REF, ID) "
                + "            VALUES(?, ?, ?) ";

        Optional<Coche> coche = daoCoches.selectCar(id);
        Optional<Piloto> piloto = daoPilotos.selectAllPilot(ref);

        if (coche.isPresent() && piloto.isPresent()) {

            String cMarca = coche.get().getMarca();
            String pMArca = piloto.get().getMarca();

            if (cMarca.equals(marca) && pMArca.equals(marca)) {

                try (Connection conexion = dataSource.getConnection();
                        PreparedStatement ps = conexion.prepareStatement(sql)) {

                    ps.setString(1, marca);
                    ps.setString(2, ref);
                    ps.setString(3, id);
                    ps.executeUpdate();

                    return selectTeams(marca).get();

                } catch (SQLException ex) {
                    throw new F1Exception(ex);
                }

            } else {
                throw new DistintaMarcaException();
            }

        } else {
            throw new ValoresNoExistentesException();
        }

    }

    @Override
    public boolean deleteTeam(String marca) throws F1Exception {

        final String sql = " DELETE ESCUDERIAS WHERE MARCA = ? ";

        try (Connection conexion = dataSource.getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, marca);
            int i = ps.executeUpdate();

            if (i == 0) {
                return false;
            }

            return true;

        } catch (SQLException ex) {
            throw new F1Exception(ex);
        }

    }
}
