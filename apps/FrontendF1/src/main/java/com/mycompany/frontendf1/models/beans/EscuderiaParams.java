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
public class EscuderiaParams {
    
    @JsonbProperty("marca")
    private String marca;
   
    @JsonbProperty("ref")
    private String ref;
   
    @JsonbProperty("id")
    private String id; 

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
}
