
package com.egg.biblioteca.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.hibernate.annotations.GenericGenerator;

@NamedQueries({ // es para poder hacer varias querys
    
@NamedQuery(name = "Editorial.findAll", query =" SELECT e FROM Editorial e"),  // es para hacer las consultas y solo llamamos x el nombre
@NamedQuery( name = "Editorial.findPorId", query = " SELECT e FROM Editorial e WHERE e.id = :id  ")
})

@Entity
public class Editorial implements Serializable {

     @Id
    @GeneratedValue(generator = "uuid")
     @GenericGenerator(name = "uuid", strategy="uuid2")
    private String id;
    
    private String nombre;
  
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Editorial() {
    }

    
    public Editorial(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
       
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Editorial)) {
            return false;
        }
        Editorial other = (Editorial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Editorial{" + "id=" + id + ", nombre=" + nombre + '}';
    }

   
}
