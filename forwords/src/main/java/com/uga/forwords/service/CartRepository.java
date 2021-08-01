package com.uga.forwords.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.uga.forwords.model.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

}
