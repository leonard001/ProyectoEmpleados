
package com.mycompany.aplicativoweb.conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Conexion {
    
    private static EntityManagerFactory emf = null;
    
    public static EntityManager createEntityManager(){
        try{
            if(emf == null){
                emf = Persistence.createEntityManagerFactory("test");
                System.out.println("Conectado");
            }
        }catch(Exception ex){
            System.out.println("Error de conexion -->> " + ex.getMessage());
        }
        return emf.createEntityManager();
    }
}
