/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backendf1.resources;

import com.mycompany.backendf1.model.DaoEscuderia;
import com.mycompany.backendf1.model.beans.Escuderia;
import com.mycompany.backendf1.model.cdi.Cache;
import com.mycompany.backendf1.model.exceptions.F1Exception;
import com.mycompany.backendf1.model.validate.EscuderiaValidate;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Carlos
 */
@Path("escuderias")
public class EscuderiasResource {

    @Inject
    private DaoEscuderia dao;
    
    @Inject
    private Validator validator;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectTeams() throws F1Exception {

        return Response.ok()
                .entity(dao.selectAllTeams().values())
                .build();

    }

    @GET
    @Path("{marca}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectTeam(@PathParam("marca") String marca) throws F1Exception {

        Optional<HashMap<String, Escuderia>> opt = dao.selectTeams(marca);

        return opt.map(escuderia -> Response.ok()
                                            .entity(escuderia.get(marca))
                                            .build())
                  .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)
                                                           .build());
                

    }

    @POST
    @Path("insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertTeam(EscuderiaValidate escuderia) throws F1Exception {
        
        
        Set<ConstraintViolation<EscuderiaValidate>> validate = validator.validate(escuderia);
        if(!validate.isEmpty()){
            JsonObjectBuilder job = Json.createObjectBuilder();
            for (ConstraintViolation<EscuderiaValidate> constraintViolation : validate) {
                
                job.add(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
                
            }
            
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(job.build())
                           .build();
        }
        
        return Response.ok()
                .entity(dao.insertTeam(escuderia.getMarca(), 
                                       escuderia.getRef(), 
                                       escuderia.getId())
                           .get(escuderia.getMarca()))
                .build();

    }

    @DELETE
    @Path("delete/{escuderia}")
    public Response deleteTeam(@PathParam("escuderia") String escuderia) throws F1Exception {

        if (dao.deleteTeam(escuderia)) {
            return Response.ok()
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND)
                .build();

    }

}
