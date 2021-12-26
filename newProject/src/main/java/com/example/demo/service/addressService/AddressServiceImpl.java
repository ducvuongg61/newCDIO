package com.example.demo.service.addressService;

import com.example.demo.model.Address;
import com.example.demo.repository.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    AddressRepository addressRepository;

    @Override
    public Page<Address> findByNameAddress(String address, Pageable pageable) {
        return addressRepository.findByNameAddressContains(address , pageable);
    }

    @Override
    public Page<Address> findByNameAddressAndNameUser(String nameUser, String address, Pageable pageable) {
        return addressRepository.findByNameAddressAndAccUser_NameContains(nameUser , address , pageable);
    }

    @Override
    public Page<Address> findAll(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    @Override
    public Page<Address> findByNameUser(String nameUser, Pageable pageable) {
        return addressRepository.findAddressByAccUser_NameContains(nameUser , pageable);
    }

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }
}
