package com.phani.micorservices.restfulwebservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.phani.micorservices.restfulwebservices.exceptions.UserNotFoundException;
import com.phani.micorservices.restfulwebservices.model.User;
import com.phani.micorservices.restfulwebservices.service.UserDaoService;

@RestController
public class UserController {

	@Autowired
	private UserDaoService userDaoService;

	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers() {
		return userDaoService.findAll();
	}

	@GetMapping(path = "/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = userDaoService.findOne(id);

		if (user == null)
			throw new UserNotFoundException("id - " + id);

		EntityModel<User> entityModel = EntityModel.of(user);
		
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		entityModel.add(linkToUsers.withRel("all-users"));
		
		return entityModel;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
