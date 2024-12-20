package com.ism.services;

import com.ism.entity.Client;
import com.ism.entity.Dette;

import java.util.List;
import java.util.Optional;

public class DetteServiceImpl implements DetteServiceImplInter {
    private  List<Dette> dettes;

    public DetteServiceImpl(List<Dette> dettes) {
        this.dettes = dettes;
    }

    public DetteServiceImpl() {
        //TODO Auto-generated constructor stub
    }

    @Override
    public void create(Dette dette) {
        dettes.add(dette);
    }

    @Override
    public Optional<Dette> findById(String id) {
        return dettes.stream()
                     .filter(d -> d.getId().equals(id))
                     .findFirst();
    }

    @Override
    public List<Dette> findAll() {
        return dettes;
    }

    @Override
    public void update(Dette dette) {
        // Implémentation de la mise à jour
    }

    @Override
    public void delete(Dette dette) {
        dettes.remove(dette);
    }

    @Override
    public List<Dette> listUnpaidDebts(Client client) {
        return dettes.stream()
                     .filter(dette -> dette.getClient().equals(client) && dette.getAmountRemaining() > 0)
                     .toList();
    }
}


