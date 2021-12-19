package com.example.demo.controller;


import com.example.demo.model.*;
import com.example.demo.repository.UserRepository.UserRepository;
import com.example.demo.repository.auctionRepository.AuctionRepository;
import com.example.demo.repository.colorRepository.ColorRepository;
import com.example.demo.repository.productBillRepository.ProductRepository;
import com.example.demo.service.auctionService.AuctionService;
import com.example.demo.service.auctionUserService.AuctionUserService;
import com.example.demo.service.categoryService.CategoryService;
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
import java.sql.Time;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
@SessionAttributes("carts")
public class AuctionController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductBillService productBillService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepo;
    @Autowired
    CommentService commentService;
    @Autowired
    AuctionService auctionService;
    @Autowired
    ColorRepository colorRepository;
    @Autowired
    AuctionUserService auctionUserService;

    @Autowired
    ProductRepository productRepository;
    @ModelAttribute("carts")
    public HashMap<Integer, Cart> showInfo() {
        return new HashMap<>();
    }

    @ModelAttribute("userNames")
    public AccUser getDauGia() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.findByAccount_IdAccount(auth.getName());
    }

    @RequestMapping("/auction")
    public String index(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("admin", "là admin");
        }
        List<Category> categoryList;
        categoryList = categoryService.findAll();
        model.addAttribute("category", categoryList);
        model.addAttribute("MensFashion", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 1));
        model.addAttribute("WomanFashion", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 2));
        model.addAttribute("Accessory", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 3));
        model.addAttribute("Bags", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 4));
        model.addAttribute("Camera", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 5));
        model.addAttribute("FootwareMan", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 6));
        model.addAttribute("FootwareWoman", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 7));
        model.addAttribute("Health", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 8));
        model.addAttribute("Houseware", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 9));
        model.addAttribute("Laptop", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 10));
        model.addAttribute("Makeup", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 11));
        model.addAttribute("MotherAndBaby", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 12));
        model.addAttribute("Smartphone", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 13));
        model.addAttribute("Television", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 14));
        model.addAttribute("Watch", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 15));
        model.addAttribute("Sport", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 16));
        return "/nha/auction/Home";
    }
    @RequestMapping("/afterLogin/auction/")
    public String afterLogin(Model model, Principal principal) {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("admin", "là admin");
        }
        List<Category> categoryList;
        categoryList = categoryService.findAll();
        model.addAttribute("category", categoryList);
        model.addAttribute("MensFashion", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 1));
        model.addAttribute("WomanFashion", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 2));
        model.addAttribute("Accessory", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 3));
        model.addAttribute("Bags", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 4));
        model.addAttribute("Camera", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 5));
        model.addAttribute("FootwareMan", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 6));
        model.addAttribute("FootwareWoman", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 7));
        model.addAttribute("Health", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 8));
        model.addAttribute("Houseware", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 9));
        model.addAttribute("Laptop", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 10));
        model.addAttribute("Makeup", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 11));
        model.addAttribute("MotherAndBaby", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 12));
        model.addAttribute("Smartphone", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 13));
        model.addAttribute("Television", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 14));
        model.addAttribute("Watch", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 15));
        model.addAttribute("Sport", productRepository.findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice("Đã duyệt", 16));
        return "redirect:/auction";
    }
    @RequestMapping("/auction-detail/{id}")
    public String producDetail(@PathVariable int id, Model model, @SessionAttribute("carts") HashMap<Integer, Cart> cartMap) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Auction auction = auctionService.findByProduct(id);
        List<AuctionUser> detailList = auctionUserService.findByProduct(id);
        Color colors = colorRepository.findByProduct_IdProduct(id);
        double giaCaoNhat = 0;
        if (!detailList.isEmpty()) {
            giaCaoNhat = detailList.get(0).getStartingPrice();
            model.addAttribute("nguoiCaoNhat", detailList.get(0));
        }
        //kiem tra xem neu chua dang nhap thi doi thanh button dang nhap
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("userName", auth.getName());
        } else {
            for (AuctionUser auctionUser : detailList) {
                if (auctionUser.getUsers().getAccount().getIdAccount().equals(auth.getName())) {
                    if (auctionUser.getStartingPrice() == giaCaoNhat) {
                        model.addAttribute("winner", userService.findByAccount(auth.getName()));
                    }
                }
            }
        }


        //gia dau cao nhat cong voi gia khoi diem
        double giaDau = giaCaoNhat + 5;
        model.addAttribute("cartMap", cartMap);
        model.addAttribute("sanPham", productService.findById(id));
        model.addAttribute("giaCaoNhat", giaCaoNhat);
        model.addAttribute("giaDau", giaDau);
        model.addAttribute("dauGia", detailList);
        model.addAttribute("color",colors);
        model.addAttribute("producTimeEnd",auction.getAuctionTime());
        return "nha/auction/ProductDetail";
    }
    @RequestMapping("/afterLogin/auction-detail/{id}")
    public String afterLoginproducDetail(@PathVariable int id, Model model, @SessionAttribute("carts") HashMap<Integer, Cart> cartMap) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Auction auction = auctionService.findByProduct(id);
        List<AuctionUser> detailList = auctionUserService.findByProduct(id);
        Color colors = colorRepository.findByProduct_IdProduct(id);
        double giaCaoNhat = 0;
        if (!detailList.isEmpty()) {
            giaCaoNhat = detailList.get(0).getStartingPrice();
            model.addAttribute("nguoiCaoNhat", detailList.get(0));
        }
        //kiem tra xem neu chua dang nhap thi doi thanh button dang nhap
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("userName", auth.getName());
        } else {
            for (AuctionUser auctionUser : detailList) {
                if (auctionUser.getUsers().getAccount().getIdAccount().equals(auth.getName())) {
                    if (auctionUser.getStartingPrice() == giaCaoNhat) {
                        model.addAttribute("winner", userService.findByAccount(auth.getName()));
                    }
                }
            }
        }
        //gia dau cao nhat cong voi gia khoi diem
        double giaDau = giaCaoNhat + auction.getPriceJump();
        model.addAttribute("cartMap", cartMap);
        model.addAttribute("sanPham", productService.findById(id));
        model.addAttribute("giaCaoNhat", giaCaoNhat);
        model.addAttribute("giaDau", giaDau);
        model.addAttribute("dauGia", detailList);
        model.addAttribute("color",colors);
        model.addAttribute("producTimeEnd",auction.getAuctionTime());
        return "redirect:/auction-detail/"+id;
    }
    @PostMapping("/dauGia")
    public String dauGia(@RequestParam int idSP, double money, Principal principal) {
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        Auction auction = auctionService.findByProduct(idSP);
        if (auction == null) {
            auction = new Auction();
            auction.setProduct(productService.findById(idSP));
            auctionService.save(auction);
        }
        //lay thoi gian hien tai
        Time time = new Time(System.currentTimeMillis());
        //them 2 thuoc tinh khoa
        AuctionUser auctionUser = new AuctionUser();
        auctionUser.setAuctions(auction);
        auctionUser.setUsers(user);
        auctionUser.setAuctionEndTime(time);
        auctionUser.setStartingPrice(money);
        auctionUserService.create(auctionUser);
        return "redirect:/auction-detail/" + idSP;
    }
    @GetMapping("/timKiem")
    public String search(@RequestParam("maDanhMuc") Integer maDanhMuc,
                         @RequestParam("tenSp") String tenSp, Model model) {
        List<Category> categories = categoryService.findAll();
        List<Product> products;
        if (maDanhMuc != 0) {
            if (!tenSp.equals("")) {
                products = productService.findByCategoryAndNameProduct("Đã duyệt", maDanhMuc, tenSp);
            } else {
                products = productService.findByCategory("Đã duyệt", maDanhMuc);
            }
        } else {
            if (!tenSp.equals("")) {
                products = productService.findByNameApproved("Đã duyệt", tenSp);
            } else {
                products = productService.findByApproved("Đã duyệt");
            }
        }
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("admin", "là admin");
        }
        if (categories.size() == 0 || products.size() == 0) {
            model.addAttribute("danhmucs", categories);
            model.addAttribute("listSP", products);
            model.addAttribute("mgskt", "ko tìm thay");
            return "/nha/auction/Home";
        } else {
            model.addAttribute("danhmucs", categories);
            model.addAttribute("listSP", products);
            model.addAttribute("mgs", "Danh sách sp tìm thấy");
            return "/nha/auction/Home";
        }
    }

}
