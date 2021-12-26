package com.example.demo.controller;

import com.example.demo.model.AccUser;
import com.example.demo.model.Color;
import com.example.demo.repository.UserRepository.UserRepository;
import com.example.demo.service.colorService.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class ColorsController {
    @Autowired
    private ColorService colorService;
    @Autowired
    private UserRepository userRepo;

    @ModelAttribute("userNames")
    public AccUser getDauGia() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.findByAccount_IdAccount(auth.getName());
    }

    @GetMapping("/listcolor")
    public String colorList(@RequestParam(defaultValue = "0") int page,
                            Optional<String> nameColor,Optional<String> nameProduct, Model model){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("admin", "là admin");
        }
        Pageable pageableSort = PageRequest.of(page, 5);
        if(!nameColor.isPresent()){
            if(nameProduct.isPresent()){
                model.addAttribute("nameColor",nameColor.get());
                model.addAttribute("colorlist",null);
            }else{
                model.addAttribute("colorlist",colorService.findAllPage(pageableSort));
            }
        }else{
            model.addAttribute("nameColor",nameColor.get());
            model.addAttribute("nameProduct",nameProduct.get());
            model.addAttribute("colorlist",null);
        }
        return "/nha/color/list";
    }
    @GetMapping("/color/delete/{idColor}")
    public String deleteColor(@PathVariable Integer idColor,RedirectAttributes redirectAttributesl){
        colorService.delete(idColor);
        redirectAttributesl.addFlashAttribute("mgsecolor", "Deleted!!");
        return "redirect:/admin/listcolor";
    }
    @GetMapping("/color/edit/{idColor}")
    public String editColor(@PathVariable int idColor,Model model){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("admin", "là admin");
        }
        model.addAttribute("color",colorService.findById(idColor));
        return "/nha/color/edit";
    }
//    @PostMapping( "/color/edit")
//    public String saveEditColor( Color color,RedirectAttributes redirectAttributes){
//        this.colorService.save(color);
//        redirectAttributes.addFlashAttribute("mgsecolor", "Updated " + color.getColor() +"success!");
//        return "redirect:/admin/listcolor";
//    }
}
