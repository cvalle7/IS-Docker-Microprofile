/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frontendf1.models.providers;

import com.mycompany.frontendf1.models.exceptions.F1BadRequestException;
import com.mycompany.frontendf1.models.exceptions.F1Exception;
import com.mycompany.frontendf1.models.exceptions.F1NotFoundException;
import java.util.Optional;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

/**
 *
 * @author Carlos
 */
public class BackendServiceResponseMapper implements ResponseExceptionMapper<F1Exception>{

    @Override
    public F1Exception toThrowable(Response rspns) {
        int status = rspns.getStatus();
        
        switch(rspns.getStatus()){
            
            case 400: String badrequest="BadRequest";
                      return new F1BadRequestException(rspns.readEntity(JsonObject.class).toString());
            case 404: String notFound = "NotFound";
                    return new F1NotFoundException();
            case 500: return new F1Exception();
            default: return null;
            
        }
    }
    
}
