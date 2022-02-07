/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frontendf1.models.imp;

import com.mycompany.frontendf1.models.DaoPilotos;
import com.mycompany.frontendf1.models.beans.Piloto;
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
public class ImpDaoPiloto implements DaoPilotos{

    @Inject
    @RestClient
    private BackendService service;
    
    @Override
    public List<Piloto> selectAllPilots() throws F1Exception {
        return service.selectPilotos();
    }

    @Override
    public Piloto selectAllPilot(String ref) throws F1Exception {
        return service.selectPiloto(ref);
    }

    @Override
    public Piloto insertPilot(Piloto piloto) throws F1Exception {
        return service.insertPiloto(piloto);
    }

    @Override
    public Piloto updatePilot(String ref, String nombre, String apellidos, String titulos) throws F1Exception {
        Piloto piloto = new Piloto(ref, nombre, apellidos, titulos, null);
        return service.updatePiloto(ref, piloto);
    }

    @Override
    public Response deletePilot(String ref) throws F1Exception {
        return service.deletePilot(ref);
    }
    
}
