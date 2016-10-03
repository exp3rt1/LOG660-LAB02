package com.etsmtl.equipe9.dao;

import java.sql.Connection;
import com.etsmtl.equipe9.service.IDAO;

public abstract class DAO implements IDAO {
    protected Connection conn = DAOFactory.getInstance().getConn();
}
