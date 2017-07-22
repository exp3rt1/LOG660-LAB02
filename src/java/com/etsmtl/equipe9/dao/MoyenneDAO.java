package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.model.MaVueMoyenne;
import java.util.List;

public class MoyenneDAO extends DAOAbstrait<MaVueMoyenne, Long>{

    @Override
    public List<MaVueMoyenne> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public MaVueMoyenne findById(Long id) {
        try {
            connect();
            return this.em.find(MaVueMoyenne.class, id);
        } catch (Exception e) {
            return null;
        } finally {
            disconnect();
        }
    }

    @Override
    public List<MaVueMoyenne> findById(List<Long> listeId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean insert(MaVueMoyenne obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean update(MaVueMoyenne obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(MaVueMoyenne obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

   

}
