package com.DDD.AT.controller;

import com.DDD.AT.entities.Assinatura;
import com.DDD.AT.services.AssinaturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assinaturas")
public class AssinaturaController {

    private final AssinaturaService assinaturaService;

    public AssinaturaController(AssinaturaService assinaturaService) {
        this.assinaturaService = assinaturaService;
    }

    @GetMapping
    public ResponseEntity<List<Assinatura>> listarTodas() {
        return ResponseEntity.ok(assinaturaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assinatura> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(assinaturaService.buscarPorId(id));
    }

    @PostMapping("/conta/{idConta}")
    public ResponseEntity<Assinatura> criar(@PathVariable Long idConta, @RequestBody Assinatura assinatura) {
        return ResponseEntity.status(201).body(assinaturaService.criarAssinatura(assinatura, idConta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Assinatura> atualizar(@PathVariable Long id, @RequestBody Assinatura assinatura) {
        return ResponseEntity.ok(assinaturaService.atualizar(id, assinatura));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        assinaturaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
