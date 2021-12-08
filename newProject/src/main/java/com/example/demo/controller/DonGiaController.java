package com.example.demo.controller;

import com.example.demo.model.AccUser;
import com.example.demo.model.ProductBill;
import com.example.demo.repository.UserRepository.UserRepository;
import com.example.demo.service.productBillService.BillService;
import com.example.demo.service.productBillService.ProductBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/nhas")
public class DonGiaController {
    @Autowired
    ProductBillService productBillService;
    @Autowired
    BillService billService;
    @Autowired
    UserRepository userRepo;

    @ModelAttribute("userNames")
    public AccUser getDauGia() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.findByAccount_IdAccount(auth.getName());
    }

    @RequestMapping("/dongia")
    public ModelAndView listAll(@RequestParam(defaultValue = "0") int page){
        ModelAndView model = new ModelAndView("/nha/DonGia");
        Page<ProductBill> productBillList;
        Pageable pageable = PageRequest.of(page, 5);
        productBillList = productBillService.findAll(pageable);
        model.addObject("listDonGia",productBillList);
        return model;
    }

    @PostMapping("/pagaList")
    public ModelAndView getList(@RequestParam(defaultValue = "0") int page, @RequestParam String nameUser,
                                @RequestParam String nameProduct) {
        ModelAndView modelAndView = new ModelAndView("/nha/DonGia");
        Page<ProductBill> productBills;
        Pageable pageableSort = PageRequest.of(page, 999);
        if (nameUser.equals("")) {
            if (!nameProduct.equals("")) {
                productBills = productBillService.findByProduct_ProductNameContains(nameProduct, pageableSort);
            } else {
                productBills = productBillService.findAll(pageableSort);
            }
        } else {
            if (!nameProduct.equals("")) {
                productBills = productBillService.findByProduct_ProductNameAndBill_idAccountContains(nameUser, nameProduct, pageableSort);
            } else {
                productBills = productBillService.findByBill_idAccountContains(nameUser, pageableSort);
            }
        }
        modelAndView.addObject("nameUser", nameUser);
        modelAndView.addObject("productBills", productBills);
        modelAndView.addObject("nameProduct", nameProduct);
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        billService.delete(id);
        return "redirect:/nhas/dongia";
    }

}
