package com.mycompany.backendf1.resources;

import com.mycompany.backendf1.model.DaoCoches;
import com.mycompany.backendf1.model.beans.Coche;
import com.mycompany.backendf1.model.beans.Piloto;
import com.mycompany.backendf1.model.cdi.Cache;
import com.mycompany.backendf1.model.exceptions.F1Exception;
import com.mycompany.backendf1.model.validate.CocheUpdateValidate;
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
 * @author
 */
@RequestScoped
@Path("coches")
public class CochesResource {

    @Inject
    @Cache
    private DaoCoches dao;

    @Inject
    private Validator validator;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectCoches() throws F1Exception {

        return Response.ok()
                .entity(dao.selectAllCars())
                .build();

    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response seleCoche(@PathParam("id") String id) throws F1Exception {

        Optional<Coche> opt = dao.selectCar(id);

        return opt.map(coche -> Response.ok()
                .entity(coche)
                .build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)
                .build());

    }

    @POST
    @Path("insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertCoche(Coche coche) throws F1Exception {

        Optional<Response> optValidation = carvalidation(coche);

        if (optValidation.isPresent()) {
            return optValidation.get();
        }

        return Response.ok()
                .entity(dao.insertCar(coche))
                .build();

    }

    @PUT
    @Path("update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCoche(@PathParam("id") String id, CocheUpdateValidate coche) throws F1Exception {

        Set<ConstraintViolation<CocheUpdateValidate>> validate = validator.validate(coche);
        if (!validate.isEmpty()) {

            JsonObjectBuilder job = Json.createObjectBuilder();

            for (ConstraintViolation<CocheUpdateValidate> errores : validate) {

                job.add(errores.getPropertyPath().toString(),
                        errores.getMessage());

            }

            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(job.build())
                           .build();
        }
        
        Optional<Coche> insertCoche = dao.updateCar(id, coche.getModelo(), coche.getCv(), coche.getvMax());

        return insertCoche.map(car -> Response.ok()
                .entity(car)
                .build()
        )
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)
                .build()
                );

    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteCar(@PathParam("id") String id) throws F1Exception {
        if (dao.deleteCar(id)) {
            return Response.ok()
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND)
                .build();

    }

    private Optional<Response> carvalidation(Coche coche) {

        Set<ConstraintViolation<Coche>> validate = validator.validate(coche);
        if (!validate.isEmpty()) {

            JsonObjectBuilder job = Json.createObjectBuilder();

            for (ConstraintViolation<Coche> errores : validate) {

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
