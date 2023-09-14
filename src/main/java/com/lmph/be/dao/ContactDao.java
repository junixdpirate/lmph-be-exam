package com.lmph.be.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmph.be.entity.Contact;

public interface ContactDao extends JpaRepository<Contact, Long>{

}
