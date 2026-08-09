package br.com.guto.spring_boot_security.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProdutoDto {
    private String nome;
    private BigDecimal preco;
    private Integer quantidade;
}
