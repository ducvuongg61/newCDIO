package com.example.demo.controller;

import com.example.demo.model.AccUser;
import com.example.demo.model.Category;
import com.example.demo.repository.UserRepository.UserRepository;
import com.example.demo.service.categoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserRepository userRepo;

    @ModelAttribute("userNames")
    public AccUser getDauGia() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.findByAccount_IdAccount(auth.getName());
    }
    
    @GetMapping(value = "/category/list")
    private String viewList(Model model, Principal principal) {
    
        model.addAttribute("category", categoryService.findAll());
        return "/nha/category/list";
    }

    @GetMapping(value = "/category/create")
    private String viewCreate(Model model, Principal principal) {
    
        model.addAttribute("categorys", new Category());
        return "/nha/category/create";
    }

    @PostMapping(value = "/category/create")
    private String Create(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasFieldErrors()) {
            return "/nha/category/create";
        }
        List<Category> categoryList = categoryService.findByName(category.getCategoryName());

        if ( categoryList.size() != 0) {
            model.addAttribute("mgsdm", "Danh mục đã tồn tại.");
            return "/nha/category/create";
        }
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("mgsedit", "Thêm mới danh mục thành công.");
        return "redirect:/admin/category/list";
    }

    @GetMapping(value = "/category/edit")
    public String ViewEdit(@RequestParam("id") Integer id, Model model, Principal principal) {
        model.addAttribute("category", categoryService.findById(id));
        return "/nha/category/edit";
    }

    @PostMapping(value = "/category/edit")
    public String Edit(Category category, Model model, Principal principal) {
        this.categoryService.save(category);
        model.addAttribute("mgsedit", "Sửa danh mục thành công.");
        model.addAttribute("category", categoryService.findAll());
        return "/nha/category/list";
    }

    @GetMapping(value = "/category/delete/{idCategory}")
    public String delete(@PathVariable int idCategory, Model model, Principal principal) {
    
        this.categoryService.delete(idCategory);
        model.addAttribute("mgsedit", "Xóa danh mục thành công.");
        return "redirect:/admin/category/list";
    }
}
