package com.armstech.sysjob.mvc.repository;

import org.springframework.data.repository.CrudRepository;

import com.armstech.sysjob.mvc.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByMail(String mail);

	User findByCpf(String cpf);

}
