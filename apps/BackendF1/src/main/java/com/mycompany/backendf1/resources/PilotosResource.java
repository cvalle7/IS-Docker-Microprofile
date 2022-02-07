/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backendf1.resources;

import com.mycompany.backendf1.model.DaoPilotos;
import com.mycompany.backendf1.model.beans.Piloto;
import com.mycompany.backendf1.model.cdi.Cache;
import com.mycompany.backendf1.model.exceptions.F1Exception;
import com.mycompany.backendf1.model.validate.PilotoUpdateValidate;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
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
@RequestScoped
@Path("pilotos")
public class PilotosResource {

    @Inject
    @Cache
    private DaoPilotos dao;

    @Inject
    private Validator validator;

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
    public Response selectPiloto(@PathParam("ref") String ref) throws F1Exception {

        Optional<Piloto> opt = dao.selectAllPilot(ref);

        return opt.map(piloto -> Response.ok()
                .entity(piloto)
                .build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)
                .build());

    }

    @POST
    @Path("insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertPiloto(Piloto piloto) throws F1Exception {

        Optional<Response> optValidation = pilotovalidation(piloto);

        if (optValidation.isPresent()) {
            return optValidation.get();
        }

        return Response.ok()
                       .entity(dao.insertPilot(piloto))
                       .build();

    }

    @PUT
    @Path("update/{ref}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePiloto(@PathParam("ref") String ref, PilotoUpdateValidate piloto) throws F1Exception {

        piloto.setRef(ref);
        
        Set<ConstraintViolation<PilotoUpdateValidate>> validate = validator.validate(piloto);
        if (!validate.isEmpty()) {

            JsonObjectBuilder job = Json.createObjectBuilder();

            for (ConstraintViolation<PilotoUpdateValidate> errores : validate) {

                job.add(errores.getPropertyPath().toString(),
                        errores.getMessage());

            }

            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(job.build())
                           .build();
        }
        
        Optional<Piloto> insertPilot = dao.updatePilot(ref,
                                                       piloto.getNombre(),
                                                       piloto.getApellidos(),
                                                       piloto.getTitulos());

        return insertPilot.map(pilot -> Response.ok()
                .entity(pilot)
                .build()
        )
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)
                .build()
                );

    }

    @DELETE
    @Path("delete/{ref}")
    public Response deletePilot(@PathParam("ref") String ref) throws F1Exception {

        if (dao.deletePilot(ref)) {
            return Response.ok()
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND)
                .build();

    }

    private Optional<Response> pilotovalidation(Piloto piloto) {

        Set<ConstraintViolation<Piloto>> validate = validator.validate(piloto);
        if (!validate.isEmpty()) {

            JsonObjectBuilder job = Json.createObjectBuilder();

            for (ConstraintViolation<Piloto> errores : validate) {

                job.add(errores.getPropertyPath().toString(),
                        errores.getMessage());

            }

            Response response = Response.status(Response.Status.BAD_REQUEST)
                    .entity(job.build())
                    .build();

            return Optional.of(response);

        }

        return Optional.empty();

    }

}
