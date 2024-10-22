package com.learn.springBootLdapOverview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learn.springBootLdapOverview.model.LdapUser;
import com.learn.springBootLdapOverview.service.LdapService;

// youtube playlist ldap: https://www.youtube.com/playlist?list=PLyzY2l387AlO0KMY2Ydkzyxj1NgwtvR64
//  udemy ldap course link : https://www.udemy.com/share/10c2jr3@gXg1kvyeuFFQapv4E2gw0HJSi9gzWhNBOhYpOZd6zC04bgBjd4p9a3r-yUv8fEzoEg==/
@RestController
public class LdapAuthenticationController {

	@Autowired
	private LdapService ldapService;

	@GetMapping("/")
	public String index() {
		return "Welcome to the home page!";
	}

	@GetMapping("/getUserDetails")
	public String getUserDetails(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		// access user details
		String userName = userDetails.getUsername();
		boolean accNonExpired = userDetails.isAccountNonExpired();
		return "UserDetails: " + userName + "\n Account Non Expired: " + accNonExpired;
	}

	@GetMapping("/getAllUsers")
	public List<LdapUser> getAllUsers() {
		return ldapService.getAllUsers();
	}

	@GetMapping("/getUserById/{uid}")
	public String getUserById(@PathVariable String uid) {

		return ldapService.getUserById(uid);
	}
	
	@GetMapping("/deleteUser/{uid}")
	public String deleteUser(@PathVariable String uid) {
		ldapService.deleteUser(uid);
		return "User Deleted";
	}
}
