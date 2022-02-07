/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frontendf1.models.imp;

import com.mycompany.frontendf1.models.DaoCoches;
import com.mycompany.frontendf1.models.beans.Coche;
import com.mycompany.frontendf1.models.exceptions.F1Exception;
import com.mycompany.frontendf1.models.services.BackendService;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author Carlos
 */
@ApplicationScoped
public class ImpDaoCoche implements DaoCoches{

    
    @Inject
    @RestClient
    private BackendService service;
    
    @Override
    public List<Coche> selectAllCars() throws F1Exception {
        return service.selectCoches();
    }

    @Override
    public Coche selectCar(String id) throws F1Exception {
        return service.seleCoche(id);
    }

    @Override
    public Coche insertCar(Coche coche) throws F1Exception {
        return service.insertCoche(coche);
    }

    @Override
    public Coche updateCar(String id, String modelo, String cv, String vmax) throws F1Exception {
        Coche coche = new Coche(id, null, modelo, cv, vmax);
        return service.updateCoche(id, coche);
    }

    @Override
    public Response deleteCar(String id) throws F1Exception {
        return service.deleteCar(id);
    }
    
}
