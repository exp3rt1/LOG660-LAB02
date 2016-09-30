package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.Repository;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class LoginCtrl {
	@EJB
	private Repository db;
}
