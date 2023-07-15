package br.com.banco.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "conta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContaModel {

    @Id
    @Column(name = "id_conta", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_responsavel", nullable = false)
    private String nomeResponsavel;

    @JsonBackReference
    @OneToMany
    @JoinColumn(name = "conta_id")
    private List<TransferenciaModel> transferencias;

}
