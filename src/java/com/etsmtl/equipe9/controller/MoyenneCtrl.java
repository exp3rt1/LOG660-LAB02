package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.MoyenneDAO;
import com.etsmtl.equipe9.model.MaVueMoyenne;
import com.etsmtl.equipe9.service.DAOFactory;
import java.math.BigDecimal;

public class MoyenneCtrl {
    
    MoyenneDAO dao = DAOFactory.getInstance().getMoyenneDAO();
    
    public BigDecimal getMoyenne (Long idFilm){
        
        
        MaVueMoyenne moyenne = dao.findById(idFilm);
        
        if (moyenne == null) {
            
            return null;
        }
        
        return moyenne.getCote();
   }

}
