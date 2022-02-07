/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backendf1.model;

import com.mycompany.backendf1.model.beans.Coche;
import com.mycompany.backendf1.model.exceptions.F1Exception;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Carlos
 */
public interface DaoCoches {
    public List<Coche> selectAllCars() throws F1Exception;
    public Optional<Coche> selectCar(String id) throws F1Exception;
    public Coche insertCar(Coche coche) throws F1Exception;
    public Optional<Coche> updateCar(String id, String modelo, String cv, String vmax) throws F1Exception;
    public boolean deleteCar(String id) throws F1Exception;
}
