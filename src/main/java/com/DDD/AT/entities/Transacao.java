package com.DDD.AT.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;
    private String comerciante;
    private Boolean aprovada;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    @JsonIgnore
    public Conta conta;

}
