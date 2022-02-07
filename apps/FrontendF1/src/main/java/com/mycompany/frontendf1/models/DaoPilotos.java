/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frontendf1.models;

import com.mycompany.frontendf1.models.beans.Piloto;
import com.mycompany.frontendf1.models.exceptions.F1Exception;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.core.Response;

/**
 *
 * @author Carlos
 */
public interface DaoPilotos {
    public List<Piloto> selectAllPilots() throws F1Exception;
    public Piloto selectAllPilot(String ref) throws F1Exception;
    public Piloto insertPilot(Piloto piloto) throws F1Exception;
    public Piloto updatePilot(String ref, String nombre, String apellidos, String titulos) throws F1Exception;
    public Response deletePilot(String ref) throws F1Exception;
}
