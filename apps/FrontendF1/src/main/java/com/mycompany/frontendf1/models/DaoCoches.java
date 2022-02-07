/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frontendf1.models;

import com.mycompany.frontendf1.models.beans.Coche;
import com.mycompany.frontendf1.models.exceptions.F1Exception;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.core.Response;

/**
 *
 * @author Carlos
 */
public interface DaoCoches {
    public List<Coche> selectAllCars() throws F1Exception;
    public Coche selectCar(String id) throws F1Exception;
    public Coche insertCar(Coche coche) throws F1Exception;
    public Coche updateCar(String id, String modelo, String cv, String vmax) throws F1Exception;
    public Response deleteCar(String id) throws F1Exception;
}
