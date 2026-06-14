package com.DDD.AT.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String numeroCatao;
    private Boolean cartaoAtivo;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assinatura_id")
    private Assinatura assinatura;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Musica> musicasFavoritas;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Playlist> playlists;

}
