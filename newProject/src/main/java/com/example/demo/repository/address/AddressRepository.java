package com.example.demo.repository.address;

import com.example.demo.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address , Integer> {
    Page<Address> findAddressByAccUser_Name(String nameUser, Pageable pageable);

    Page<Address> findByNameAddressAndAccUser_Name(String nameAddress , String nameUser, Pageable pageable);

    Page<Address> findByNameAddress(String nameAddress , Pageable pageable);


}
