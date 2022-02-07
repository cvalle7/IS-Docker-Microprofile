/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frontendf1.models.imp;

import com.mycompany.frontendf1.models.DaoEscuderias;
import com.mycompany.frontendf1.models.beans.Escuderia;
import com.mycompany.frontendf1.models.beans.EscuderiaParams;
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

public class ImpDaoEscuderia implements DaoEscuderias {

    @Inject
    @RestClient
    private BackendService service;

    @Override
    public List<Escuderia> selectAllTeams() throws F1Exception {
        return service.selectTeams();
    }

    @Override
    public Escuderia selectTeams(String marca) throws F1Exception {
        return service.selectTeam(marca);
    }

    @Override
    public Escuderia insertTeam(String marca, String ref, String id) throws F1Exception {
        EscuderiaParams escuderia = new EscuderiaParams();
        escuderia.setId(id);
        escuderia.setMarca(marca);
        escuderia.setRef(ref);
        
        return service.insertTeam(escuderia);
    }

    @Override
    public Response deleteTeam(String marca) throws F1Exception {
        return service.deleteTeam(marca);
    }

}
