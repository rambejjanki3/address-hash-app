package com.sample.addresshashapp.repository;

import com.sample.addresshashapp.model.AddressHash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressHashRepository extends JpaRepository<AddressHash, Long> {
}

