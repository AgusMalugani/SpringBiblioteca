/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.biblioteca.controladores;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.servicio.AutorServicio;
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

/**
 *
 * @author Usuario
 */
  @Controller
 @RequestMapping("/autor")     // localhost:8080/autor
public class AutorControlador {
      
      @Autowired // para inicializarlo
     private  AutorServicio as;
      
      
      @GetMapping("/registrar") // localhost:8080/autor/registrar
  public String registrar(){

return "autor_form.html";
}
  
    @PostMapping("/registro")  //para obtener los datos del form
    public String registro( @RequestParam String nombre, ModelMap modelo  ){  // recibo x parametro lo del form
        
          try {
              as.crearAutor(nombre); // lo envio a la creacion de autores
    modelo.put("exito", "El autor fue cargador con exito!");

          } catch (MiException ex) {
     modelo.put("error", ex.getMessage());          
              return "autor_form.html"; // SI HAY UN ERROR, ENTONCES VUELKVE AL FORMULARIO
          }
        return "autor_form.html"; // SI ESTA TODO CORRECTO, SE VUELVE AL INDEX
        
  }
    
    @GetMapping("/lista")
    public String lista(ModelMap modelo){
        List<Autor> autores = as.listarAutores();
        modelo.addAttribute("autores", autores);
        return "autor_list.html";
    }
    
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        
        modelo.put("autor", as.getOne(id));
        
        return "autor_modificar.html";
        
        
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar( @PathVariable String id, String nombre, ModelMap modelo ){
          try {
              as.modificarAutor(id, nombre);
              return "redirect:../lista";
          } catch (MiException ex) {
              modelo.put("error", ex.getMessage());
              return "autor_modificar.html";
          }
        
    }
    
    
  
    
}
