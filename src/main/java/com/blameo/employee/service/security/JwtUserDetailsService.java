package com.blameo.employee.service.security;

import java.util.ArrayList;
import java.util.Optional;

import com.blameo.employee.config.security.JwtCustomUserDetail;
import com.blameo.employee.entity.UserStatusEnum;
import com.blameo.employee.entity.model.User;
import com.blameo.employee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public JwtCustomUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> optUser = userRepository.findOneByUsername(username);
		if (!optUser.isPresent() || optUser.get().getStatus().equals(UserStatusEnum.DELETED)) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		User user = optUser.get();
		return new JwtCustomUserDetail(user.getUsername(), user.getPassword(), new ArrayList<>(), user.getId());
	}
}