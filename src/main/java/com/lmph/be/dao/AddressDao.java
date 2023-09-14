package com.lmph.be.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmph.be.entity.Address;

public interface AddressDao extends JpaRepository<Address, Long>{

}
