package br.com.guto.spring_boot_security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ExercicioDto {
    @NotBlank
    private String nome;
    @NotBlank
    private String grupoMuscular;
}
