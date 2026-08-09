package br.com.guto.spring_boot_security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RegisterRequestDto {
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String cpf;
    @NotBlank
    private String senha;
}
