package com.example.demo.repository.address;

import com.example.demo.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address , Integer> {
    Page<Address> findAddressByAccUser_NameContains(String nameUser, Pageable pageable);

    Page<Address> findByNameAddressAndAccUser_NameContains(String nameAddress , String nameUser, Pageable pageable);

    Page<Address> findByNameAddressContains(String nameAddress , Pageable pageable);


}
