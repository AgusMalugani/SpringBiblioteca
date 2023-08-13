package com.egg.biblioteca.servicio;

import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorios.EditorialRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialServicio {

    @Autowired
    EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre) throws MiException {
        validar(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);

        editorialRepositorio.save(editorial);

    }

    
        public List listarEditoriales(){
        List<Editorial> listaEdit = editorialRepositorio.findAll();
        return listaEdit;
    }
    
    
    
    private void validar(String nombre) throws MiException {

        if (nombre.isEmpty() || nombre == null) {

            throw new MiException("El nombre no puede estar vacio o nulo");
        }

    }
}
