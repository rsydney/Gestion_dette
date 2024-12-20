package com.ism.repository;

import java.util.List;

import com.ism.entity.Dette;
import com.ism.repository.core.Repository;

public interface DetteRepositoryInter extends Repository<Dette> {
    List<Dette> getSettledDebts();
}
