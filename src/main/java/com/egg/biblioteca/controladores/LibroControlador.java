
package com.egg.biblioteca.controladores;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.servicio.AutorServicio;
import com.egg.biblioteca.servicio.EditorialServicio;
import com.egg.biblioteca.servicio.LibroServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")
public class LibroControlador {
    
    @Autowired
    private LibroServicio ls;
    
    @Autowired
    private AutorServicio as;
    
    @Autowired
    private EditorialServicio es;
    
  
    @GetMapping("/registrar")
    public String registrar( ModelMap modelo){
        List<Autor> autores = as.listarAutores();
        List<Editorial> editoriales = es.listarEditoriales();
                
        modelo.addAttribute("autores", autores);  
        modelo.addAttribute("editoriales", editoriales);
        
        
        return "libro_form.html";
    }
 
@PostMapping("/registro")
public String registro(@RequestParam(required = false) String isbn, @RequestParam String titulo, @RequestParam(required = false) Integer ejemplares, @RequestParam String idAutor, @RequestParam String idEditorial, ModelMap modelo){
  
        try {
            ls.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            modelo.put("exito", "El libro fue cargado correctamente!");
        } catch (MiException ex) {
            
        List<Autor> autores = as.listarAutores();
        List<Editorial> editoriales = es.listarEditoriales();
                
        modelo.addAttribute("autores", autores);  
        modelo.addAttribute("editoriales", editoriales);    // es x si hay un error, vuelva a mostrar la lista de los libros.
        
        modelo.put("error", ex.getMessage());
        
        
            return "libro_form.html";
        }
        return "index.html";
}
   
 @GetMapping("/lista")
    public String lista(ModelMap modelo){
        List<Libro> libros = ls.listarLibros();
        modelo.addAttribute("libros", libros);
        return "libro_list.html";
    }
    
    
    @GetMapping("/modificar/{isbn}")
    public String modificar( @PathVariable String isbn, ModelMap modelo){
         List<Autor> autores = as.listarAutores();
        List<Editorial> editoriales = es.listarEditoriales();
                
        modelo.addAttribute("autores", autores);  
        modelo.addAttribute("editoriales", editoriales);
        
        modelo.put("libro", ls.getOne(isbn));
        
        return "libro_modificar.html";
    }
    
    @PostMapping("/modificar/{isbn}")
    public String modificar(@PathVariable String isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial ,ModelMap modelo){
        
        try {
            ls.modificarLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
             return "redirect:../lista";
            
        } catch (MiException ex) {
           modelo.put("error", ex.getMessage());
           return "libro_modificar.html";
        }
        
       
    }
    
    
}
