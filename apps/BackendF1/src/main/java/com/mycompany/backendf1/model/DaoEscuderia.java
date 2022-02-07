/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backendf1.model;

import com.mycompany.backendf1.model.beans.Escuderia;
import com.mycompany.backendf1.model.beans.Piloto;
import com.mycompany.backendf1.model.exceptions.F1Exception;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Carlos
 */
public interface DaoEscuderia {
    public HashMap<String, Escuderia> selectAllTeams() throws F1Exception;
    public Optional<HashMap<String, Escuderia>> selectTeams(String marca) throws F1Exception;
    public HashMap<String, Escuderia> insertTeam(String marca, String ref, String id) throws F1Exception;
    public boolean deleteTeam(String marca) throws F1Exception;
}
