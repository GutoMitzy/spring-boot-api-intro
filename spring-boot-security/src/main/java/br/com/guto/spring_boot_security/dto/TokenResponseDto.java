package br.com.guto.spring_boot_security.dto;

public record TokenResponseDto (String token, Long expiresIn){

}
