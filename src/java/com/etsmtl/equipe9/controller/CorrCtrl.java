package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.CorrDAO;
import com.etsmtl.equipe9.dao.LocationDAO;
import com.etsmtl.equipe9.service.DAOFactory;
import java.util.ArrayList;
import java.util.List;

public class CorrCtrl {
    
    CorrDAO daoC = DAOFactory.getInstance().getCorrDAO();
    LocationDAO daoL = DAOFactory.getInstance().getLocationDAO();
    
    public List<Long> getCorr(Long idFilm, String courriel){
        
        List<Long> listeCorrTrier = daoC.findByIdAll(idFilm);
        
        List<Long>listeFilmDejaLoue = daoL.retournerFilmDejaLoue(courriel);
        
        listeCorrTrier.removeAll(listeFilmDejaLoue);
        
        
        int i = 0;
        List<Long> listeRecommandation = new ArrayList<>();
        
        for (Long long1 : listeCorrTrier) {
            
            if (i == 3) {
                break;
            }
            listeRecommandation.add(long1);
            i++;
        }
        
        
        return listeRecommandation;
    }
}
