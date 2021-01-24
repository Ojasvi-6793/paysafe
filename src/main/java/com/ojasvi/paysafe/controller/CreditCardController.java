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
import com.ojasvi.paysafe.model.CreditCard;
import com.ojasvi.paysafe.repository.CreditCardRepository;

/**
 * The type CreditCard controller.
 *
 * @author Ojasvi Agrawal
 */
@RestController
@RequestMapping("/api/v1")
public class CreditCardController {

	@Autowired
	private CreditCardRepository creditCardRepository;

	/**
	 * Get all creditCards list.
	 *
	 * @return the list
	 */
	@GetMapping("/creditCards")
	public List<CreditCard> getAllCreditCards() {
		return creditCardRepository.findAll();
	}

	/**
	 * Gets creditCards by id.
	 *
	 * @param creditCardId the creditCard id
	 * @return the creditCards by id
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping("/creditCards/{id}")
	public ResponseEntity<CreditCard> getCreditCardsById(@PathVariable(value = "id") Long creditCardId)
			throws ResourceNotFoundException {
		CreditCard creditCard = creditCardRepository.findById(creditCardId)
				.orElseThrow(() -> new ResourceNotFoundException("CreditCard not found on :: " + creditCardId));
		return ResponseEntity.ok().body(creditCard);
	}

	/**
	 * Create creditCard creditCard.
	 *
	 * @param creditCard the creditCard
	 * @return the creditCard
	 */
	@PostMapping("/creditCards")
	public CreditCard createCreditCard(@Valid @RequestBody CreditCard creditCard) {
		return creditCardRepository.save(creditCard);
	}

	/**
	 * Update creditCard response entity.
	 *
	 * @param creditCardId      the creditCard id
	 * @param creditCardDetails the creditCard details
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@PutMapping("/creditCards/{id}")
	public ResponseEntity<CreditCard> updateCreditCard(@PathVariable(value = "id") Long creditCardId,
			@Valid @RequestBody CreditCard creditCardDetails) throws ResourceNotFoundException {

		CreditCard creditCard = creditCardRepository.findById(creditCardId)
				.orElseThrow(() -> new ResourceNotFoundException("CreditCard not found on :: " + creditCardId));

		creditCard.setCardHolderName(creditCardDetails.getCardHolderName());
		creditCard.setCardNumber(creditCardDetails.getCardNumber());
		creditCard.setDate(creditCardDetails.getDate());
		creditCard.setExpiryDate(creditCardDetails.getExpiryDate());
		creditCard.setUserID(creditCardDetails.getUserID());

		// to do _ fill other methods of creditCards
		final CreditCard updatedCreditCard = creditCardRepository.save(creditCard);
		return ResponseEntity.ok(updatedCreditCard);
	}

	/**
	 * Delete creditCard map.
	 *
	 * @param creditCardId the creditCard id
	 * @return the map
	 * @throws Exception the exception
	 */
	@DeleteMapping("/creditCard/{id}")
	public Map<String, Boolean> deleteCreditCard(@PathVariable(value = "id") Long creditCardId) throws Exception {
		CreditCard creditCard = creditCardRepository.findById(creditCardId)
				.orElseThrow(() -> new ResourceNotFoundException("CreditCard not found on :: " + creditCardId));

		creditCardRepository.delete(creditCard);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
