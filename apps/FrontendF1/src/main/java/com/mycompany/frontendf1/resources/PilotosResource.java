/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frontendf1.resources;

import com.mycompany.frontendf1.models.DaoPilotos;
import com.mycompany.frontendf1.models.beans.Coche;
import com.mycompany.frontendf1.models.beans.Piloto;
import com.mycompany.frontendf1.models.exceptions.F1BadRequestException;
import com.mycompany.frontendf1.models.exceptions.F1Exception;
import com.mycompany.frontendf1.models.exceptions.F1NotFoundException;
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Carlos
 */
@Path("pilotos")
public class PilotosResource {
    
    @Inject
    private DaoPilotos dao;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectPilotos() throws F1Exception {

        return Response.ok()
                .entity(dao.selectAllPilots())
                .build();

    }

    @GET
    @Path("{ref}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectPiloto(@PathParam("ref") String ref) {

        try {
            Piloto piloto = dao.selectAllPilot(ref);
            return Response.ok()
                           .entity(piloto)
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
    public Response insertPiloto(Piloto piloto) throws F1Exception {

        try {
            return Response.ok()
                    .entity(dao.insertPilot(piloto))
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

    @POST
    @Path("update/{ref}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePiloto(@PathParam("ref") String ref, Piloto piloto) throws F1Exception {

       try {
            return Response.ok()
                    .entity(dao.updatePilot(ref, piloto.getNombre(), piloto.getApellidos(), piloto.getTitulos()))
                    .build();
        } catch (F1BadRequestException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ex)
                    .build();
        } catch (F1NotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND)
                           .build();
        } catch (F1Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .build();
        }

    }

    @GET
    @Path("delete/{ref}")
    public Response deletePilot(@PathParam("ref") String ref) throws F1Exception {

        try {
            dao.deletePilot(ref);
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
