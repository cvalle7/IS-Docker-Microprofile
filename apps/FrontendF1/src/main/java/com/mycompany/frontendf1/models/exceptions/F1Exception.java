/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frontendf1.models.exceptions;

/**
 *
 * @author Carlos
 */
public class F1Exception extends Exception{

    public F1Exception() {
    }

    public F1Exception(String string) {
        super(string);
    }

    public F1Exception(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public F1Exception(Throwable thrwbl) {
        super(thrwbl);
    }

    public F1Exception(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
}
