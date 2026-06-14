package com.DDD.AT.repositories;
import com.DDD.AT.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findById(Long id);
    boolean existsByEmail(String email);
}
