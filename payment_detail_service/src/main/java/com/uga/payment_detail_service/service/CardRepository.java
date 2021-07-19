package com.uga.payment_detail_service.service;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.uga.payment_detail_service.model.PaymentCard;

@Repository
public interface CardRepository extends CrudRepository<PaymentCard, String>{
	
	public PaymentCard findByCardNumber(String cardNumber);
	
	public List<PaymentCard> findByAccountId(String account_id);
	
	public PaymentCard findByCardNumberAndNameAndCardExpiryAndAccountId(String cardnumber, String name, Timestamp expiry, String account_id);
	
	public PaymentCard findByCardNumberAndAccountId(String cardnumber, String account_id);
}
