package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.UserRepository.UserRepository;
import com.example.demo.service.accountService.AccountService;
import com.example.demo.service.categoryService.CategoryService;
import com.example.demo.service.colorService.ColorService;
import com.example.demo.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    UserRepository userRepo;

    @Autowired
    ProductService productService;

    @Autowired
    AccountService accountService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ColorService colorService;


    @GetMapping(value = "/product/list")
    public String user(Model model, Principal principal, @PageableDefault(size = 5) Pageable pageable) {
        String userName = principal.getName();
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_SALER"))) {
            model.addAttribute("saler", "là saler");
        }
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        model.addAttribute("userNames", user);
        model.addAttribute("listProduct", colorService.findAllProduct(userName));
        return "/vuong/ListProductSaler";
    }

    @GetMapping(value = "/product/listApproved")
    public String userNotApprovedYet(Product product, Model model, Principal principal) {
        String userName = principal.getName();
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("admin", "là admin");
        }
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        model.addAttribute("userNames", user);
        model.addAttribute("listSP", colorService.findAllApprovedProduct("Chưa duyệt", userName));
        return "/vuong/listchuaduyet";
    }

    @GetMapping(value = "/product/upProductMoney")
    public String formUpdateProduct(Product product, Model model , Principal principal) {
        String userName = principal.getName();
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().
                anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_SALER"))) {
            model.addAttribute("saler" , "là saler");
        }
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        model.addAttribute("listSP", productService.findAllByNotApprovedYet("No money", userName));
        return "/vuong/listNoUpdateMoney";
    }

    @GetMapping(value = "/product/waitList")
    public String formWaitList(Color color, Model model , Principal principal) {
        String userName = principal.getName();
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().
                anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_SALER"))) {
            model.addAttribute("saler" , "là saler");
        }
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        model.addAttribute("listProductColor", colorService.findByProduct_Status("No money"));
        return "/vuong/waitList";
    }


    @GetMapping(value = "/product/NotApprove")
    public String userNotApprove(Product product, Model model, Principal principal) {
        String userName = principal.getName();
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("admin", "là admin");
        }
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        model.addAttribute("userNames", user);
        model.addAttribute("listSP", productService.findAllByNotApprovedYet("Không duyệt", userName));
        return "/vuong/listkhongduyet";
    }

    @GetMapping(value = "/product/create")
    public String viewCreate(Model model, Principal principal) {
        Product product = new Product();
        LocalDate localDate = LocalDate.now();
        product.setDatePost(localDate.toString());
        model.addAttribute("products", product);
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_SALER"))) {
            model.addAttribute("saler", "là saler");
        }
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        model.addAttribute("userNames", user);
        model.addAttribute("userName", principal.getName());
        model.addAttribute("account", accountService.findAll());
        model.addAttribute("category", categoryService.findAll());
        model.addAttribute("color" , colorService.findAll());
        model.addAttribute("notApprovedYet", "No money");
        return "/vuong/create_nguoidung";
    }

    @PostMapping(value = "/product/create")
    public String create(@Valid @ModelAttribute("products") Product product, BindingResult bindingResult, Model model, Principal principal) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        System.out.println(LocalDateTime.now());
        String dateFormat = format.format(date);
        System.out.println("create product" + product);
        String idAccount = principal.getName();
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_SALER"))) {
            model.addAttribute("saler", "là saler");
        }
        product.setAccounts(new Account(idAccount));
        if (bindingResult.hasFieldErrors()) {
            AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
            model.addAttribute("userNames", user);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("account", accountService.findAll());
            model.addAttribute("category", categoryService.findAll());
            model.addAttribute("color" , colorService.findAll());
            model.addAttribute("notApprovedYet", "notUpdate");
            return "/vuong/create_nguoidung";
        }
        this.productService.save(product);
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        model.addAttribute("userNames", user);
        model.addAttribute("listProduct", colorService.findAllProduct(idAccount));
        model.addAttribute("mgs", "thêm mới sản phẩm thành công");
        return "/vuong/ListProductSaler";
    }

    @GetMapping(value = "/product/view")
    public String viewView(@RequestParam("id") Integer id, Model model, Principal principal) {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_SALER"))) {
            model.addAttribute("admin", "là admin");
        }
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        model.addAttribute("userNames", user);
        model.addAttribute("products", productService.findById(id));
        model.addAttribute("category", categoryService.findAll());
        return "/vuong/view";
    }

    @GetMapping(value = "/product/productDone")
    public String doneProduct(Model model , Principal principal) {
        Color color = new Color();
        String userName = principal.getName();
        model.addAttribute("colors" , color);
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_SALER"))) {
            model.addAttribute("saler", "là saler");
        }
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        model.addAttribute("userNames", user);
        model.addAttribute("userName", principal.getName());
        model.addAttribute("account", accountService.findAll());
        model.addAttribute("product" , productService.findAll());
        model.addAttribute("productNoMoney", productService.findAllByNotApprovedYet("No money", userName));
        model.addAttribute("stocking", "Còn hàng");
        return "/vuong/create_color";
    }

    @PostMapping(value = "/product/productDone")
    public String createColor(@ModelAttribute("colors") Color color , BindingResult bindingResult , Model model , Principal principal){
        String idAccount = principal.getName();
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_SALER"))) {
            model.addAttribute("saler", "là saler");
        }
        if (bindingResult.hasFieldErrors()) {
            AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
            model.addAttribute("userNames", user);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("notApprovedYet", "notUpdate");
            return "/vuong/create_color";
        }
        this.colorService.save(color);
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        model.addAttribute("userNames", user);
        model.addAttribute("listProduct", colorService.findAllProduct(idAccount));
        model.addAttribute("mgs", "thêm mới sản phẩm thành công");
        return "/vuong/ListProductSaler";
    }

    @GetMapping(value = "product/edit")
    public String viewEdit(@RequestParam("id") Integer id, Model model, Principal principal) {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_SALER"))) {
            model.addAttribute("saler", "là saler");
        }
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        model.addAttribute("userNames", user);
        model.addAttribute("products", productService.findById(id));
        model.addAttribute("category", categoryService.findAll());
        model.addAttribute("notApprovedYet11" , "Chưa duyệt");
        model.addAttribute("waiting" , "No money");
        return "/vuong/edit";
    }

    @PostMapping(value = "/product/edit")
    public String Edit(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model, Principal principal) {
        String idAccount = principal.getName();
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("admin", "là admin");
        }
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
//        new Product().validate(product, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("userNames", user);
            model.addAttribute("category", categoryService.findAll());
            return "/vuong/edit";
        }
        model.addAttribute("userNames", user);
        model.addAttribute("product", colorService.findAllProduct(idAccount));
        model.addAttribute("category", categoryService.findAll());
        this.productService.save(product);
        model.addAttribute("mgsedit", "Sửa sản phẩm thành công");
        System.out.println("userName ------ " + product.getAccounts());
        System.out.println("ten -----------" + product.getStatus());
        System.out.println("ten -----------" + product.getProductName());
        return "/vuong/ListProductSaler";
    }

    @GetMapping(value = "/product/delete/{idProduct}")
    public String deleteProduct(@PathVariable Integer idProduct, Model model, Principal principal) {
        this.productService.delete(idProduct);
        String idAccount = principal.getName();
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_SALER"))) {
            model.addAttribute("saler", "là saler");
        }
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        model.addAttribute("userNames", user);
        model.addAttribute("listProduct", colorService.findAllProduct(idAccount));
        model.addAttribute("mgsdelete", "Xóa sản phẩm thành công!");
        return "/vuong/ListProductSaler";
    }

    @GetMapping("product/searchWaitingApproval")
    public String searchWaitingApproval(@RequestParam("nameProduct") String nameProduct, Model model, Principal principal) {
        String userName = principal.getName();
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_SALER"))) {
            model.addAttribute("saler", "là saler");
        }
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        List<Product> products = productService.findByNotApprovedYet("Chưa duyệt", userName, nameProduct);
        if (products.size() == 0) {
            System.out.println("Đang rỗng nè coi đo dai bao nhieu nao  ====================" + products.size());
            model.addAttribute("userNames", user);
            model.addAttribute("products", products);
            model.addAttribute("mgstk", "Không tìm thấy sản phẩm");
            return "/vuong/listchuaduyet";
        } else {
            System.out.println("Đang rỗng nè coi đo dai bao nhieu nao  ====================" + products.size());
            model.addAttribute("userNames", user);
            model.addAttribute("products", products);
            model.addAttribute("mgstk1", "sản phẩm được tìm thấy");
            return "/vuong/listchuaduyet";
        }
    }

    @GetMapping("/searchMine")
    public String search(@RequestParam("nameProduct") String nameProduct,
                         Model model, Principal principal) {
        String idAccount = principal.getName();
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_SALER"))) {
            model.addAttribute("saler", "là saler");
        }
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        List<Product> products = productService.findByNameProduct(idAccount, nameProduct);
        if (products.size() == 0) {
            System.out.println("Đang rỗng nè coi đo dai bao nhieu nao  ====================" + products.size());
            model.addAttribute("userNames", user);
            model.addAttribute("products", products);
            model.addAttribute("mgstk", "Không tìm thấy sản phẩm");
            return "/vuong/ListProductSaler";
        } else {
            System.out.println("Đang rỗng nè coi đo dai bao nhieu nao  ====================" + products.size());
            model.addAttribute("userNames", user);
            model.addAttribute("products", products);
            model.addAttribute("mgstk1", "sản phẩm được tìm thấy");
            return "/vuong/ListProductSaler";
        }
    }
}
