package br.com.guto.spring_boot_security.dto;

import java.math.BigDecimal;

public interface AvaliacaoFisicaProjection {
    Integer getIdAluno();
    String getNomeAluno();
    Integer getIdAvaliacao();
    BigDecimal getPeso();
    BigDecimal getAltura();
    BigDecimal getPorcentagemGorduraCorporal();
}

