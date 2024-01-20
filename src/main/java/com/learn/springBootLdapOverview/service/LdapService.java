package com.learn.springBootLdapOverview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import com.learn.springBootLdapOverview.model.LdapUser;


@Service
public class LdapService {

	@Autowired
	private LdapTemplate ldapTemplate;
	private static final String BASE_DN="ou=users,ou=system";
	
	public void addUser(LdapUser ldapUser) {
		ldapTemplate.bind("uid="+ldapUser.getUsername()+","+BASE_DN, null, ldapUser.toAttributes());
		
	}
}

