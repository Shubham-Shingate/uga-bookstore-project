package com.uga.forwords.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.uga.forwords.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
	
//	@Transactional
//	@Procedure("SP_CREATE_USER_ACCOUNT")
//	public String saveUser(String in_FULL_NAME, String in_PHONE_NO, String in_EMAIL_ID, String in_PASSWORD, String in_ACCOUNT_STATUS, String in_ROLE);
	 
	
	@Query(value = "CALL SP_CREATE_USER_ACCOUNT(:in_FULL_NAME, :in_PHONE_NO, :in_EMAIL_ID, :in_PASSWORD, :in_ACCOUNT_STATUS, :in_ROLE);", nativeQuery = true)
	public String saveUser(
			@Param("in_FULL_NAME") String in_FULL_NAME,
			@Param("in_PHONE_NO") String in_PHONE_NO,
			@Param("in_EMAIL_ID") String in_EMAIL_ID,
			@Param("in_PASSWORD") String in_PASSWORD,
			@Param("in_ACCOUNT_STATUS") String in_ACCOUNT_STATUS,
			@Param("in_ROLE") String in_ROLE);
	
	
}
