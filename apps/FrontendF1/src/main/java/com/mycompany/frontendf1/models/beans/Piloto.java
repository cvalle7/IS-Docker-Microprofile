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
public class Piloto {
    @JsonbProperty("ref")
    private String ref;
    @JsonbProperty("nombre")
    private String nombre;
    @JsonbProperty("apellidos")
    private String apellidos;
    @JsonbProperty("titulos")
    private String titulos;
    @JsonbProperty("marca")
    private String marca;

    public Piloto(String ref, String nombre, String apellidos, String titulos, String marca) {
        this.ref = ref;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.titulos = titulos;
        this.marca = marca;
    }

    public Piloto() {
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
