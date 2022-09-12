
package com.mycompany.aplicativoweb.Dao;

import com.mycompany.aplicativoweb.conexion.Conexion;
import com.mycompany.aplicativoweb.entidades.Empleado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author leonard
 */
public class EmpleadoDAO {
    
    public void insertarEmpleado(Empleado empleado){
        EntityManager em = null;
        try{
            em = Conexion.createEntityManager();
            em.getTransaction().begin();
            System.err.println("---Objeto---" + empleado);
            em.persist(empleado);
            em.getTransaction().commit();
            System.out.println("Guardado con exito");
        }catch(Exception ex){
            System.out.println("Error al guardar " + ex.getMessage());
        }finally{
            if(em != null){
                em.close();
            }
        }
    }
    
    public void modificarEmpleado(Empleado empleado){
        EntityManager em = null;
        try{
            em = Conexion.createEntityManager();
            Empleado emp = em.find(Empleado.class, empleado.getId());
            em.getTransaction().begin();
            if(em.contains(emp)){
                em.merge(empleado);
                System.out.println("Modificado exitosamente");
            }
            em.getTransaction().commit();
            System.out.println("Guardado con exito");
        }catch(Exception ex){
            System.out.println("Error al modificar " + ex.getMessage());
        }finally{
            if(em != null){
                em.close();
            }
        }
    }
    
    public void eliminarEmpleado(Empleado empleado){
        EntityManager em = null;
        try{
            em = Conexion.createEntityManager();
            Empleado emp = em.find(Empleado.class, empleado.getId());
            em.getTransaction().begin();
            if(em.contains(emp)){
                em.remove(emp);
                System.out.println("Modificado eliminado");
            }
            em.getTransaction().commit();
            System.out.println("Guardado con exito");
        }catch(Exception ex){
            System.out.println("Error al modificar " + ex.getMessage());
        }finally{
            if(em != null){
                em.close();
            }
        }
    }
    
    public List<Empleado> listarEmpleados(){
        EntityManager em = null;
        List<Empleado> listaEmpleados = new ArrayList<>();
        try{
            em = Conexion.createEntityManager();
            Query query = em.createQuery("Select d From Empleado d");
            listaEmpleados = query.getResultList();
            System.out.println("Listado con exito" + listaEmpleados +" el Query "+ query.getResultList());
        }catch(Exception ex){
            System.out.println("Error al modificar " + ex.getMessage());
        }finally{
            if(em != null){
                em.close();
            }
        }
        return listaEmpleados;
    }
}
