package com.example.demo.controller;

import com.example.demo.model.AccUser;
import com.example.demo.model.Auction;
import com.example.demo.repository.UserRepository.UserRepository;
import com.example.demo.service.auctionService.AuctionService;
import com.example.demo.service.product.ProductService;
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
public class AdminAuctionController {
    @Autowired
    AuctionService auctionService;


    @Autowired
    ProductService productService;

    @Autowired
    UserRepository userRepo;
    @ModelAttribute("userNames")
    public AccUser getDauGia() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.findByAccount_IdAccount(auth.getName());
    }

    @GetMapping("/auction/list")
    public String listAuction(@RequestParam(defaultValue = "0") int page,
                              Optional<String> nameProduct, Optional<String> auctionTime, Model model){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("admin", "là admin");
        }
        Pageable pageableSort = PageRequest.of(page, 5);
        if(!nameProduct.isPresent()){
            if(auctionTime.isPresent()){
                model.addAttribute("nameAuction",auctionTime.get());
                model.addAttribute("auctionlist",auctionService.findByAuctionTimeContains(auctionTime.get(),pageableSort));
            }else{
                model.addAttribute("auctionlist",auctionService.findByAllPage(pageableSort));
            }
        }else{
            if(auctionTime.isPresent()){
                model.addAttribute("nameColor",nameProduct.get());
                model.addAttribute("nameProduct",auctionTime.get());
                model.addAttribute("auctionlist",null);
            }else{
                model.addAttribute("nameAuction",nameProduct.get());
                model.addAttribute("auctionlist",null);
            }
        }
        return "/nha/admin/auction/list";
    }

    @GetMapping("/auction/delete/{idAuction}")
    public String deleteAuction(@PathVariable int idAuction, RedirectAttributes redirectAttributesl){
        Auction auction = auctionService.findById(idAuction);
        productService.delete(auction.getProduct().getIdProduct());
        redirectAttributesl.addFlashAttribute("mgseauction", "Deleted!");
        return "redirect:/admin/auction/list";
    }

    @GetMapping("/auction/edit/{idAuction}")
    public String editAuction(@PathVariable int idAuction, Model model){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("admin", "là admin");
        }
        model.addAttribute("auction",auctionService.findById(idAuction));
        return "/nha/admin/auction/edit";
    }

    @PostMapping("/auction/edit")
    public String submitEditAuction(Auction auction,RedirectAttributes redirectAttributes){
        auctionService.save(auction);
        redirectAttributes.addFlashAttribute("mgseauction" , "Update " +auction.getProduct().getProductName()+"success");
        return "redirect:/admin/auction/list";
    }


    

}
