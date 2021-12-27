package com.example.demo.controller;

import com.example.demo.model.AccUser;
import com.example.demo.model.Address;
import com.example.demo.model.Role;
import com.example.demo.repository.UserRepository.UserRepository;
import com.example.demo.service.addressService.AddressService;
import com.example.demo.service.userService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AccountAdminController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserRepository userRepo;

    @Autowired
    AddressService addressService;

    @RequestMapping("/account")
    public String listAll(@RequestParam(defaultValue = "0") int page, Principal principal, Model model){
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        model.addAttribute("userNames",user);
        Page<AccUser> userPage;
        Pageable pageable = PageRequest.of(page,5);
        userPage = userService.findByAlAndAccountRoleAndAccountAndAccUserAndAddress(pageable);
        Page<Address> userPages;
        Pageable pageables = PageRequest.of(page,5);
        userPages = addressService.findAll(pageables);
        model.addAttribute("users",userPage);
        model.addAttribute("addresses",userPages);
        return "/nha/admin/AccountAdmin";
    }

    @PostMapping("/filter")
    public String getList(@RequestParam(defaultValue = "0") int page, @RequestParam String nameUser, @RequestParam String address,
                          Principal principal, Model model) {
//        Page<AccUser> users;
        Page<Address> addresses;
        Pageable pageableSort = PageRequest.of(page, 5);
         //tim kiem
        if (nameUser.equals("")) {
            if (!address.equals("")) {
                addresses = addressService.findByNameAddress(address, pageableSort);
            } else {
                addresses = addressService.findAll(pageableSort);
            }
        } else {
            if (!address.equals("")) {
                addresses = addressService.findByNameAddressAndNameUser(nameUser, address, pageableSort);
            } else {
                addresses = addressService.findByNameUser(nameUser, pageableSort);
            }
        }
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        model.addAttribute("addresses", addresses);
        model.addAttribute("nameUser", nameUser);
        model.addAttribute("address",address);
        model.addAttribute("userNames",user);
        return "/nha/admin/AccountAdmin";
    }

    @GetMapping("/add_member")
    public String create(Model model, Principal principal) {
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        model.addAttribute("userNames", user);
        model.addAttribute("user", new AccUser());
        return "nha/admin/AccountAdd";
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        userService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Deleted!!");
        return "redirect:/admin/account";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("nguoiDung1") AccUser users, RedirectAttributes redirectAttributes, Model model, Principal principal) {
        System.out.println(users.getIdUser());
        userService.save(users);
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        model.addAttribute("userNames", user);
        redirectAttributes.addFlashAttribute("success", "Created!");
        return "redirect:/admin/account";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model, Principal principal) {
        AccUser user = userService.findById(id);
        AccUser user1 = userRepo.findByAccount_IdAccount(principal.getName());
        model.addAttribute("userNames", user1);
        model.addAttribute("findUser", userService.findById(id));
        model.addAttribute("userName", user.getName());
        return "nha/admin/AccountEdit";
    }

    @PostMapping("/edit_member")
    public String edit(@ModelAttribute("user") AccUser user, Model model, RedirectAttributes redirectAttributes, Principal principal) {
        userService.save(user);
        AccUser user1 = userRepo.findByAccount_IdAccount(principal.getName());
        model.addAttribute("userNames", user1);
        redirectAttributes.addFlashAttribute("success", "Updated!");
        model.addAttribute("userName", user.getName());
        return "redirect:/admin/account";
    }


}
