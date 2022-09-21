package com.papeleria.v1.papeleria_v1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.papeleria.v1.papeleria_v1.Repository.VentaRepository;

@Controller
public class VentaController{
    @Autowired
    private VentaRepository ventaRepository;

    /**
     * Lista de Ventas
     * @return
     */
    @GetMapping("/ventas")
    public String getVentas(Model model) {
        model.addAttribute("ventas", ventaRepository.findAll());
        return "Ventas/ventas";
    }
    
}

