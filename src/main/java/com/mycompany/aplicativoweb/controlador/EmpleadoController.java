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
@Named(value = "empleadoController")
public class EmpleadoController implements Serializable {

    private List<Empleado> empleados;
    private Empleado empleadoSeleccionado;
    private List<Empleado> listEmpleadosSeleccionados;
    private List<Departamento> dpartamentos;
    private int idDepartamento = 0;

    @PostConstruct
    public void init() {
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

    public void cargardepartamentos() {
        DepartamentoDAO dep = new DepartamentoDAO();
        dpartamentos = dep.listarDepartamentos();
        PrimeFaces.current().ajax().update(":form1:tab:form:dialogs:manage-Nuevoempleado-content");
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public void insertarEmpleado() {
        DepartamentoDAO de = new DepartamentoDAO();
        Departamento dep = new Departamento();
        if (idDepartamento != 0) {
            dep.setId(idDepartamento);
            Departamento dep2 = de.busacarDepartamentoId(dep);
            EmpleadoDAO emp = new EmpleadoDAO();
            if (dep2.getId() != 0) {
                empleadoSeleccionado.setDepartamento(dep2);
                empleadoSeleccionado.setFechaCreacion(new Date());
                emp.insertarEmpleado(empleadoSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empleado agregado"));
                listar();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No existe departamento"));
            }

            PrimeFaces.current().executeScript("PF('nuevoEmpleadoDialog').hide()");
            actualizar();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe ingresar un departamento"));
            PrimeFaces.current().executeScript("PF('nuevoEmpleadoDialog').hide()");
            PrimeFaces.current().ajax().update(":form1:tab:form:messages");
        }

    }

    public void actualizarEmpleado() {
        EmpleadoDAO emp = new EmpleadoDAO();
        emp.modificarEmpleado(empleadoSeleccionado);
        empleadoSeleccionado.setFechaCreacion(new Date());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empleado actualizado"));
        listar();

        PrimeFaces.current().executeScript("PF('modificarDepartamentoDialog').hide()");
        actualizar();
    }

    public void eliminarEmpleado(Empleado empleado) {
        if (empleado != null) {
            EmpleadoDAO emp = new EmpleadoDAO();
            System.out.println("----------------------------->>>>>>>>>>>>>>>>>>>" + empleado);
            emp.eliminarEmpleado(empleado);
            listar();
            actualizar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empleado Eliminado"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Seleccione un valor"));
        }
    }

    public void listar() {
        EmpleadoDAO emp = new EmpleadoDAO();
        empleados = emp.listarEmpleados();
    }

    public void actualizar() {
        PrimeFaces.current().ajax().update(":form1:tab:form:dt-empleados", ":form1:tab:form:messages");
    }
}
