package com.etsmtl.equipe9.ws;

import com.etsmtl.equipe9.controller.LoginCtrl;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("login")
public class LoginWS {
    private LoginCtrl ctrl = new LoginCtrl();
	
	@Context
    private UriInfo context;
	
}
