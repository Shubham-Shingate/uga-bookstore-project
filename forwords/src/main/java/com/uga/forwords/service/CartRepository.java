package com.uga.forwords.service;

import org.springframework.data.repository.CrudRepository;

import com.uga.forwords.model.Cart;

public interface CartRepository extends CrudRepository<Cart, Long> {

}
