package com.etsmtl.equipe9.ws;

import com.etsmtl.equipe9.controller.ClientCtrl;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("client")
public class ClientWS {
    private ClientCtrl ctrl = new ClientCtrl();
	
	@Context
    private UriInfo context;
}
