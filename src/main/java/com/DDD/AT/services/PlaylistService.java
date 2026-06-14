package com.DDD.AT.services;

import com.DDD.AT.entities.Conta;
import com.DDD.AT.entities.Playlist;
import com.DDD.AT.repositories.ContaRepository;
import com.DDD.AT.repositories.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final ContaRepository contaRepository;

    public PlaylistService(PlaylistRepository playlistRepository, ContaRepository contaRepository) {
        this.playlistRepository = playlistRepository;
        this.contaRepository = contaRepository;
    }

    public List<Playlist> listarTodas() {
        return playlistRepository.findAll();
    }

    public Playlist buscarPorId(Long id) {
        return playlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist não encontrada"));
    }

    public Playlist criar(Playlist playlist, Long idConta) {
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        playlist.setConta(conta);
        playlist.setMusicas(new ArrayList<>());
        return playlistRepository.save(playlist);
    }

    public Playlist adicionarMusica(Long id, String musica) {
        Playlist playlist = buscarPorId(id);
        playlist.getMusicas().add(musica);
        return playlistRepository.save(playlist);
    }

    public Playlist atualizar(Long id, Playlist playlistAtualizada) {
        Playlist playlist = buscarPorId(id);
        playlist.setNome(playlistAtualizada.getNome());
        playlist.setMusicas(playlistAtualizada.getMusicas());
        return playlistRepository.save(playlist);
    }

    public void deletar(Long id) {
        Playlist playslist = buscarPorId(id);
        playlistRepository.delete(playslist);
    }
}