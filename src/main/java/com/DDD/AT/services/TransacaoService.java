package com.DDD.AT.services;

import com.DDD.AT.entities.Conta;
import com.DDD.AT.entities.Transacao;
import com.DDD.AT.repositories.ContaRepository;
import com.DDD.AT.repositories.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final ContaRepository contaRepository;


    public TransacaoService(TransacaoRepository transacaoRepository, ContaRepository contaRepository) {
        this.transacaoRepository = transacaoRepository;
        this.contaRepository = contaRepository;
    }

    private LocalDateTime tempoLimite = null;
    private List<Transacao> transacoes = new ArrayList<>();
    private Integer transacoesIguais = 0;

    public List<Transacao> listarTodas() {
        return transacaoRepository.findAll();
    }

    public Transacao buscarPorId(Long id) {
        return transacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));
    }

    public Transacao criar(Transacao novaTransacao, Long idConta) {
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));


        LocalDateTime dataTransacao = LocalDateTime.now();

        if (!conta.getCartaoAtivo()) {
            throw new RuntimeException("Cartão inválido");
        }

        if (tempoLimite != null && dataTransacao.isBefore(tempoLimite)) {
            if (transacoes.size() >= 3) {
                throw new RuntimeException("Muitas transações requisitadas");
            }

            transacoes.forEach(transacao -> {
                if (novaTransacao.getValor().equals(transacao.getValor())
                        && novaTransacao.getComerciante().equals(transacao.getComerciante())) {
                    transacoesIguais ++;
                    if (transacoesIguais >= 2){
                        throw new RuntimeException("Transação duplicada");
                    }
                }
            });
            transacoes.add(novaTransacao);
        } else {
            transacoes = new ArrayList<>();
            transacoesIguais = 0;
            transacoes.add(novaTransacao);
            tempoLimite = LocalDateTime.now().plusMinutes(2);
        }

        novaTransacao.setConta(conta);
        novaTransacao.setAprovada(true);
        return transacaoRepository.save(novaTransacao);
    }

    public Transacao atualizar(Long id, Transacao transacaoAtualizada) {
        Transacao transacao = buscarPorId(id);
        transacao.setValor(transacaoAtualizada.getValor());
        transacao.setComerciante(transacaoAtualizada.getComerciante());
        return transacaoRepository.save(transacao);
    }

    public void deletar(Long id) {
        Transacao transacao = buscarPorId(id);
        transacaoRepository.deleteById(id);
    }
}

