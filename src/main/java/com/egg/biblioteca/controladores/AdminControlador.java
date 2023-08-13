/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.biblioteca.controladores;

import com.egg.biblioteca.entidades.Usuario;
import com.egg.biblioteca.repositorios.UsuarioRepositorio;
import com.egg.biblioteca.servicio.UsuarioServicio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    UsuarioRepositorio ur;

    @Autowired
    UsuarioServicio us;

    @GetMapping("/dashboard")
    public String panelAdministrativo() {

        return "panel.html";

    }

    @GetMapping("/usuarios")
    public String listar(ModelMap modelo) {

        List<Usuario> usuarios = us.listarUsuarios();

        modelo.put("usuarios", usuarios);

        return "usuario_list.html";

    }
    
    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable String id){
        us.cambiarRol(id);
        return "redirect:/admin/usuarios";
    }
    

}
