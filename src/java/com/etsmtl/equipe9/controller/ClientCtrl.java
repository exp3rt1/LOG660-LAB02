package com.etsmtl.equipe9.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.etsmtl.equipe9.service.IDAO;

@Stateless
public class ClientCtrl {
    @EJB
    private IDAO db;
}
