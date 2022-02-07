/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backendf1.model.beans;

import com.mycompany.backendf1.model.validate.Ref;
import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Carlos
 */

public class Piloto {
    @NotNull
    @NotBlank
    @Ref
    @JsonbProperty("ref")
    private String ref;
    @NotNull
    @NotBlank
    @JsonbProperty("nombre")
    private String nombre;
    @NotNull
    @NotBlank
    @JsonbProperty("apellidos")
    private String apellidos;
    @NotNull
    @NotBlank
    @JsonbProperty("titulos")
    private String titulos;
    @NotNull
    @NotBlank
    @JsonbProperty("marca")
    private String marca;

    public Piloto() {
    }

    public Piloto(String ref, String nombre, String apellidos, String titulos, String marca) {
        this.ref = ref;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.titulos = titulos;
        this.marca = marca;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTitulos() {
        return titulos;
    }

    public void setTitulos(String titulos) {
        this.titulos = titulos;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    
}
