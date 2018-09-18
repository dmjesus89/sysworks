package com.armstech.sysjob.mvc.repository.query;

import java.util.List;

import com.armstech.sysjob.mvc.model.User;
import com.armstech.sysjob.mvc.model.filter.UserFilter;

public interface UserRepositoryQuery {

	public List<User> getByFilter(UserFilter userFilter);

}
