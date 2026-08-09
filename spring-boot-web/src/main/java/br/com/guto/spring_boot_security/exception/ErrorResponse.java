package br.com.guto.spring_boot_security.exception;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ErrorResponse {
    private String message;
    private Integer status;
}
