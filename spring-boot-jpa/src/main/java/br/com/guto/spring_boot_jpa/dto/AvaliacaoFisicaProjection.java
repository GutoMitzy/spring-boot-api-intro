package br.com.guto.spring_boot_jpa.dto;

import java.math.BigDecimal;

public interface AvaliacaoFisicaProjection {
    Integer getIdAluno();
    String getNomeAluno();
    Integer getIdAvaliacao();
    BigDecimal getPeso();
    BigDecimal getAltura();
    BigDecimal getPorcentagemGorduraCorporal();
}

