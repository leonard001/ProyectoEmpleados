
package com.mycompany.aplicativoweb.Dao;

import com.mycompany.aplicativoweb.conexion.Conexion;
import com.mycompany.aplicativoweb.entidades.Departamento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author leonard
 */
public class DepartamentoDAO {

    public void insertarDepartamento(Departamento departamento){
        EntityManager em = null;
        try{
            em = Conexion.createEntityManager();
            em.getTransaction().begin();
            System.err.println("---Objeto---" + departamento);
            em.persist(departamento);
            System.out.println("antes de guardar");
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
    
    public void modificarDepartamento(Departamento departamento){
        EntityManager em = null;
        try{
            em = Conexion.createEntityManager();
            Departamento dep = em.find(Departamento.class, departamento.getId());
            em.getTransaction().begin();
            if(em.contains(dep)){
                em.merge(departamento);
                System.out.println("Modificado exitosamente");
            }
            em.getTransaction().commit();
        }catch(Exception ex){
            System.out.println("Error al modificar " + ex.getMessage());
        }finally{
            if(em != null){
                em.close();
            }
        }
    }
    
    public void eliminarDepartamento(Departamento departamento){
        EntityManager em = null;
        try{
            em = Conexion.createEntityManager();
            System.out.println("valor a eliminar" + departamento);
            Departamento dep = em.find(Departamento.class, departamento.getId());
            em.getTransaction().begin();
            if(em.contains(dep)){
                em.remove(dep);
                System.out.println("Departamento eliminado");
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
    
    public Departamento busacarDepartamentoId(Departamento departamento){
        EntityManager em = null;
        Departamento dep = new Departamento();
        try{
            em = Conexion.createEntityManager();
            dep = em.find(Departamento.class, departamento.getId());
            System.out.println("Busacado con exito" + dep);
        }catch(Exception ex){
            System.out.println("Error al buscar " + ex.getMessage());
        }finally{
            if(em != null){
                em.close();
            }
        }
        return dep;
    }
    
    public List<Departamento> busacarDepartamentoCodigo(Departamento departamento){
        EntityManager em = null;
        List<Departamento> listaDepartamentos = new ArrayList<>();
        try{
            em = Conexion.createEntityManager();
            Query query = em.createQuery("Select d From Departamento d Where codigo="+departamento.getCodigo());
            listaDepartamentos = query.getResultList();
            System.out.println("Listado con exito");
        }catch(Exception ex){
            System.out.println("Error al Listar " + ex.getMessage());
        }finally{
            if(em != null){
                em.close();
            }
        }
        return listaDepartamentos;
    }
    
    public List<Departamento> listarDepartamentos(){
        EntityManager em = null;
        List<Departamento> listaDepartamentos = new ArrayList<>();
        try{
            em = Conexion.createEntityManager();
            Query query = em.createQuery("Select d From Departamento d");
            listaDepartamentos = query.getResultList();
            System.out.println("Listado con exito");
        }catch(Exception ex){
            System.out.println("Error al modificar " + ex.getMessage());
        }finally{
            if(em != null){
                em.close();
            }
        }
        return listaDepartamentos;
    }
}
