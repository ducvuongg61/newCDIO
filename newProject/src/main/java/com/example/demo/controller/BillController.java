package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.UserRepository.UserRepository;
import com.example.demo.service.categoryService.CategoryService;
import com.example.demo.service.colorService.ColorService;
import com.example.demo.service.commentService.CommentService;
import com.example.demo.service.product.ProductService;
import com.example.demo.service.productBillService.ProductBillService;
import com.example.demo.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
@SessionAttributes("carts")
public class BillController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductBillService productBillService;

//    @Autowired
//    AuctionUserService auctionUserService;
    @Autowired
    ColorService colorService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepo;

    @Autowired
    CommentService commentService;

    @ModelAttribute("carts")
    public HashMap<Integer, Cart> showInfo() {
        return new HashMap<>();
    }

    @ModelAttribute("userNames")
    public AccUser getDauGia() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.findByAccount_IdAccount(auth.getName());
    }

    @RequestMapping("/")
    public String index(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("admin", "là admin");
        }
        List<Category> categoryList;
        categoryList = categoryService.findAll();
        model.addAttribute("category", categoryList);
        model.addAttribute("MensFashion", productService.findByCategory("Đã duyệt", 1));
        model.addAttribute("WomanFashion", productService.findByCategory("Đã duyệt", 2));
        model.addAttribute("Accessory", productService.findByCategory("Đã duyệt", 3));
        model.addAttribute("Bags", productService.findByCategory("Đã duyệt", 4));
        model.addAttribute("Camera", productService.findByCategory("Đã duyệt", 5));
        model.addAttribute("FootwareMan", productService.findByCategory("Đã duyệt", 6));
        model.addAttribute("FootwareWoman", productService.findByCategory("Đã duyệt", 7));
        model.addAttribute("Health", productService.findByCategory("Đã duyệt", 8));
        model.addAttribute("Houseware", productService.findByCategory("Đã duyệt", 9));
        model.addAttribute("Laptop", productService.findByCategory("Đã duyệt", 10));
        model.addAttribute("Makeup", productService.findByCategory("Đã duyệt", 11));
        model.addAttribute("MotherAndBaby", productService.findByCategory("Đã duyệt", 12));
        model.addAttribute("Smartphone", productService.findByCategory("Đã duyệt", 13));
        model.addAttribute("Television", productService.findByCategory("Đã duyệt", 14));
        model.addAttribute("Watch", productService.findByCategory("Đã duyệt", 15));
        model.addAttribute("Sport", productService.findByCategory("Đã duyệt", 16));
        return "/nha/Home";
    }

    @RequestMapping("/afterLogin")
    public String afterLogin(Model model, Principal principal) {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("admin", "là admin");
        }
        List<Category> categoryList;
        categoryList = categoryService.findAll();
        model.addAttribute("category", categoryList);
        model.addAttribute("MensFashion", productService.findByCategory("Đã duyệt", 1));
        model.addAttribute("WomanFashion", productService.findByCategory("Đã duyệt", 2));
        model.addAttribute("Accessory", productService.findByCategory("Đã duyệt", 3));
        model.addAttribute("Bags", productService.findByCategory("Đã duyệt", 4));
        model.addAttribute("Camera", productService.findByCategory("Đã duyệt", 5));
        model.addAttribute("FootwareMan", productService.findByCategory("Đã duyệt", 6));
        model.addAttribute("FootwareWoman", productService.findByCategory("Đã duyệt", 7));
        model.addAttribute("Health", productService.findByCategory("Đã duyệt", 8));
        model.addAttribute("Houseware", productService.findByCategory("Đã duyệt", 9));
        model.addAttribute("Laptop", productService.findByCategory("Đã duyệt", 10));
        model.addAttribute("Makeup", productService.findByCategory("Đã duyệt", 11));
        model.addAttribute("MotherAndBaby", productService.findByCategory("Đã duyệt", 12));
        model.addAttribute("Smartphone", productService.findByCategory("Đã duyệt", 13));
        model.addAttribute("Television", productService.findByCategory("Đã duyệt", 14));
        model.addAttribute("Watch", productService.findByCategory("Đã duyệt", 15));
        model.addAttribute("Sport", productService.findByCategory("Đã duyệt", 16));
        return "redirect:/";
    }

    @RequestMapping("/productDetail/{id}")
    public String productDetailBill(@PathVariable int id, Model model, @SessionAttribute("carts") HashMap<Integer, Cart> cartMap){
        Product product;
        //truyền idproduct để hiện thị product
        product =  productService.findById(id);
        //Truyền idproduct để hiển thị nhiều color
        Color color = colorService.findById(id);
        List<Color> colorList = colorService.findByIdProduct(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("userName", auth.getName());
        }
        model.addAttribute("codeObject",color);
        model.addAttribute("color",colorList);
        model.addAttribute("product",product);
        model.addAttribute("cartMap",cartMap);
        return "Vinh/ProductDetail";
    }

    @RequestMapping("afterLogin/productDetail/{id}")
    public String afterLoginProductDetailBill(@PathVariable int id, Model model, @SessionAttribute("carts") HashMap<Integer, Cart> cartMap){
        Product product;
        product =  productService.findById(id);
        //Truyền idproduct để hiện color
        List<Color> colorList = colorService.findByIdProduct(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("userName", auth.getName());
        }
        model.addAttribute("color",colorList);
        model.addAttribute("product",product);
        model.addAttribute("cartMap",cartMap);
        return "redirect:/productDetail/" +id;
    }

    @GetMapping("/search")
    public String search(@RequestParam("idCategory") Integer idCategory,
                         @RequestParam("nameProduct") String nameProduct, Model model) {
        List<Category> categories = categoryService.findAll();
        List<Product> products;
        if (idCategory != 0) {
            if (!nameProduct.equals("")) {
                products = productService.findByCategoryAndNameProduct("Đã duyệt", idCategory, nameProduct);
            } else {
                products = productService.findByCategory("Đã duyệt", idCategory);
            }
        } else {
            if (!nameProduct.equals("")) {
                products = productService.findByNameApproved("Đã duyệt", nameProduct);
            } else {
                products = productService.findByApproved("Đã duyệt");
            }
        }
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("admin", "là admin");
        }
        if (categories.size() == 0 || products.size() == 0) {
            model.addAttribute("categories", categories);
            model.addAttribute("products", products);
            model.addAttribute("mgskt", "ko tìm thay");
            return "/nha/Home";
        } else {
            model.addAttribute("categories", categories);
            model.addAttribute("products", products);
            model.addAttribute("mgs", "Danh sách sp tìm thấy");
            return "/nha/Home";
        }
    }

}
