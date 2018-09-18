package com.armstech.sysjob.mvc.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.armstech.sysjob.api.exception.UserAlreadyExistsException;
import com.armstech.sysjob.mvc.model.User;
import com.armstech.sysjob.mvc.model.filter.UserFilter;
import com.armstech.sysjob.mvc.model.security.UserRole;
import com.armstech.sysjob.mvc.repository.RoleRepository;
import com.armstech.sysjob.mvc.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public List<User> getUsers() {
		List<User> listUser = (List<User>) userRepository.findAll();
		return listUser;
	}

	public List<User> getByFilter(UserFilter userFilter) {
		//return this.userRepository.getByFilter(userFilter);
		return null;
	}

	public User getUserById(Long id) throws NoSuchElementException {
		Optional<User> option = userRepository.findById(id);
		if (null != option && option.get() != null) {
			return option.get();
		}
		throw new EmptyResultDataAccessException(1);
	}

	public User save(final User user) {
		if (validateMailExist(user.getMail())) {
			throw new UserAlreadyExistsException();
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setDtCadastro(LocalDate.now());
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, roleRepository.findByName("USER_BASIC")));
		return saveUser(user);
	}
	
	public User update(Long id, User user) {
		User userSaved = this.getUserById(id);
		System.out.println(user.getAddress().getId());
		System.out.println(userSaved.getAddress().getId());
		BeanUtils.copyProperties(user, userSaved, "id");
		userSaved.setPassword(passwordEncoder.encode(userSaved.getPassword()));
		userRepository.save(userSaved);
		return userSaved;

	}
	
	public void delete(Long id) {
		User userSaved = this.getUserById(id);
		userRepository.delete(userSaved);
	}

	public void changeStatus(Long id, Boolean enabled) {
		User userSaved = this.getUserById(id);
		userSaved.setEnabled(enabled);
		userRepository.save(userSaved);
	}
	
	private User saveUser(final User user) {
		userRepository.save(user);
		return user;
	}

	private boolean checkMailExist(final String mail) {
		if (userRepository.findByMail(mail) == null) {
			return true;
		}
		return false;
	}
	private boolean validateCpfExist(final String cpf) {
		if (userRepository.findByCpf(cpf) == null) {
			return false;
		}
		return true;
	}

	private boolean validateMailExist(final String mail) {
		if (userRepository.findByMail(mail) == null) {
			return false;
		}
		return true;
	}

	



}
