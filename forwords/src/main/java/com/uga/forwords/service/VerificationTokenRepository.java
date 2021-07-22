package com.uga.forwords.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uga.forwords.model.VerificationToken;

@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {

}
