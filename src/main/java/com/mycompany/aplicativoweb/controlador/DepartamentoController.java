
package com.mycompany.aplicativoweb.controlador;

import com.mycompany.aplicativoweb.Dao.DepartamentoDAO;
import com.mycompany.aplicativoweb.entidades.Departamento;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author leona
 */
@ViewScoped
@Named(value="departamentoController")
public class DepartamentoController implements Serializable{
    
    private Departamento departamento;
    private Departamento departamentoSeleccionado;
    private List<Departamento> depatamentos;
    private List<Departamento> departamentoSeleccionados;
    private int codigoBuscar = 0;
    
    @PostConstruct
    public void init(){
        this.departamento = new Departamento();
        listar();
    }

    public DepartamentoController() {
    }

    public List<Departamento> getDepatamentos() {
        return depatamentos;
    }

    public Departamento getDepartamentoSeleccionado() {
        return departamentoSeleccionado;
    }

    public void setDepartamentoSeleccionado(Departamento departamentoSeleccionado) {
        this.departamentoSeleccionado = departamentoSeleccionado;
    }

    public List<Departamento> getDepartamentoSeleccionados() {
        return departamentoSeleccionados;
    }

    public void setDepartamentoSeleccionados(List<Departamento> departamentoSeleccionados) {
        this.departamentoSeleccionados = departamentoSeleccionados;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public int getCodigoBuscar() {
        return codigoBuscar;
    }

    public void setCodigoBuscar(int codigoBuscar) {
        this.codigoBuscar = codigoBuscar;
    }
    
    public void nuevo(){
        
    }
    
    public void insertarDepartamento(){
        DepartamentoDAO de = new DepartamentoDAO();
        departamento.setFechaCreacion(new Date());
        de.insertarDepartamento(departamento);
        departamento = new Departamento();
        PrimeFaces.current().executeScript("PF('insertarDepartamentoDialog').hide()");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Departamento agregado"));
        listar();
        
        //actualizar();
    }
    
    public void buscarDepartamentoCodigo(){
        DepartamentoDAO deD = new DepartamentoDAO();
        depatamentos=null;
        depatamentos = deD.busacarDepartamentoCodigo(departamento);
        actualizar();
    }
    
    public void actualizarDepartamento(){
        DepartamentoDAO de = new DepartamentoDAO();
        departamento.setFechaModificacion(new Date());
        de.modificarDepartamento(departamento);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Departamento actualizado"));
        listar();
        //actualizar();
    }
    
    public void departamentoActualizar(Departamento d){
        departamento = d;
        System.out.println("vlor________--- " + d);
    }
    
     public void eliminarDepartamento(Departamento departamento) {
         
        if(departamento !=null ){
        DepartamentoDAO deD = new DepartamentoDAO();
        System.out.println("departamentoSeleccionado:  " + departamento);
        deD.eliminarDepartamento(departamento);
        listar();
        actualizar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Departamento eliminado"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Seleccione un valor"));
        }
    }
    
    public void listar(){
        DepartamentoDAO de = new DepartamentoDAO();
        depatamentos=null;
        depatamentos = de.listarDepartamentos();
    }
    
    public void actualizar(){
        PrimeFaces.current().ajax().update(":form1:tab:form2:dt-departamento", ":form1:tab:form2:messages",
                ":form1:tab:form2:dialogoInsertar:manage-departamento-content");
    }
}
