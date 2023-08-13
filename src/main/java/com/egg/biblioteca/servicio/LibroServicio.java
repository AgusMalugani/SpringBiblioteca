
package com.egg.biblioteca.servicio;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorios.AutorRepositorio;
import com.egg.biblioteca.repositorios.EditorialRepositorio;
import com.egg.biblioteca.repositorios.LibroRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServicio {
    
    @Autowired
    LibroRepositorio libroRepositorio;
    
    @Autowired
    AutorRepositorio autorRepositorio;
    
    @Autowired
    EditorialRepositorio editorialRepositorio;
    
    @Transactional
    public void crearLibro(String isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial  ) throws MiException{
 
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        // si viene vacio o nulo, se va alanzar una excepcion y no se va a hacer lo de abajo, y no va a entrar a la bd
        
        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
        
        
        
        
        
        Libro libro = new Libro();
        
        
        
        libro.setIsbn(isbn);
        
        libro.setTitulo(titulo);
        
        libro.setEjemplares(ejemplares);
        
        libro.setAlta( new Date() );
        
        libro.setAutor(autor);
        
        libro.setEditorial(editorial);
        
        libroRepositorio.save(libro);
        
        
    }
    
    public List listarLibros(){
        List<Libro> listaLibro = libroRepositorio.findAll();
        return listaLibro;
    }
    
    @Transactional
    public void modificarLibro(String isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial  ) throws MiException{
        validar(isbn,titulo,ejemplares,idAutor,idEditorial);
        
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);
        
         Autor autor = new Autor();
         Editorial editorial = new Editorial();
        
        if(respuestaAutor.isPresent()){
             autor = respuestaAutor.get();          
        }
        
        if(respuestaEditorial.isPresent()){
             editorial = respuestaEditorial.get();
        }
        
        
        if(respuesta.isPresent()){
            // si respuesta tiene algo, es verdadero, entonces.
            Libro libro = respuesta.get(); // guardo el libro que me trajo
            
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            
            libro.setEjemplares(ejemplares);
            
            libroRepositorio.save(libro);
            
        }
        
        
    }
    
    public Libro getOne(String isbn){
        
        return libroRepositorio.getOne(isbn);
    }
    
    
    private void validar(String isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial  ) throws MiException{
               
        if(isbn == null){
            throw new MiException("El isbn no puede ser nulo");
        }
        
        if(  titulo.isEmpty() || titulo == null ){
            throw new MiException("El titulo no puede estar vacio o ser nulo");
        }
        
        if ( ejemplares == null ){
            throw new MiException(" El numero de ejemplares no debe ser nulo");
            
        }
        
        if( idAutor.isEmpty() || idAutor == null ){
             throw new MiException("El id Autor no puede estar vacio o ser nulo");
        }
        
        if( idEditorial.isEmpty() || idEditorial == null ){
             throw new MiException("El id Editorial no puede estar vacio o ser nulo");
        }
        
    }
    
    
    
    
}
