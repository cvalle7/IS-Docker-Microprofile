/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backendf1.model.cdi.producers;

import com.mycompany.backendf1.model.DaoCoches;
import com.mycompany.backendf1.model.DaoEscuderia;
import com.mycompany.backendf1.model.DaoPilotos;
import com.mycompany.backendf1.model.cdi.Cache;
import com.mycompany.backendf1.model.imp.CacheF1;
import com.mycompany.backendf1.model.imp.ImpDaoEscuderias;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

/**
 *
 * @author Carlos
 */
@ApplicationScoped
public class CacheProducer {

    @Inject
    private DaoCoches daoCoches;

    @Inject
    private DaoPilotos daoPilotos;

    @ApplicationScoped
    @Produces
    @Cache()
    public CacheF1 cacheCocheProducer() {
        return new CacheF1(daoCoches, 
                           daoPilotos);
    }
  

}
