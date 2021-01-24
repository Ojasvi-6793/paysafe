/**
 * 
 */
package com.ojasvi.paysafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ojasvi.paysafe.model.Transaction;

/**
 * @author Ojasvi Agrawal
 *
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

