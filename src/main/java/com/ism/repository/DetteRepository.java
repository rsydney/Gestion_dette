package com.ism.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ism.entity.Dette;

public class DetteRepository implements DetteRepositoryInter {
    private List<Dette> dettes;

    public DetteRepository(List<Dette> dettes) {
        this.dettes = dettes;
    }

    public DetteRepository() {
        //TODO Auto-generated constructor stub
    }

    @Override
    public void add(Dette dette) {
        dettes.add(dette);
    }

    @Override
    public List<Dette> getAll() {
        return dettes;
    }

    @Override
    public Optional<Dette> findById(int id) {
        return dettes.stream()
                .filter(dette -> dette.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Dette> getSettledDebts() {
        return dettes.stream()
                .filter(dette -> dette.getAmountRemaining() == 0)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Dette> findBy(String data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBy'");
    }
}
