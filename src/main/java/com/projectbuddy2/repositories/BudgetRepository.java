package com.projectbuddy2.repositories;


import com.projectbuddy2.entities.Budget;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BudgetRepository extends CrudRepository<Budget, Long> {
    Optional<Budget> findById(Long id);
    Boolean existsByName(String name);
}
