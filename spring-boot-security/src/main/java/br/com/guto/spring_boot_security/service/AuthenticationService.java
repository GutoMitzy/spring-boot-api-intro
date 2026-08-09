package br.com.guto.spring_boot_security.service;

import br.com.guto.spring_boot_security.config.TokenProvider;
import br.com.guto.spring_boot_security.database.model.AlunoModel;
import br.com.guto.spring_boot_security.database.model.RoleModel;
import br.com.guto.spring_boot_security.database.repository.IAlunosRepository;
import br.com.guto.spring_boot_security.database.repository.IRolesRepository;
import br.com.guto.spring_boot_security.dto.LoginRequestDto;
import br.com.guto.spring_boot_security.dto.RegisterRequestDto;
import br.com.guto.spring_boot_security.dto.TokenResponseDto;
import br.com.guto.spring_boot_security.enums.RoleTypeEnum;
import br.com.guto.spring_boot_security.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final IAlunosRepository alunosRepository;
    private final IRolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public void register(RegisterRequestDto dto) throws BadRequestException {
        AlunoModel aluno = alunosRepository.findByEmail(dto.getEmail())
                .orElse(null);

        if(aluno != null){
            throw new BadRequestException("Não foi possível cadastrar o email!");
        }

        RoleModel role = rolesRepository.findByNome(RoleTypeEnum.ROLE_ALUNO.name())
                .orElseGet(() -> rolesRepository.save(RoleModel.builder()
                        .nome(RoleTypeEnum.ROLE_ALUNO.name())
                        .build()
                ));

        alunosRepository.save(AlunoModel.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .roles(Set.of(role))
                .senha(passwordEncoder.encode(dto.getSenha()))
                .build());
    }

    public TokenResponseDto login(LoginRequestDto loginRequestDto) throws BadRequestException {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken((loginRequestDto.getEmail()), loginRequestDto.getSenha()));
            String token = tokenProvider.gerarToken(authentication);
            return new TokenResponseDto(token, expirationTime);
        } catch(BadCredentialsException bce)  {
            throw new BadRequestException("Credenciais inválidas!");
        } catch(Exception e) {
            throw e;
        }
    }
}
