
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
    
    @PostConstruct
    public void init()  {
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
    
    public void insertarDepartamento(){
        System.out.println("Codigo:" + departamento.getCodigo());
        System.out.println("Nombre:" + departamento.getNombre());
        DepartamentoDAO de = new DepartamentoDAO();
        departamento.setFechaCreacion(new Date());
        de.insertarDepartamento(departamento);
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Departamento agregado"));
        listar();
        
        PrimeFaces.current().executeScript("PF('insertarDepartamentoDialog').hide()");
        PrimeFaces.current().ajax().update(":form1:tab:form2:dt-departamento", ":form1:tab:form2:messages");
    }
    
    public void actualizarDepartamento(){
        System.out.println("Codigo:" + departamento.getCodigo());
        System.out.println("Nombre:" + departamento.getNombre());
        DepartamentoDAO de = new DepartamentoDAO();
        de.modificarDepartamento(departamento);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Departamento actualizado"));
        listar();
        
        PrimeFaces.current().executeScript("PF('modificarDepartamentoDialog').hide()");
        PrimeFaces.current().ajax().update(":form1:tab:form2:dt-departamento", ":form1:tab:form2:messages");
    }
    
     public void deleteProduct() {
        DepartamentoDAO deD = new DepartamentoDAO();
        System.out.println("departamentoSeleccionado:  " + departamentoSeleccionado);
        deD.eliminarDepartamento(departamentoSeleccionado);
        //listar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update(":form1:tab:form2:dt-departamento", ":form1:tab:form2:messages");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedProducts()) {
            int size = this.departamentoSeleccionados.size();
            return size > 1 ? size + " products selected" : "1 product selected";
        }

        return "Delete";
    }

    public boolean hasSelectedProducts() {
        return this.departamentoSeleccionados != null && !this.departamentoSeleccionados.isEmpty();
    }

    public void deleteSelectedProducts() {
        DepartamentoDAO deD = new DepartamentoDAO();
        for(Departamento de:departamentoSeleccionados){
            deD.eliminarDepartamento(de);
        }
        this.departamentoSeleccionados = null;
        listar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update(":form1:tab:form2:dt-departamento", ":form1:tab:form2:messages");
        PrimeFaces.current().executeScript("PF('dtDepartamentos').clearFilters()");
    }
    
    public void listar(){
        DepartamentoDAO de = new DepartamentoDAO();
        depatamentos = de.listarDepartamentos();
    }
}
