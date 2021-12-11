package com.example.demo.controller;


import com.example.demo.model.*;
import com.example.demo.repository.UserRepository.UserRepository;
import com.example.demo.service.accountService.AccountService;
import com.example.demo.service.addressService.AddressService;
import com.example.demo.service.roleService.RoleService;
import com.example.demo.service.userService.UserService;
import com.example.demo.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("")
public class MainController {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepo;

    @Autowired
    AccountService accountService;

    @Autowired
    RoleService roleService;

    @Autowired
    AddressService addressService;

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        // 1 cái util( dùng chung) dùng để hiển thị principal
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "/nha/userInfoPage";
    }

    @GetMapping(value = "/register")
    public String viewSignUp(Model model) {
        model.addAttribute("register", new AccountUser());
        return "/nha/register";
    }

    @PostMapping(value = "/register")
    public String singUp(@Valid @ModelAttribute("register") AccountUser accountUser, BindingResult bindingResult, Model model) {
        new AccountUser().validate(accountUser, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/nha/register";
        }
        Account account = accountService.findById(accountUser.getUserName1());
        List<AccUser> email = userService.findByEmail(accountUser.getGmail1());
        if (account != null && email.size() != 0) {
            model.addAttribute("errTK", "Ten tai khoan da ton tai");
            model.addAttribute("errEmail", "Email da ton tai");
            return "/nha/register";
        }
        if (account != null) {
            model.addAttribute("errTK", "Ten tai khoan da ton tai");
            return "/nha/register";
        }
        if (email.size() != 0) {
            model.addAttribute("errEmail", "Email da ton tai");
            return "/nha/register";
        }
        Set<Role> roles = roleService.findByRoleName("ROLE_CUSTOMER") ;
        System.out.println("quyền là  " + roles);

        Address address = new Address();
//        user.setName(accountUser.getName1());
        Account account1 =new Account(accountUser.getUserName1(), bCryptPasswordEncoder.encode(accountUser.getPassWord1()),true, roles);
//        user.setAccount(account1);
//        user.setGmail(accountUser.getGmail1());
//        user.setDateOfBirth(accountUser.getDateTime1());
//        user.setPhoneUser(accountUser.getPhoneUser1());
//        user.setNumberCard(accountUser.getNumberCard1());
        AccUser user = new AccUser(accountUser.getName1(),Boolean.parseBoolean(accountUser.getSex1()),accountUser.getDateTime1(),accountUser.getGmail1(),
                accountUser.getNumberCard1(),accountUser.getPhoneUser1(),account1);
        address.setAccUser(user);
        address.setNameAddress(accountUser.getAddress1());



////        user.getAddress().add(address);
//        userService.save(user);
//        address.setNameAddress(accountUser.getAddress1());
        addressService.save(address);
        System.out.println("nguoi dun  ==========" + user);
        return "redirect:/login";
    }

    @GetMapping("/403")
    private String accessDenied(Model model, Principal principal) {
        AccUser user = userService.findByAccount(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("mgs", "Bạn không có quyền truy cập !");
        return "/Vinh/ErrorPage";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "/nha/login";
    }
}
