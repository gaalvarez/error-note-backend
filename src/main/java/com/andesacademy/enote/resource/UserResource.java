/**
 * 
 */
package com.andesacademy.enote.resource;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andesacademy.enote.exception.UserNotFoundException;
import com.andesacademy.enote.model.User;
import com.andesacademy.enote.repository.UserRepository;

/**
 * @author a2g
 *
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class UserResource {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/users")
	public ResponseEntity<Object> createStudent(@RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getIdUser()).toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/users/validate/{username}")
	public ResponseEntity<?> validateExist(@PathVariable String username) {
		final Map<String, Integer> response = new HashMap<>();
		final Integer count = userRepository.countByUsername(username);
		response.put("count", count);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public User retrieveStudent(@PathVariable Integer id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		return user.get();
	}

}
