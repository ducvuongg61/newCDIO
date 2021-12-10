package com.example.demo.controller;

import com.example.demo.model.AccUser;
import com.example.demo.model.Account;
import com.example.demo.model.Product;
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
        model.addAttribute("listProduct", productService.findProduct(userName));
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
        model.addAttribute("listSP", productService.findAllByNotApprovedYet("Chưa duyệt", userName));
        return "/vuong/listchuaduyet";
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
//        LocalDate localDate = LocalDate.now();
//        product.setDateAuction(localDate.toString());
        Product product = new Product();
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
        model.addAttribute("notApprovedYet", "Chưa duyệt");
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
//        product.setStatus("Chưa duyệt");
//        product.setDateAuction(dateFormat);
        product.setAccounts(new Account(idAccount));
//        new Product().validate(product, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
            model.addAttribute("userNames", user);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("account", accountService.findAll());
            model.addAttribute("category", categoryService.findAll());
            model.addAttribute("color" , colorService.findAll());
            model.addAttribute("notApprovedYet", "Chưa duyệt");
            return "/vuong/create_nguoidung";
        }
        this.productService.save(product);
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        model.addAttribute("userNames", user);
        model.addAttribute("listProduct", productService.findAccount(idAccount));
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
        return "/vuong/edit";
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
        model.addAttribute("product", productService.findAccount(idAccount));
        model.addAttribute("category", categoryService.findAll());
//        System.out.println("ten -----------" + product.getAccounts().getUserName());
        this.productService.save(product);
        model.addAttribute("mgsedit", "Sửa sản phẩm thành công");
        System.out.println("userName ------ " + product.getAccounts());
//        System.out.println("ten -----------" + product.getDateAuction());
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
        model.addAttribute("listProduct", productService.findAccount(idAccount));
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
