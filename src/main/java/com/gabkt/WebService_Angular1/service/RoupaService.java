package com.gabkt.WebService_Angular1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabkt.WebService_Angular1.model.Roupa;
import com.gabkt.WebService_Angular1.repository.RoupaDao;

@Service
public class RoupaService {
    @Autowired
    private RoupaDao roupaDao;

    public Roupa inserirRoupa(Roupa roupa) {
        return roupaDao.inserirRoupa(roupa);
    }

    public Roupa buscarRoupaPorId(int id) {
        return roupaDao.buscarRoupaPorId(id);
    }

    public Roupa atualizarRoupa(Roupa roupa) {
        return roupaDao.atualizarRoupa(roupa);
    }

    public Roupa excluirRoupa(int id) {
        return roupaDao.excluirRoupa(id);
    }

    public List<Roupa> listarTodasRoupas() {
        return roupaDao.listarTodasRoupas();
    }
}
