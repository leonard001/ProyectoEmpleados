
package com.mycompany.aplicativoweb.controlador;

import com.mycompany.aplicativoweb.Dao.DepartamentoDAO;
import com.mycompany.aplicativoweb.Dao.EmpleadoDAO;
import com.mycompany.aplicativoweb.entidades.Departamento;
import com.mycompany.aplicativoweb.entidades.Empleado;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

@ViewScoped
@Named(value="empleadoController")
public class EmpleadoController implements Serializable{
    
    private List<Empleado> empleados;
    private Empleado empleadoSeleccionado;
    private List<Empleado> listEmpleadosSeleccionados;
    private List<Departamento> dpartamentos;
    private int idDepartamento;
    
    
    @PostConstruct
    public void init()  {
        this.empleadoSeleccionado = new Empleado();
        listar();
        cargardepartamentos();
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> Empleados) {
        this.empleados = Empleados;
    }

    public Empleado getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSeleccionado(Empleado empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }

    public List<Empleado> getListEmpleadosSeleccionados() {
        return listEmpleadosSeleccionados;
    }

    public void setListEmpleadosSeleccionados(List<Empleado> listEmpleadosSeleccionados) {
        this.listEmpleadosSeleccionados = listEmpleadosSeleccionados;
    }

    public List<Departamento> getDpartamentos() {
        return dpartamentos;
    }

    public void setDpartamentos(List<Departamento> dpartamentos) {
        this.dpartamentos = dpartamentos;
    }
    
    public void cargardepartamentos(){
        DepartamentoDAO dep = new DepartamentoDAO();
        dpartamentos = dep.listarDepartamentos();
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
    
     public void insertarEmpleado(){
        System.out.println("Codigo:" + empleadoSeleccionado.getNombres());
        System.out.println("Nombre:" + empleadoSeleccionado.getApellidos());
        DepartamentoDAO de = new DepartamentoDAO();
        Departamento dep = new Departamento();
        dep.setId(idDepartamento);
        Departamento dep2 = de.busacarDepartamentoId(dep);
        EmpleadoDAO emp = new EmpleadoDAO();
        if(dep2.getId()!= 0){
            empleadoSeleccionado.setDepartamento(dep2);
            empleadoSeleccionado.setFechaCreacion(new Date());
        emp.insertarEmpleado(empleadoSeleccionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empleado agregado"));
        listar();
        }else{
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No existe departamento"));
        }
        
        PrimeFaces.current().executeScript("PF('insertarDepartamentoDialog').hide()");
        PrimeFaces.current().ajax().update(":form1:tab:form:dt-empleados", ":form1:tab:form:messages");
    }
    
    public void actualizarEmpleado(){
        System.out.println("Codigo:" + empleadoSeleccionado.getNombres());
        System.out.println("Nombre:" + empleadoSeleccionado.getApellidos());
        EmpleadoDAO emp = new EmpleadoDAO();
        emp.modificarEmpleado(empleadoSeleccionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empleado actualizado"));
        listar();
        
        PrimeFaces.current().executeScript("PF('modificarDepartamentoDialog').hide()");
        PrimeFaces.current().ajax().update(":form1:tab:form:dt-empleados", ":form1:tab:form:messages");
    }
    
     public void eliminarEmpleado() {
        EmpleadoDAO emp = new EmpleadoDAO();
        System.out.println("departamentoSeleccionado:  " + empleadoSeleccionado);
        emp.eliminarEmpleado(empleadoSeleccionado);
        //listar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empleado Eliminado"));
        PrimeFaces.current().ajax().update(":form1:tab:form:dt-empleados", ":form1:tab:form:messages");
    }

    public String optenerMensajeBoton() {
        if (fueronSeleccionadosEmpleados()) {
            int size = this.listEmpleadosSeleccionados.size();
            return size > 1 ? size + " Empleados seleccionados" : "1 Empleado seleccionado";
        }

        return "Delete";
    }

    public boolean fueronSeleccionadosEmpleados() {
        return this.listEmpleadosSeleccionados != null && !this.listEmpleadosSeleccionados.isEmpty();
    }

    public void emininarEmpleadosSeleccionados() {
         EmpleadoDAO emp = new EmpleadoDAO();
        for(Empleado em:listEmpleadosSeleccionados){
            emp.eliminarEmpleado(em);
        }
        this.listEmpleadosSeleccionados = null;
        listar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empleados eliminados"));
        PrimeFaces.current().ajax().update(":form1:tab:form:dt-empleados", ":form1:tab:form:messages");
        PrimeFaces.current().executeScript("PF('dtEmpleados').clearFilters()");
    }
    
    public void listar(){
        EmpleadoDAO emp = new EmpleadoDAO();
        empleados = emp.listarEmpleados();
    }
}
