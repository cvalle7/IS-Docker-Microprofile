package com.mycompany.frontendf1.resources;

import com.mycompany.frontendf1.models.DaoCoches;
import com.mycompany.frontendf1.models.beans.Coche;
import com.mycompany.frontendf1.models.exceptions.F1BadRequestException;
import com.mycompany.frontendf1.models.exceptions.F1Exception;
import com.mycompany.frontendf1.models.exceptions.F1NotFoundException;
import com.mycompany.frontendf1.models.services.BackendService;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.validation.ConstraintViolation;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author 
 */
@Path("coches")
public class CochesResource {
    
    @Inject
    private DaoCoches dao;
    
   
    @GET
    public Response selectCars() throws F1Exception{
        return Response.ok()
                       .entity(dao.selectAllCars())
                       .build();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectCar(@PathParam("id") String id){
        try {
            Coche selectCar = dao.selectCar(id);
            return Response.ok()
                           .entity(selectCar)
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
    public Response insertCoche(Coche coche) {

        try {
            return Response.ok()
                    .entity(dao.insertCar(coche))
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
    @Path("update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCoche(@PathParam("id") String id, Coche coche) throws F1Exception {

        try {
            return Response.ok()
                    .entity(dao.updateCar(id, coche.getModelo(), coche.getCv(), coche.getvMax()))
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
    @Path("delete/{id}")
    public Response deleteCoche(@PathParam("id") String id) throws F1Exception {

        try {
             dao.deleteCar(id);
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
