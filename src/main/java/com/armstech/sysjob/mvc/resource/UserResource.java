package com.armstech.sysjob.mvc.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.armstech.sysjob.api.event.ResourceCreateEvent;
import com.armstech.sysjob.mvc.model.User;
import com.armstech.sysjob.mvc.model.filter.UserFilter;
import com.armstech.sysjob.mvc.service.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		List<User> listUser = userService.getUsers();
		if (!listUser.isEmpty()) {
			return ResponseEntity.ok(listUser);
		}
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<List<User>> getUserByFilter(UserFilter userFilter) {
		List<User> listUser = userService.getCompanysByFilter(userFilter);
		if (!listUser.isEmpty()) {
			return ResponseEntity.ok(listUser);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		User user = userService.getUserById(id);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<User> save(@Valid @RequestBody User user, HttpServletResponse response) {
		user = userService.save(user);
		publisher.publishEvent(new ResourceCreateEvent(this, response, user.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User user) {
		User userUpdated = userService.update(id, user);
		return ResponseEntity.ok(userUpdated);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}

	@PutMapping("/{id}/status")
	@ResponseStatus
	public void changeStatus(@PathVariable Long id, @RequestBody Boolean enabled) {
		userService.changeStatus(id, enabled);
	}

}
