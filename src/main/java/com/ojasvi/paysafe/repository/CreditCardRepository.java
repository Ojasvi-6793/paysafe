/**
 * 
 */
package com.ojasvi.paysafe.repository;

/**
 * @author Ojasvi Agrawal
 *
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ojasvi.paysafe.model.CreditCard;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}

