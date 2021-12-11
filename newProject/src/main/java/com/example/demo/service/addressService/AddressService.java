package com.example.demo.service.addressService;

import com.example.demo.model.AccUser;
import com.example.demo.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService {
    Page<Address> findByNameAddress(String address, Pageable pageable);

    Page<Address> findByNameAddressAndNameUser(String nameUser, String address, Pageable pageable);

    Page<Address> findAll(Pageable pageable);

    Page<Address> findByNameUser(String nameUser , Pageable pageable);

    void save(Address address);

}
