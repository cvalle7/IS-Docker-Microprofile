/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frontendf1.resources;

import com.mycompany.frontendf1.models.DaoEscuderias;
import com.mycompany.frontendf1.models.beans.Coche;
import com.mycompany.frontendf1.models.beans.Escuderia;
import com.mycompany.frontendf1.models.beans.EscuderiaParams;
import com.mycompany.frontendf1.models.exceptions.F1BadRequestException;
import com.mycompany.frontendf1.models.exceptions.F1Exception;
import com.mycompany.frontendf1.models.exceptions.F1NotFoundException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.validation.ConstraintViolation;
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
    private DaoEscuderias dao;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectTeams() throws F1Exception {

        return Response.ok()
                .entity(dao.selectAllTeams())
                .build();

    }

    @GET
    @Path("{marca}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectTeam(@PathParam("marca") String marca) throws F1Exception {

        try {
            
            return Response.ok()
                           .entity(dao.selectTeams(marca))
                           .build();
            
            
        } catch (F1NotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND)
                           .build();
        } catch (F1Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .build();
        }

    }

    @POST
    @Path("insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertTeam(EscuderiaParams escuderia) throws F1Exception {
        
        try {
            
            return Response.ok()
                    .entity(dao.insertTeam(escuderia.getMarca(), 
                                           escuderia.getRef(),
                                           escuderia.getId()))
                    .build();
        } catch (F1BadRequestException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ex)
                    .build();
        } catch (F1Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .build();
        }
        

    }

    @GET
    @Path("delete/{escuderia}")
    public Response deleteTeam(@PathParam("escuderia") String escuderia) throws F1Exception {

       try {
            dao.deleteTeam(escuderia);
            return Response.ok()
                           .entity("Eliminado")
                           .build();
            
        } catch (F1NotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND)
                           .build();
        } catch (F1Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .build();
        }

    }

}
