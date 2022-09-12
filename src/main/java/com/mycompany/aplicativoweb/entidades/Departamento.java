
package com.mycompany.aplicativoweb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author leonard
 */
@Entity 
@Table( name="departamentos")
public class Departamento implements Serializable {
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column(name="departamento_codigo")
    private int codigo;
    @Column(name="deparamento_nombre")
    private String nombre;
    @Column(name="fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name="fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    
    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
    private Set<Empleado> empleados = new HashSet<>();

    public Departamento() {
    }

    public Departamento(int codigo, String nombre, Date fechaCreacion, Date fechaModificacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Set<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
        for(Empleado empleado: empleados){
            empleado.setDepartamento(this);
        }
    }
    
    @Override
    public String toString() {
        return "Departamento{" + "codigo=" + codigo + ", nombre=" + nombre + ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion + '}';
    }   
}
