package br.com.guto.spring_boot_security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LoginRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
}
