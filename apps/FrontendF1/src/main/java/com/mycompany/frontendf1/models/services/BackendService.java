/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frontendf1.models.services;

import com.mycompany.frontendf1.models.beans.Coche;
import com.mycompany.frontendf1.models.beans.Escuderia;
import com.mycompany.frontendf1.models.beans.EscuderiaParams;
import com.mycompany.frontendf1.models.beans.Piloto;
import com.mycompany.frontendf1.models.exceptions.F1Exception;
import com.mycompany.frontendf1.models.providers.BackendServiceResponseMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author Carlos
 */
@RegisterRestClient
@RegisterProvider(BackendServiceResponseMapper.class)
@Path("api")
public interface BackendService {
    
    @Path("coches")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Coche> selectCoches() throws F1Exception;

    @GET
    @Path("coches/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Coche seleCoche(@PathParam("id") String id) throws F1Exception ;

    @POST
    @Path("coches/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Coche insertCoche(Coche coche) throws F1Exception ;

    @PUT
    @Path("coches/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Coche updateCoche(@PathParam("id") String id, Coche coche) throws F1Exception ;

    @DELETE
    @Path("coches/delete/{id}")
    public Response deleteCar(@PathParam("id") String id) throws F1Exception;
    
    
    
    
    @GET
    @Path("pilotos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Piloto> selectPilotos() throws F1Exception;
    
    @GET
    @Path("pilotos/{ref}")
    @Produces(MediaType.APPLICATION_JSON)
    public Piloto selectPiloto(@PathParam("ref") String ref) throws F1Exception;

    @POST
    @Path("pilotos/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Piloto insertPiloto(Piloto piloto) throws F1Exception;

    @PUT
    @Path("pilotos/update/{ref}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Piloto updatePiloto(@PathParam("ref") String ref, Piloto piloto) throws F1Exception;

    @DELETE
    @Path("pilotos/delete/{ref}")
    public Response deletePilot(@PathParam("ref") String ref) throws F1Exception;

    
    
    
    
    
    @GET
    @Path("escuderias")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Escuderia> selectTeams() throws F1Exception;

    @GET
    @Path("escuderias/{marca}")
    @Produces(MediaType.APPLICATION_JSON)
    public Escuderia selectTeam(@PathParam("marca") String marca) throws F1Exception;

    @POST
    @Path("escuderias/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Escuderia insertTeam(EscuderiaParams escuderia) throws F1Exception;

    @DELETE
    @Path("escuderias/delete/{escuderia}")
    public Response deleteTeam(@PathParam("escuderia") String escuderia) throws F1Exception;
}
