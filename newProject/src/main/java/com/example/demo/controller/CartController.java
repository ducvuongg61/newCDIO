package com.example.demo.controller;

import com.example.demo.config.MyConstants;
import com.example.demo.model.*;

import com.example.demo.repository.UserRepository.UserRepository;
import com.example.demo.service.colorService.ColorService;
import com.example.demo.service.payPalService.PayPalPaymentIntent;
import com.example.demo.service.payPalService.PayPalPaymentMethod;
import com.example.demo.service.payPalService.PayPalService;
import com.example.demo.service.product.ProductService;

import com.example.demo.service.productBillService.BillService;
import com.example.demo.service.userService.UserService;
import com.example.demo.util.PayPalUtils;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {
    public static final String URL_PAYPAL_SUCCESS = "pay/success";
    public static final String URL_PAYPAL_CANCEL = "pay/cancel";

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PayPalService paypalService;

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserService userService;

    @Autowired
    BillService billService;

    @Autowired
    ProductService productService;

    @Autowired
    ColorService colorService;
//    @Autowired
//    AuctionUserService auctionUserService;

    public static int[] arrayQuantity = new int[10];
    public static int[] temp = new int[10];
    public static double totalMoney = 0;
    public static double sumTotalMoney = 0;
    public static int totalQuantity = 0;
    public static ProductBill orderDetail = new ProductBill();
    public static HashMap<Double, Product> productListBillTemp = new HashMap<>();

    @ModelAttribute("userNames")
    public AccUser getDauGia() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.findByAccount_IdAccount(auth.getName());
    }

    @GetMapping("showCart")
    public ModelAndView show(@SessionAttribute("carts") HashMap<Integer, Cart> cartMap) {
        ModelAndView modelAndView = new ModelAndView("Vinh/CartPage");
        modelAndView.addObject("carts", cartMap);
        modelAndView.addObject("cartSize", cartMap.size());
        return modelAndView;
    }

//    @GetMapping("/showCart/getData/{idProduct}&{idColor}")
    @GetMapping("/showCart/getData/{idProduct}")
    public String show(@PathVariable(value ="idProduct") int idProduct,
                        @RequestParam String quantity,
                       @RequestParam String money,
                       @RequestParam(value ="idColor") int idColor,
                       Model model, @SessionAttribute("carts") HashMap<Integer, Cart> cartMap) {
        totalMoney = Double.parseDouble(money);
        sumTotalMoney += totalMoney;
        totalQuantity = Integer.parseInt(quantity);
        extracted(idProduct, arrayQuantity, temp);
        System.out.println("totalMoney:" + totalMoney);
        System.out.println("totalMoney:" + totalQuantity);
        model.addAttribute("money", money);
        model.addAttribute("quantity", quantity);
        model.addAttribute("cart", new Cart());
        if (cartMap == null) {
            cartMap = new HashMap<>();
        }
        Product product = productService.findById(idProduct);
        Color color = colorService.findById(idColor);
        System.out.println("color"+color.getColor());
        if (color != null) {
            for (int i = 0; i < 10; i++) {
                if (arrayQuantity[i] == idProduct) {
                    color.setQuantity(color.getQuantity() - temp[i]);
                    if (color.getQuantity() < 1) {
                        color.setStatus("Out of product");
                    }
                }
            }
            colorService.save(color);
            if (cartMap.containsKey(idProduct)) {
                Cart item = cartMap.get(idProduct);
                item.setColor(color);
                item.setProduct(product);
                item.setQuantity(item.getQuantity() + totalQuantity);
                item.setMaxPrice(totalMoney);
                cartMap.put(idProduct, item);
            } else {
                Cart cart = new Cart();
                cart.setColor(color);
                cart.setProduct(product);
                cart.setQuantity(totalQuantity);
                cart.setMaxPrice(totalMoney);
                cartMap.put(idProduct, cart);
            }
        }
        model.addAttribute("carts", cartMap);
        model.addAttribute("cartSize", cartMap.size());
        return "redirect:/showCart";
    }

    private void extracted(@PathVariable int id, int[] arrayQuantity, int[] temp) {
        for (int i = 0; i < 10; i++) {
            if (arrayQuantity[i] == 0) {
                arrayQuantity[i] = id;
                temp[i] = totalQuantity;
                break;
            }
        }
    }

    @GetMapping("/deleteCart/{id}")
    public String deleteCart(@PathVariable int id, @SessionAttribute("carts") HashMap<Integer, Cart> cartMap, Model model) {
        cartMap.remove(id);
        extracted(id, arrayQuantity, temp);
        Color color = colorService.findById(id);
        if (color != null) {
            for (int i = 0; i < 10; i++) {
                if (arrayQuantity[i] == id) {
                    System.out.println("xoa " + temp[i]);
                    color.setQuantity(color.getQuantity() + temp[i]);
                    if (color.getQuantity() >= 1) {
                        color.setStatus("Đã duyệt");
                    }
                    break;
                }
            }
            colorService.save(color);
        }
        return "redirect:/showCart";
    }

    @GetMapping("/hoaDon/layDuLieu/{id}")
    public String getHoaDon(@PathVariable int id, Model model, @SessionAttribute("carts") HashMap<Integer, Cart> cartMap) {
        if (cartMap == null) {
            cartMap = new HashMap<>();
        }
        Color color = colorService.findById(id);
        if (color != null) {
            color.setQuantity(color.getQuantity() - totalQuantity);
            if (color.getQuantity() <= 0) {
                color.setStatus("Out of product"); // thay ham xoa cx dc
            }
            colorService.save(color);
            if (cartMap.containsKey(id)) {
                Cart item = cartMap.get(id);
                item.setColor(color);
                item.setQuantity(item.getQuantity() + totalQuantity);
                item.setMaxPrice(totalMoney);
                cartMap.put(id, item);
            } else {
                Cart cart = new Cart();
                cart.setColor(color);
                cart.setQuantity(totalQuantity);
                cart.setMaxPrice(totalMoney);
                cartMap.put(id, cart);
            }
        }
        model.addAttribute("bill", new Bill());
        model.addAttribute("carts", cartMap);
        return "Vinh/Pay";
    }

    @GetMapping("/bill/pay")
    public String thanhToan(@SessionAttribute("carts") HashMap<Integer, Cart> cartMap, @ModelAttribute Bill bill, Model model) {
        Double inputTotal = sumTotalMoney;
        List<String> nameProduct = new ArrayList<>();
        for (Map.Entry<Integer, Cart> entry : cartMap.entrySet()) {
            Cart value = entry.getValue();
            nameProduct.add(value.getProduct().getProductName());
        }
        HashMap<Double, Product> productListBill = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AccUser user = userService.findByAccount(auth.getName());
        LocalDate currentDate = LocalDate.now();
        bill.setCurrent(String.valueOf(currentDate));
        bill.setStatus("Đang giao");
        bill.setUser(user);
        bill.setTotalCost(totalMoney);
        billService.save(bill);
        ProductBill productBill = new ProductBill();
        for (Map.Entry<Integer, Cart> entry : cartMap.entrySet()) {
            Cart value = entry.getValue();
            productListBill.put(value.getMaxPrice(), value.getProduct());
            productBill.setBill(bill);
            productBill.setProduct(value.getProduct());
            billService.saveDetail(productBill);
        }
        model.addAttribute("inputTotal",inputTotal);
        model.addAttribute("carts",cartMap);
        model.addAttribute("orderDetail", orderDetail);
        model.addAttribute("productListBillTemp", productListBillTemp);
        model.addAttribute("orderDetail", productBill);
        model.addAttribute("productListBillTemp", productListBill);
        return "Vinh/ReceiptPage";
    }
//
//
//    @GetMapping("/payPal")
//    public String index() {
//        return "paypal/index";
//    }
//
    @GetMapping("/pay")
    public String pay(HttpServletRequest request, @SessionAttribute("carts") HashMap<Integer, Cart> cartMap,
                      @ModelAttribute Bill bill, Model model) {
        HashMap<Double, Product> productListBill = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AccUser user = userService.findByAccount(auth.getName());
        LocalDate currentDate = LocalDate.now();
        bill.setCurrent(String.valueOf(currentDate));
        bill.setStatus("Đang giao");
        bill.setUser(user);
        bill.setTotalCost(totalMoney);
        billService.save(bill);
        ProductBill productBill = new ProductBill();
        for (Map.Entry<Integer, Cart> entry : cartMap.entrySet()) {
            Cart value = entry.getValue();
            productListBill.put(value.getMaxPrice(), value.getProduct());
            productBill.setBill(bill);
            productBill.setProduct(value.getProduct());
            billService.saveDetail(productBill);
        }
        orderDetail = productBill;
        productListBillTemp = productListBill;
        String cancelUrl = PayPalUtils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
        String successUrl = PayPalUtils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
        try {
            Payment payment = paypalService.createPayment(
                    sumTotalMoney,
                    "USD",
                    PayPalPaymentMethod.paypal,
                    PayPalPaymentIntent.sale,
                    "payment description",
                    cancelUrl,
                    successUrl);
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return "redirect:" + links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping(URL_PAYPAL_CANCEL)
    public String cancelPay() {
        return "paypal/cancel";
    }

    @GetMapping(URL_PAYPAL_SUCCESS)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String
            payerId, Model model, @SessionAttribute("carts") HashMap<Integer, Cart> cartMap, Principal principal) {
        Double inputTotal = sumTotalMoney;
        List<String> tenSp = new ArrayList<>();
        AccUser user = userRepo.findByAccount_IdAccount(principal.getName());
        MyConstants.setFriendEmail(user.getGmail());
        for (Map.Entry<Integer, Cart> entry : cartMap.entrySet()) {
            Cart value = entry.getValue();
            tenSp.add(value.getProduct().getProductName());
        }

        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("luytlong122@gmail.com");
                message.setTo(MyConstants.FRIEND_EMAIL);
                message.setSubject("THÔNG BÁO ĐÃ THANH TOÁN HÓA ĐƠN!");
                message.setText("Mã hóa đơn: HD" + orderDetail.getBill().getIdBill() + "\n" +
                        "Danh sách sản phẩm: " + tenSp + "\n" +
                        "Ngày mua: " + orderDetail.getBill().getCurrent() + "\n" +
                        "Số tiền đã thanh toán: " + sumTotalMoney);
                this.emailSender.send(message);
                model.addAttribute("inputTotal", inputTotal);
                model.addAttribute("carts", cartMap);
                model.addAttribute("orderDetail", orderDetail);
                model.addAttribute("productListBillTemp", productListBillTemp);
                return "Vinh/ReceiptPage";
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        sumTotalMoney = 0;
        return "redirect:/payPal";
    }
}
