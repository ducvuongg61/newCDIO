package com.example.demo.controller;


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
//    @Autowired
//    ProductService productService;
//    @Autowired
//    CategoryService categoryService;
//    @Autowired
//    ProductBillService productBillService;
//    @Autowired
//    AuctionUserService auctionUserService;
//    @Autowired
//    UserService userService;
//    @Autowired
//    UserRepo userRepo;
//    @Autowired
//    CommentService commentService;
//    @Autowired
//    AuctionRepo auctionRepo;
//
//
//    @ModelAttribute("carts")
//    public HashMap<Integer, Cart> showInfo() {
//        return new HashMap<>();
//    }
//
//    @ModelAttribute("nguoiDung")
//    public User getDauGia() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        return userRepo.findByAccount_UserName(auth.getName());
//    }
//
//    @RequestMapping("/auction")
//    public String index(Model model) {
//        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
//                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
//            model.addAttribute("admin", "là admin");
//        }
//        List<Category> categoryList;
//        categoryList = categoryService.findAll();
//        model.addAttribute("category", categoryList);
//        model.addAttribute("MensFashion", productService.findByCategory("Đã duyệt", 1));
//        model.addAttribute("WomanFashion", productService.findByCategory("Đã duyệt", 2));
//        model.addAttribute("Accessory", productService.findByCategory("Đã duyệt", 3));
//        model.addAttribute("Bags", productService.findByCategory("Đã duyệt", 4));
//        model.addAttribute("Camera", productService.findByCategory("Đã duyệt", 5));
//        model.addAttribute("FootwareMan", productService.findByCategory("Đã duyệt", 6));
//        model.addAttribute("FootwareWoman", productService.findByCategory("Đã duyệt", 7));
//        model.addAttribute("Health", productService.findByCategory("Đã duyệt", 8));
//        model.addAttribute("Houseware", productService.findByCategory("Đã duyệt", 9));
//        model.addAttribute("Laptop", productService.findByCategory("Đã duyệt", 10));
//        model.addAttribute("Makeup", productService.findByCategory("Đã duyệt", 11));
//        model.addAttribute("MotherAndBaby", productService.findByCategory("Đã duyệt", 12));
//        model.addAttribute("Smartphone", productService.findByCategory("Đã duyệt", 13));
//        model.addAttribute("Television", productService.findByCategory("Đã duyệt", 14));
//        model.addAttribute("Watch", productService.findByCategory("Đã duyệt", 15));
//        model.addAttribute("Sport", productService.findByCategory("Đã duyệt", 16));
//        return "/nha/auction/Home";
//    }
//    @RequestMapping("/afterLogin/auction/")
//    public String afterLogin(Model model, Principal principal) {
//        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
//                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
//            model.addAttribute("admin", "là admin");
//        }
//        List<Category> categoryList;
//        categoryList = categoryService.findAll();
//        model.addAttribute("category", categoryList);
//        model.addAttribute("MensFashion", productService.findByCategory("Đã duyệt", 1));
//        model.addAttribute("WomanFashion", productService.findByCategory("Đã duyệt", 2));
//        model.addAttribute("Accessory", productService.findByCategory("Đã duyệt", 3));
//        model.addAttribute("Bags", productService.findByCategory("Đã duyệt", 4));
//        model.addAttribute("Camera", productService.findByCategory("Đã duyệt", 5));
//        model.addAttribute("FootwareMan", productService.findByCategory("Đã duyệt", 6));
//        model.addAttribute("FootwareWoman", productService.findByCategory("Đã duyệt", 7));
//        model.addAttribute("Health", productService.findByCategory("Đã duyệt", 8));
//        model.addAttribute("Houseware", productService.findByCategory("Đã duyệt", 9));
//        model.addAttribute("Laptop", productService.findByCategory("Đã duyệt", 10));
//        model.addAttribute("Makeup", productService.findByCategory("Đã duyệt", 11));
//        model.addAttribute("MotherAndBaby", productService.findByCategory("Đã duyệt", 12));
//        model.addAttribute("Smartphone", productService.findByCategory("Đã duyệt", 13));
//        model.addAttribute("Television", productService.findByCategory("Đã duyệt", 14));
//        model.addAttribute("Watch", productService.findByCategory("Đã duyệt", 15));
//        model.addAttribute("Sport", productService.findByCategory("Đã duyệt", 16));
//        return "redirect:/auction";
//    }
//    @RequestMapping("/auction-detail/{id}")
//    public String producDetail(@PathVariable int id, Model model, @SessionAttribute("carts") HashMap<Integer, Cart> cartMap) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Product product = productService.findById(id);
//        List<AuctionUser> detailList = auctionUserService.findByProduct(id);
//        double giaCaoNhat = 0;
//        if (!detailList.isEmpty()) {
//            giaCaoNhat = detailList.get(0).getStartingPrice();
//            model.addAttribute("nguoiCaoNhat", detailList.get(0));
//        }
//        //kiem tra xem neu chua dang nhap thi doi thanh button dang nhap
//        if (auth.getName().equals("anonymousUser")) {
//            model.addAttribute("userName", auth.getName());
//        } else {
//            for (AuctionUser auctionUser : detailList) {
//                if (auctionUser.getUser().getAccount().getUserName().equals(auth.getName())) {
//                    if (auctionUser.getStartingPrice() == giaCaoNhat) {
//                        model.addAttribute("winner", userService.findByUser_User(auth.getName()));
//                    }
//                }
//            }
//        }
//        double giaBinhThuong = product.getPrice();
//        //gia dau cao nhat cong voi gia khoi diem
//        double giaDau = giaCaoNhat + product.getPrice();
//        model.addAttribute("cartMap", cartMap);
//        model.addAttribute("sanPham", productService.findById(id));
//        model.addAttribute("giaCaoNhat", giaCaoNhat);
//        model.addAttribute("giaDau", giaDau);
//        model.addAttribute("dauGia", detailList);
//        model.addAttribute("giaBinhThuong",giaBinhThuong);
//        model.addAttribute("producTimeEnd",product.getDateEndAuction());
//        return "nha/auction/ProductDetail";
//    }
//    @RequestMapping("/afterLogin/auction-detail/{id}")
//    public String afterLoginproducDetail(@PathVariable int id, Model model, @SessionAttribute("carts") HashMap<Integer, Cart> cartMap) {
//        List<AuctionUser> detailList = auctionUserService.findByProduct(id);
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Product product = productService.findById(id);
//        double giaCaoNhat = 0;
//        if (!detailList.isEmpty()) {
//            giaCaoNhat = detailList.get(0).getStartingPrice();
//            model.addAttribute("nguoiCaoNhat", detailList.get(0));
//        }
//        //kiem tra xem neu chua dang nhap thi doi thanh button dang nhap
//        if (auth.getName().equals("anonymousUser")) {
//            model.addAttribute("userName", auth.getName());
//        } else {
//            for (AuctionUser auctionUser : detailList) {
//                if (auctionUser.getUser().getAccount().getUserName().equals(auth.getName())) {
//                    if (auctionUser.getStartingPrice() == giaCaoNhat) {
//                        model.addAttribute("winner", userService.findByUser_User(auth.getName()));
//                    }
//                }
//            }
//        }
//        double giaBinhThuong = product.getPrice();
//        //gia dau cao nhat cong voi gia khoi diem
//        double giaDau = giaCaoNhat + 1000;
//        model.addAttribute("cartMap", cartMap);
//        model.addAttribute("sanPham", productService.findById(id));
//        model.addAttribute("giaCaoNhat", giaCaoNhat);
//        model.addAttribute("giaDau", giaDau);
//        model.addAttribute("dauGia", detailList);
//        model.addAttribute("giaBinhThuong",giaBinhThuong);
//        return "redirect:/auction-detail/"+id;
//    }
//    @GetMapping("/dauGia")
//    public String dauGia(@RequestParam int idSP, double money, Principal principal) {
//        User user = userRepo.findByAccount_UserName(principal.getName());
//        Auction auction = auctionRepo.findByProduct_IdProduct(idSP);
//        if (auction == null) {
//            auction = new Auction();
//            auction.setProduct(productService.findById(idSP));
//            auctionRepo.save(auction);
//        }
//        int maDauGia = auction.getIdAuction();
//        //lay thoi gian hien tai
//        Time time = new Time(System.currentTimeMillis());
//        //them 2 thuoc tinh khoa
//        AuctionUserKey auctionUserKey = new AuctionUserKey(maDauGia, user.getIdUser());
//        AuctionUser auctionUser = new AuctionUser();
//        auctionUser.setId(auctionUserKey);
//        auctionUser.setAuction(auction);
//        auctionUser.setUser(user);
//        auctionUser.setAuctionEndTime(time);
//        auctionUser.setStartingPrice(money);
//        auctionUserService.create(auctionUser);
//        return "redirect:/auction-detail/" + idSP;
//    }
//    @GetMapping("/timKiem")
//    public String search(@RequestParam("maDanhMuc") Integer maDanhMuc,
//                         @RequestParam("tenSp") String tenSp, Model model) {
//        List<Category> categories = categoryService.findAll();
//        List<Product> products;
//        if (maDanhMuc != 0) {
//            if (!tenSp.equals("")) {
//                products = productService.findByDanhMucTenSanPham("Đã duyệt", maDanhMuc, tenSp);
//            } else {
//                products = productService.findByDanhMuc("Đã duyệt", maDanhMuc);
//            }
//        } else {
//            if (!tenSp.equals("")) {
//                products = productService.findByNameDaDuyet("Đã duyệt", tenSp);
//            } else {
//                products = productService.findByDaDuyet("Đã duyệt");
//            }
//        }
//        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
//                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
//            model.addAttribute("admin", "là admin");
//        }
//        if (categories.size() == 0 || products.size() == 0) {
//            model.addAttribute("danhmucs", categories);
//            model.addAttribute("listSP", products);
//            model.addAttribute("mgskt", "ko tìm thay");
//            return "/nha/auction/Home";
//        } else {
//            model.addAttribute("danhmucs", categories);
//            model.addAttribute("listSP", products);
//            model.addAttribute("mgs", "Danh sách sp tìm thấy");
//            return "/nha/auction/Home";
//        }
//    }

}
