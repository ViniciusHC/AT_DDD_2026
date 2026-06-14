package com.DDD.AT.repositories;
import com.DDD.AT.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    Optional<Transacao> findById(long id);
}
