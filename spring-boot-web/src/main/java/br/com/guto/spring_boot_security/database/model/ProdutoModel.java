package br.com.guto.spring_boot_security.database.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProdutoModel {
    private Integer id;
    private String nome;
    private BigDecimal preco;
    private Integer quantidade;
}
