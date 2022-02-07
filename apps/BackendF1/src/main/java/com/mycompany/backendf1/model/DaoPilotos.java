/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backendf1.model;

import com.mycompany.backendf1.model.beans.Piloto;
import com.mycompany.backendf1.model.exceptions.F1Exception;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Carlos
 */
public interface DaoPilotos {
    public List<Piloto> selectAllPilots() throws F1Exception;
    public Optional<Piloto> selectAllPilot(String ref) throws F1Exception;
    public Piloto insertPilot(Piloto piloto) throws F1Exception;
    public Optional<Piloto> updatePilot(String ref, String nombre, String apellidos, String titulos) throws F1Exception;
    public boolean deletePilot(String ref) throws F1Exception;
}
