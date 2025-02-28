package com.sample.addresshashapp.service;

import com.sample.addresshashapp.model.Address;
import com.sample.addresshashapp.model.AddressHash;
import com.sample.addresshashapp.repository.AddressHashRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressHashRepository addressHashRepository;

    // Process list of addresses, format them, generate hashes, and save them to DB
    public void processAndSaveAddressHashes(List<Address> addresses) {
        for (Address address : addresses) {
            // Convert address fields to a single string, separated by underscores
            String addressAsOneString = formatAddress(address);

            // Generate Murmur3 hash using Guava
            String hash = Hashing.murmur3_32().hashString(addressAsOneString, StandardCharsets.UTF_8).toString();

            // Create AddressHash entity and save it to the database
            AddressHash addressHash = new AddressHash(hash);
            addressHashRepository.save(addressHash);
        }
    }

    // Helper method to format address fields into one string
    private String formatAddress(Address address) {
        return String.join("_",
                address.getStreetLine1(),
                address.getStreetLine2().isEmpty() ? "NULL" : address.getStreetLine2(),
                address.getStreetLine3().isEmpty() ? "NULL" : address.getStreetLine3(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getZipcode());
    }
}
