package com.DDD.AT.services;
import com.DDD.AT.entities.Conta;
import com.DDD.AT.entities.Musica;
import com.DDD.AT.repositories.ContaRepository;
import com.DDD.AT.repositories.MusicaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MusicaService {

    private final MusicaRepository musicaRepository;
    private final ContaRepository contaRepository;

    public MusicaService(MusicaRepository musicaRepository, ContaRepository contaRepository) {
        this.musicaRepository = musicaRepository;
        this.contaRepository = contaRepository;
    }

    public List<Musica> listarTodas() {
        return musicaRepository.findAll();
    }

    public Musica buscarPorId(Long id) {
        return musicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Música não encontrada"));
    }

    public Musica favoritar(Musica musica, Long idConta) {
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        boolean jaFavoritada = conta.getMusicasFavoritas().stream()
                .filter(m -> m.getTitulo().equals(musica.getTitulo())
                        && m.getArtista().equals(musica.getArtista()))
                .findFirst()
                .isPresent();

        if (jaFavoritada) {
            throw new RuntimeException("Música já favoritada");
        }

        musica.setConta(conta);
        return musicaRepository.save(musica);
    }

    public Musica atualizar(Long id, Musica musicaAtualizada) {
        Musica musica = buscarPorId(id);
        musica.setTitulo(musicaAtualizada.getTitulo());
        musica.setArtista(musicaAtualizada.getArtista());
        return musicaRepository.save(musica);
    }

    public void deletar(Long id) {
        Musica musica = buscarPorId(id);
        musicaRepository.delete(musica);
    }
}
