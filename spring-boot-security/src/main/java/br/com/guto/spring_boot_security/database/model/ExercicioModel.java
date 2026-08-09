package br.com.guto.spring_boot_security.database.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exercicios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExercicioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;
    @Column(name = "grupo_muscular", nullable = false)
    private String grupoMuscular;

}
