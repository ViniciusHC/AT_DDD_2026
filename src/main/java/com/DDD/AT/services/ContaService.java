package com.DDD.AT.services;

import com.DDD.AT.entities.Conta;
import com.DDD.AT.repositories.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public List<Conta> listarTodas() {
        return contaRepository.findAll();
    }

    public Conta buscarPorId(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    public Conta criar(Conta conta) {
        if (contaRepository.existsByEmail(conta.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        return contaRepository.save(conta);
    }

    public Conta atualizar(Long id, Conta contaAtualizada) {
        Conta conta = buscarPorId(id);
        conta.setNome(contaAtualizada.getNome());
        conta.setEmail(contaAtualizada.getEmail());
        conta.setNumeroCatao(contaAtualizada.getNumeroCatao());
        conta.setCartaoAtivo(contaAtualizada.getCartaoAtivo());
        return contaRepository.save(conta);
    }

    public void deletar(Long id) {
        Conta conta = buscarPorId(id);
        contaRepository.delete(conta);
    }
}
