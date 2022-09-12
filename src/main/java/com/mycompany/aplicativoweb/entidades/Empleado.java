
package com.mycompany.aplicativoweb.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author leonard
 */
@Entity 
@Table( name="empleados")
public class Empleado implements Serializable {
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column(name="documento_tipo")
    private String documentoTipo;
    @Column(name="documento_numero")
    private String documentoNumero;
    private String nombres;
    private String apellidos;
    //@Column(name="departamentos_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="departamentos_id", referencedColumnName = "id")
    private Departamento departamento;
    private String ciudad;
    private String direccion;
    @Column(name="correo_electrónico")
    private String email;
    private String telefono;
    @Column(name="fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name="fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    public Empleado(String documentoTipo, String documentoNumero, String nombres, String apellidos, Departamento departamento, String ciudad, String direccion, String email, String telefono, Date fechaCreacion, Date fechaModificacion) {
        this.documentoTipo = documentoTipo;
        this.documentoNumero = documentoNumero;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }

    public Empleado() {
    }

    public String getDocumentoTipo() {
        return documentoTipo;
    }

    public void setDocumentoTipo(String documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    public String getDocumentoNumero() {
        return documentoNumero;
    }

    public void setDocumentoNumero(String documentoNumero) {
        this.documentoNumero = documentoNumero;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "Empleado{" + "documentoTipo=" + documentoTipo + ", documentoNumero=" + documentoNumero + ", nombres=" + nombres + ", apellidos=" + apellidos + ", departamento=" + departamento + ", ciudad=" + ciudad + ", direccion=" + direccion + ", email=" + email + ", telefono=" + telefono + ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion + '}';
    }
}
