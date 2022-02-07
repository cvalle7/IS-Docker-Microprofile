/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frontendf1.models;

import com.mycompany.frontendf1.models.beans.Escuderia;
import com.mycompany.frontendf1.models.exceptions.F1Exception;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.core.Response;

/**
 *
 * @author Carlos
 */
public interface DaoEscuderias {
    public List<Escuderia> selectAllTeams() throws F1Exception;
    public Escuderia selectTeams(String marca) throws F1Exception;
    public Escuderia insertTeam(String marca, String ref, String id) throws F1Exception;
    public Response deleteTeam(String marca) throws F1Exception;
}
