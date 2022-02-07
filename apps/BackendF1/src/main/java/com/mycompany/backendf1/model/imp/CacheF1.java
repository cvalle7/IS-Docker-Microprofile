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
import com.mycompany.backendf1.model.exceptions.F1Exception;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Carlos
 */
@ApplicationScoped
public class CacheF1 implements DaoCoches, DaoPilotos{

    private DaoCoches daoCoche;
    private DaoPilotos daoPiloto;
    
    private HashMap<String, Piloto> listaPilotos = new HashMap<>();
    private HashMap<String, Coche> listaCoches = new HashMap<>();

    private Logger logger = Logger.getLogger(CacheF1.class.getName());

    public CacheF1(DaoCoches daoCoche, DaoPilotos daoPiloto) {
        this.daoCoche = daoCoche;
        this.daoPiloto = daoPiloto;
        logger.info("CacheF1()");
    }

    @Override
    public List<Coche> selectAllCars() throws F1Exception {
        return daoCoche.selectAllCars();
    }

    @Override
    public Optional<Coche> selectCar(String id) throws F1Exception {
        if (listaCoches.containsKey(id)) {
            logger.log(Level.INFO, "CacheCoches(): Cache HIT");
            return Optional.of(listaCoches.get(id));
        } else {
            logger.log(Level.INFO, "CacheCoches(): Cache FAULT");
            Optional<Coche> coche = daoCoche.selectCar(id);

            if (coche.isPresent()) {
                listaCoches.put(id, coche.get());
            }

            return coche;

        }
    }

    @Override
    public Coche insertCar(Coche coche) throws F1Exception {
        if (listaCoches.containsKey(coche.getId())) {
            logger.log(Level.INFO, "CacheCoches(): Cache HIT");
            throw new F1Exception("Piloto existente");
        }

        Coche car = daoCoche.insertCar(coche);
        listaCoches.put(coche.getId(), car);

        return car;
    }

    @Override
    public Optional<Coche> updateCar(String id, String modelo, String cv, String vmax) throws F1Exception {
        Optional<Coche> coche = daoCoche.updateCar(id, modelo, cv, vmax);

        if (listaCoches.containsKey(id)) {
            logger.log(Level.INFO, "CacheCoches(): Cache HIT");

            if (coche.isPresent()) {
                listaCoches.replace(id, coche.get());
            }

        } else {
            logger.log(Level.INFO, "CacheCoches(): Cache FAULT");
            if (coche.isPresent()) {
                listaCoches.put(id, coche.get());
            }
        }


        return coche;
    }

    @Override
    public boolean deleteCar(String id) throws F1Exception {

        if (listaCoches.containsKey(id)) {
            listaCoches.remove(id);
            return daoCoche.deleteCar(id);
        } else {
            return daoCoche.deleteCar(id);
        }
    }

    @Override
    public List<Piloto> selectAllPilots() throws F1Exception {
        return daoPiloto.selectAllPilots();
    }

    @Override
    public Optional<Piloto> selectAllPilot(String ref) throws F1Exception {

        if (listaPilotos.containsKey(ref)) {
            logger.log(Level.INFO, "CachePilotos(): Cache HIT");
            return Optional.of(listaPilotos.get(ref));
        } else {
            logger.log(Level.INFO, "CachePilotos(): Cache FAULT");
            Optional<Piloto> piloto = daoPiloto.selectAllPilot(ref);

            if (piloto.isPresent()) {
                listaPilotos.put(ref, piloto.get());
            }

            return piloto;

        }

    }

    @Override
    public Piloto insertPilot(Piloto piloto) throws F1Exception {

        if (listaPilotos.containsKey(piloto.getRef())) {
            logger.log(Level.INFO, "CachePilotos(): Cache HIT");
            throw new F1Exception("Piloto existente");
        }

        Piloto pilot = daoPiloto.insertPilot(piloto);
        listaPilotos.put(piloto.getRef(), pilot);

        return pilot;

    }

    @Override
    public Optional<Piloto> updatePilot(String ref, String nombre, String apellidos, String titulos) throws F1Exception {

        Optional<Piloto> piloto = daoPiloto.updatePilot(ref, nombre, apellidos, titulos);

        if (listaPilotos.containsKey(ref)) {
            logger.log(Level.INFO, "CachePilotos(): Cache HIT");
            if (piloto.isPresent()) {
                listaPilotos.replace(ref, piloto.get());

            }

        } else {
            logger.log(Level.INFO, "CachePilotos(): Cache FAULT");
            if (piloto.isPresent()) {
                listaPilotos.put(ref, piloto.get());
            }
        }

        return piloto;

    }

    @Override
    public boolean deletePilot(String ref) throws F1Exception {


        if (listaPilotos.containsKey(ref)) {
            listaPilotos.remove(ref);
            return daoPiloto.deletePilot(ref);
        } else {
            return daoPiloto.deletePilot(ref);
        }
    }


}
