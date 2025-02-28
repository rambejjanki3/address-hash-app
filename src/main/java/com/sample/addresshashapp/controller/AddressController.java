package com.sample.addresshashapp.controller;

import com.sample.addresshashapp.model.Address;
import com.sample.addresshashapp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/hash")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAddressHash(@RequestBody List<Address> addresses) {
        addressService.processAndSaveAddressHashes(addresses);
    }
}

