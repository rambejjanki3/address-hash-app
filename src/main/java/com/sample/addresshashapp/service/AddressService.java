package com.sample.addresshashapp.service;

import com.sample.addresshashapp.model.Address;
import com.sample.addresshashapp.model.AddressHash;
import com.sample.addresshashapp.repository.AddressHashRepository;
import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);

    @Autowired
    private AddressHashRepository addressHashRepository;

    // Process list of addresses, format them, generate hashes, and save them to DB
    public void processAndSaveAddressHashes(List<Address> addresses) {
        for (Address address : addresses) {
            // Convert address fields to a single string, separated by underscores
            String addressAsOneString = formatAddress(address);

            // Generate Murmur3 hash using Guava
            String hash = Hashing.murmur3_32().hashString(addressAsOneString, StandardCharsets.UTF_8).toString();

            // Save the address hash to the database
            AddressHash addressHash = new AddressHash(hash);
            AddressHash savedAddressHash = addressHashRepository.save(addressHash);

            // Log the ID and length of the generated hash
            logger.info("Saved AddressHash with ID: {} and Hash Length: {}", savedAddressHash.getId(), hash.length());
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

    // Fetch all address hashes from the database
    public List<AddressHash> getAllAddressHashes() {
        return addressHashRepository.findAll();
    }
}
