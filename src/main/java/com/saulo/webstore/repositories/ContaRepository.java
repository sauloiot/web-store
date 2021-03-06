package com.saulo.webstore.repositories;

import com.saulo.webstore.models.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {
    @Transactional(readOnly = true)
    Conta findByEmail(String email);
}