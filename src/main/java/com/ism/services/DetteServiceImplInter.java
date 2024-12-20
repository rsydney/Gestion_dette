package com.ism.services;

import com.ism.entity.Client;
import com.ism.entity.Dette;

import java.util.List;
import java.util.Optional;

public interface DetteServiceImplInter {
    void create(Dette dette);
    Optional<Dette> findById(String id);
    List<Dette> findAll();
    void update(Dette dette);
    void delete(Dette dette);
    List<Dette> listUnpaidDebts(Client client);
}
