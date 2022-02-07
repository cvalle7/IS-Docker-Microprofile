/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frontendf1.models.beans;

import javax.json.bind.annotation.JsonbProperty;

/**
 *
 * @author Carlos
 */
public class Coche {
    @JsonbProperty("id")
    private String id;
    @JsonbProperty("marca")
    private String marca;
    @JsonbProperty("modelo")
    private String modelo;
    @JsonbProperty("cv")
    private String cv;
    @JsonbProperty("vMax")
    private String vMax;

    public Coche(String id, String marca, String modelo, String cv, String vMax) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.cv = cv;
        this.vMax = vMax;
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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
