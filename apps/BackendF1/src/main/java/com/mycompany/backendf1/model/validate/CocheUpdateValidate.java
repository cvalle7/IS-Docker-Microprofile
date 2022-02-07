/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backendf1.model.validate;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Carlos
 */
public class CocheUpdateValidate {
    
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
