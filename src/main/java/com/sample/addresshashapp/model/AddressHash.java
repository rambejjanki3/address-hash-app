package com.sample.addresshashapp.model;

/*
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AddressHash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addressHash;

    public AddressHash() {}

    public AddressHash(String addressHash) {
        this.addressHash = addressHash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressHash() {
        return addressHash;
    }

    public void setAddressHash(String addressHash) {
        this.addressHash = addressHash;
    }
}
