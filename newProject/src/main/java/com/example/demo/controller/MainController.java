package com.example.demo.controller;


import com.example.demo.model.AccUser;
import com.example.demo.model.Account;
import com.example.demo.model.Role;
import com.example.demo.repository.UserRepository.UserRepository;
import com.example.demo.service.accountService.AccountService;
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
        model.addAttribute("register", new AccUser());
        return "/nha/register";
    }

    @PostMapping(value = "/register")
    public String singUp(@Valid @ModelAttribute("register") AccUser accountUser, BindingResult bindingResult, Model model) {
//        new AccUser().validate(accountUser, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/nha/register";
        }
        Account account = accountService.findById(accountUser.getName());
        List<AccUser> email = userService.findByEmail(accountUser.getGmail());
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

        Set<Role> roles = roleService.findByRoleName("ROLE_CUSTOMER");
        System.out.println("quyền là  " + roles);
        AccUser user = new AccUser();
        user.setName(accountUser.getName());
        Account account1 =new Account(accountUser.getName(), bCryptPasswordEncoder.encode(accountUser.getAccount().getPassword()), roles,true);
        user.setAccount(account1);
        user.setGmail(accountUser.getGmail());
        user.setDateOfBirth(accountUser.getDateOfBirth());
        user.setPhoneUser(accountUser.getPhoneUser());
        user.setNumberCard(accountUser.getNumberCard());
        user.setAddress(accountUser.getAddress());
        user.setSex(accountUser.isSex());
        userService.save(user);
        System.out.println("user is : ==========" + user);
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
