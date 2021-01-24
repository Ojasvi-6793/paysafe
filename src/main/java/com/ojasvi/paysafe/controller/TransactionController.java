/**
 * 
 */
package com.ojasvi.paysafe.controller;

/**
 * @author Ojasvi Agrawal
 *
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojasvi.paysafe.exception.ResourceNotFoundException;
import com.ojasvi.paysafe.model.Transaction;
import com.ojasvi.paysafe.repository.TransactionRepository;

/**
 * The type Transaction controller.
 *
 * @author Ojasvi Agrawal
 */
@RestController
@RequestMapping("/api/v1")
public class TransactionController {

	@Autowired
	private TransactionRepository transactionRepository;

	/**
	 * Get all transactions list.
	 *
	 * @return the list
	 */
	@GetMapping("/transactions")
	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}

	/**
	 * Gets transactions by id.
	 *
	 * @param transactionId the transaction id
	 * @return the transactions by id
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping("/transactions/{id}")
	public ResponseEntity<Transaction> getTransactionsById(@PathVariable(value = "id") Long transactionId)
			throws ResourceNotFoundException {
		Transaction transaction = transactionRepository.findById(transactionId)
				.orElseThrow(() -> new ResourceNotFoundException("Transaction not found on :: " + transactionId));
		return ResponseEntity.ok().body(transaction);
	}

	/**
	 * Create transaction transaction.
	 *
	 * @param transaction the transaction
	 * @return the transaction
	 */
	@PostMapping("/transactions")
	public Transaction createTransaction(@Valid @RequestBody Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	/**
	 * Update transaction response entity.
	 *
	 * @param transactionId      the transaction id
	 * @param transactionDetails the transaction details
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@PutMapping("/transactions/{id}")
	public ResponseEntity<Transaction> updateTransaction(@PathVariable(value = "id") Long transactionId,
			@Valid @RequestBody Transaction transactionDetails) throws ResourceNotFoundException {

		Transaction transaction = transactionRepository.findById(transactionId)
				.orElseThrow(() -> new ResourceNotFoundException("Transaction not found on :: " + transactionId));

		transaction.setUserID(transactionDetails.getUserID());
		transaction.setDate(transactionDetails.getDate());
		transaction.setStatus(transactionDetails.getStatus());
		transaction.setAmount(transactionDetails.getAmount());

		// to do _ fill other methods of transactions
		final Transaction updatedTransaction = transactionRepository.save(transaction);
		return ResponseEntity.ok(updatedTransaction);
	}

	/**
	 * Delete transaction map.
	 *
	 * @param transactionId the transaction id
	 * @return the map
	 * @throws Exception the exception
	 */
	@DeleteMapping("/transaction/{id}")
	public Map<String, Boolean> deleteTransaction(@PathVariable(value = "id") Long transactionId) throws Exception {
		Transaction transaction = transactionRepository.findById(transactionId)
				.orElseThrow(() -> new ResourceNotFoundException("Transaction not found on :: " + transactionId));

		transactionRepository.delete(transaction);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}

