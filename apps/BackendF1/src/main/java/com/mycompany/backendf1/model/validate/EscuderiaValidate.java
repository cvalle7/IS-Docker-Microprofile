/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backendf1.model.validate;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos
 */
public class EscuderiaValidate {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    @JsonbProperty("marca")
    private String marca;
    @NotNull
    @NotBlank
    @Ref
    @JsonbProperty("ref")
    private String ref;
    @NotNull
    @NotBlank
    @JsonbProperty("id")
    private String id; 

    public EscuderiaValidate() {
    }

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
