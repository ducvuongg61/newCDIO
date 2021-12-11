package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.ConstraintViolation;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;


public class AccountUser implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Tên đăng nhập không được để trống")
    private String name1;
    private String sex1;
    @NotEmpty(message = "Vui lòng nhập ngày sinh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String DateTime1;
    @NotEmpty(message = "Vui lòng nhập email")
    @Email(message = "Sai định dạng Email")
    private String gmail1;
    private int numberCard1;
    @NotEmpty(message = "Vui lòng nhập địa chỉ")
    private String address1;
    @NotEmpty(message = "vui lòng nhập số đt")
    @Size(min = 9, max = 13, message = "Số điện thoai phải có độ dài 10-12 số")
    private String phoneUser1;
    @NotEmpty(message = "vui lòng nhập tên đăng nhập")
    private String userName1;

    @NotEmpty(message = "vui lòng nhập mật khẩu")
    @Size(min = 5, message = "vui lòng nhập mật khẩu lớn hơn 4 ký tự")
    private String passWord1;

    public AccountUser() {
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getSex1() {
        return sex1;
    }

    public void setSex1(String sex1) {
        this.sex1 = sex1;
    }

    public String getDateTime1() {
        return DateTime1;
    }

    public void setDateTime1(String dateTime1) {
        DateTime1 = dateTime1;
    }

    public String getGmail1() {
        return gmail1;
    }

    public void setGmail1(String gmail1) {
        this.gmail1 = gmail1;
    }

    public int getNumberCard1() {
        return numberCard1;
    }

    public void setNumberCard1(int numberCard1) {
        this.numberCard1 = numberCard1;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getPhoneUser1() {
        return phoneUser1;
    }

    public void setPhoneUser1(String phoneUser1) {
        this.phoneUser1 = phoneUser1;
    }

    public String getUserName1() {
        return userName1;
    }

    public void setUserName1(String userName1) {
        this.userName1 = userName1;
    }

    public String getPassWord1() {
        return passWord1;
    }

    public void setPassWord1(String passWord1) {
        this.passWord1 = passWord1;
    }

    public AccountUser(Integer id,@NotEmpty(message = "Tên đăng nhập không được để trống") String name1, String sex1, @NotEmpty(message = "Vui lòng nhập ngày sinh") @DateTimeFormat(pattern = "yyyy-MM-dd") String dateTime1, @NotEmpty(message = "Vui lòng nhập email") @Email(message = "Sai định dạng Email") String gmail1, int numberCard1, String address1,@NotEmpty(message = "vui lòng nhập số đt") @Size(min = 9, max = 13, message = "Số điện thoai phải có độ dài 10-12 số") String phoneUser1,    @NotEmpty(message = "vui lòng nhập tên đăng nhập") String userName1,@NotEmpty(message = "vui lòng nhập mật khẩu") @Size(min = 5, message = "vui lòng nhập mật khẩu lớn hơn 4 ký tự") String passWord1) {
        this.id = id;
        this.name1 = name1;
        this.sex1 = sex1;
        DateTime1 = dateTime1;
        this.gmail1 = gmail1;
        this.numberCard1 = numberCard1;
        this.address1 = address1;
        this.phoneUser1 = phoneUser1;
        this.userName1 = userName1;
        this.passWord1 = passWord1;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountUser.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object accountUsers, Errors errors) {
        AccountUser accountUser = (AccountUser) accountUsers;
        String DateTime1 = accountUser.getDateTime1();
        String email1 = accountUser.getGmail1();
//        String soDienThoai1 = ((NguoiDungTaiKhoan) nguoiDungTaiKhoan).getSoDienThoai1();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        Date ngaysinh1 = new Date();


        System.out.println("ngày hiện tại  ====" + date);
        try {
            ngaysinh1 = formatter.parse(DateTime1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (ngaysinh1.after(date)) {
            errors.rejectValue("ngaySinh1", "ngaySinh1.date");
        }

        if (!accountUser.phoneUser1.startsWith("0")) {
            errors.rejectValue("soDienThoai1", "number.startsWith");
        }
    }
}
