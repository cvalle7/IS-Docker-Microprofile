/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backendf1.model.beans;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos
 */
public class Coche {
    
    @JsonbProperty("id")
    private String id;
    @NotNull
    @NotBlank
    @JsonbProperty("marca")
    private String marca;
    @NotNull
    @NotBlank
    @JsonbProperty("modelo")
    private String modelo;
    @NotNull
    @NotBlank
    @JsonbProperty("cv")
    private String cv;
    @NotNull
    @NotBlank
    @JsonbProperty("vMax")
    private String vMax;

    public Coche(String id, String marca, String modelo, String cv, String vMax) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.cv = cv;
        this.vMax = vMax;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

   

    public Coche() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getvMax() {
        return vMax;
    }

    public void setvMax(String vMax) {
        this.vMax = vMax;
    }
    
    
    
}
