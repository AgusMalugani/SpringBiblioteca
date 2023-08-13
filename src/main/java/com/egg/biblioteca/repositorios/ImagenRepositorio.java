/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.egg.biblioteca.repositorios;

import com.egg.biblioteca.entidades.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Usuario
 */
public interface ImagenRepositorio extends JpaRepository<Imagen, String> {
    
}
