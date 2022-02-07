/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backendf1.model.beans;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 *
 * @author Carlos
 */
public class Escuderia {
    
    private String marca;
    private List<Piloto>pilotos;
    private List<Coche>coches;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public List<Piloto> getPilotos() {
        return pilotos;
    }

    public void setPilotos(List<Piloto> pilotos) {
        this.pilotos = pilotos;
    }

    public List<Coche> getCoches() {
        return coches;
    }

    public void setCoches(List<Coche> coches) {
        this.coches = coches;
    }

    
    public void addPilotos(Piloto piloto) {
        this.pilotos.add(piloto);
    }

    public void addCoches(Coche coche) {
        this.coches.add(coche);
    }
    
}
