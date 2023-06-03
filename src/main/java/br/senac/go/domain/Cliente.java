package br.senac.go.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Entity
@Table(name = "cliente")
public class Cliente extends BaseModel {

    @Column(length = 50, nullable = false)
    private String nome;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;


    @ManyToOne
    @JoinColumn(name = "email")
    private Email email;
    @ManyToOne
    @JoinColumn(name = "endereco")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "telefone")
    private Telefone telefone;

    @ManyToOne
    @JoinColumn(name = "conta")
    private Conta conta;

}
