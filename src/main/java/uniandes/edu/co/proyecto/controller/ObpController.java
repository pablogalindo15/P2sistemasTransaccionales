package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uniandes.edu.co.proyecto.modelo.Obp;
import uniandes.edu.co.proyecto.modelo.Prestamo;
import uniandes.edu.co.proyecto.repositorio.ObpRepository;
import uniandes.edu.co.proyecto.repositorio.PrestamoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ObpController {

    @Autowired
    private ObpRepository obpRepository;

    @Autowired
    private PrestamoRepository prestamoRepository;

    @GetMapping("/obps")
    public String listarObps(Model model) {
        List<Obp> obps = obpRepository.findAll();
        model.addAttribute("obps", obps);
        return "obps";
    }

    @GetMapping("/obps/new")
    public String formularioNuevoObp(Model model) {
        List<Prestamo> todosLosPrestamos = prestamoRepository.findAll();
        List<Prestamo> prestamosAbiertos = todosLosPrestamos.stream()
            .filter(prestamo -> "Abierto".equals(prestamo.getEstado()))
            .collect(Collectors.toList());
    
        model.addAttribute("obp", new Obp());
        model.addAttribute("prestamos", prestamosAbiertos);
        return "obpNueva";
    }

    @PostMapping("/obps/new/save")
    public String guardarObp(@ModelAttribute Obp obp, RedirectAttributes redirectAttributes, Model model) {
        Prestamo prestamo = prestamoRepository.findById(obp.getId_prestamo().getId()).orElse(null);

       
    
            if(obp.getTipo().equals("pago cuota")){
                    prestamo.setMonto(prestamo.getMonto()-(prestamo.getInteres()*0.01*prestamo.getMonto()));
                    prestamoRepository.save(prestamo);
                    obpRepository.save(obp);
                    return("redirect:/obps");
            }

            else if(obp.getTipo().equals("abonamineto")){
                prestamo.setMonto(prestamo.getMonto()-(obp.getValor()));
                prestamoRepository.save(prestamo);
                obpRepository.save(obp);
                return("redirect:/obps");
                
            }
        
    
    return("redirect:/");

}
    

    @GetMapping("/obps/{id}/edit")
    public String formularioEditarObp(@PathVariable("id") Integer id, Model model) {
        Obp obp = obpRepository.findById(id).orElse(null);
        List<Prestamo> prestamos = prestamoRepository.findAll();
        if (obp != null) {
            model.addAttribute("obp", obp);
            model.addAttribute("prestamos", prestamos);
            return "editar-obp";
        } else {
            return "redirect:/obps";
        }
    }

    @PostMapping("/obps/{id}/update")
    public String actualizarObp(@PathVariable("id") Integer id, @ModelAttribute Obp obp) {
        obp.setId(id);
        obpRepository.save(obp);
        return "redirect:/obps";
    }

    @GetMapping("/obps/{id}/delete")
    public String eliminarObp(@PathVariable("id") Integer id) {
        obpRepository.deleteById(id);
        return "redirect:/obps";
    }
}
