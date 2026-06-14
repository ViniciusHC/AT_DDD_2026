package com.DDD.AT.services;
import com.DDD.AT.entities.Assinatura;
import com.DDD.AT.entities.Conta;
import com.DDD.AT.repositories.AssinaturaRepository;
import com.DDD.AT.repositories.ContaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;
    private final ContaRepository contaRepository;


    public AssinaturaService(AssinaturaRepository assinaturaRepository, ContaRepository contaRepository) {
        this.assinaturaRepository = assinaturaRepository;
        this.contaRepository = contaRepository;
    }

    public List<Assinatura> listarTodas() {
        return assinaturaRepository.findAll();
    }

    public Assinatura buscarPorId(Long id) {
        return assinaturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assinatura não encontrada"));
    }

    public Assinatura criarAssinatura(Assinatura assinatura, Long idConta) {
        Conta conta = contaRepository.findById(idConta).orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        if(conta.getAssinatura() != null && conta.getAssinatura().getAtivo()){
            throw new RuntimeException("Conta já possui plano ativo");
        }

        if (!conta.getCartaoAtivo()){
            throw new RuntimeException("Cartão inválido");
        }

        assinatura.setAtivo(true);
        conta.setAssinatura(assinatura);
        return assinaturaRepository.save(assinatura);
    }

    public Assinatura atualizar(Long id, Assinatura assinaturaAtualizada) {
        Assinatura assinatura = buscarPorId(id);
        assinatura.setPlano(assinaturaAtualizada.getPlano());
        assinatura.setAtivo(assinaturaAtualizada.getAtivo());
        return assinaturaRepository.save(assinatura);
    }

    public void deletar(Long id) {
        Assinatura assinatura = buscarPorId(id);
        assinaturaRepository.delete(assinatura);
    }


}
