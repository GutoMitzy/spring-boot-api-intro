package br.com.guto.spring_boot_security.controller;

import br.com.guto.spring_boot_security.dto.LoginRequestDto;
import br.com.guto.spring_boot_security.dto.RegisterRequestDto;
import br.com.guto.spring_boot_security.dto.TokenResponseDto;
import br.com.guto.spring_boot_security.exception.BadRequestException;
import br.com.guto.spring_boot_security.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid RegisterRequestDto registerRequestDto) throws BadRequestException {
        authenticationService.register(registerRequestDto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponseDto login(@RequestBody @Valid LoginRequestDto loginRequestDto) throws BadRequestException {
        return authenticationService.login(loginRequestDto);
    }
}
