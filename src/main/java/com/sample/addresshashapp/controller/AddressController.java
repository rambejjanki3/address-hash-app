package com.sample.addresshashapp.controller;

import com.sample.addresshashapp.model.Address;
import com.sample.addresshashapp.model.AddressHash;
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

    // POST method to create address hashes
    @PostMapping("/hash")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAddressHash(@RequestBody List<Address> addresses) {
        addressService.processAndSaveAddressHashes(addresses);
    }

    // GET method to retrieve all address hashes from the database
    @GetMapping("/hashes")
    public List<AddressHash> getAllAddressHashes() {
        return addressService.getAllAddressHashes();
    }
}
