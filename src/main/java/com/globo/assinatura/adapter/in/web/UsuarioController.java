package com.globo.assinatura.adapter.in.web;

import com.globo.assinatura.adapter.in.web.dto.CriarUsuarioRequestDTO;
import com.globo.assinatura.adapter.in.web.dto.CriarUsuarioResponseDTO;
import com.globo.assinatura.adapter.in.web.mapper.UsuarioWebMapper;
import com.globo.assinatura.application.port.in.CriarUsuarioInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Cadastro de usuários")
public class UsuarioController {

    private final CriarUsuarioInputPort criarUsuarioUseCase;

    public UsuarioController(CriarUsuarioInputPort criarUsuarioUseCase) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
    }

    @PostMapping
    @Operation(summary = "Cadastrar usuário", description = "Cria um novo usuário com e-mail e nome. O e-mail deve ser único.")
    public ResponseEntity<CriarUsuarioResponseDTO> criarUsuario(@RequestBody CriarUsuarioRequestDTO request) {
        var usuario = criarUsuarioUseCase.execute(request.email(), request.nome());
        var response = UsuarioWebMapper.toResponseDTO(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
