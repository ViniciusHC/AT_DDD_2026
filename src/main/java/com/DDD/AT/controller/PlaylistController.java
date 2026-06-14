package com.DDD.AT.controller;

import com.DDD.AT.entities.Playlist;
import com.DDD.AT.services.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> listarTodas() {
        return ResponseEntity.ok(playlistService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(playlistService.buscarPorId(id));
    }

    @PostMapping("/conta/{idConta}")
    public ResponseEntity<Playlist> criar(@PathVariable Long idConta, @RequestBody Playlist playlist) {
        return ResponseEntity.status(201).body(playlistService.criar(playlist, idConta));
    }

    @PostMapping("/{id}/musicas")
    public ResponseEntity<Playlist> adicionarMusica(@PathVariable Long id, @RequestBody String musica) {
        return ResponseEntity.ok(playlistService.adicionarMusica(id, musica));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Playlist> atualizar(@PathVariable Long id, @RequestBody Playlist playlist) {
        return ResponseEntity.ok(playlistService.atualizar(id, playlist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        playlistService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
