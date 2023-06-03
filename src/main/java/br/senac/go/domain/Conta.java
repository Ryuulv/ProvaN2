package br.senac.go.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "conta")
public class Conta extends BaseModel {


    private String numero;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;
}
