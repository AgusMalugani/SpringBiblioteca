package com.egg.biblioteca.servicio;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {

    @Autowired
    AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre) throws MiException {
        validar(nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);

        autorRepositorio.save(autor);

    }

    public List listarAutores() {
        List<Autor> listaAu = autorRepositorio.findAll();
        return listaAu;
    }

    public void modificarAutor(String id, String nombre) throws MiException {
         validar(nombre);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(id);

        if (respuestaAutor.isPresent()) {
            Autor autor = respuestaAutor.get();

            autor.setNombre(nombre);

            autorRepositorio.save(autor);

        }

    }

    private void validar(String nombre) throws MiException {

        if (nombre.isEmpty() || nombre == null) {

            throw new MiException("El nombre no puede estar vacio o nulo");
        }

    }
    
    
    public Autor getOne(String id){
        
        return autorRepositorio.getOne(id);
    }
    

}
