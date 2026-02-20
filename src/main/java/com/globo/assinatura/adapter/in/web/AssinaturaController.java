package com.globo.assinatura.adapter.in.web;

import com.globo.assinatura.adapter.in.web.dto.CancelarAssinaturaResponseDTO;
import com.globo.assinatura.adapter.in.web.dto.CriarAssinaturaRequestDTO;
import com.globo.assinatura.adapter.in.web.dto.CriarAssinaturaResponseDTO;
import com.globo.assinatura.adapter.in.web.mapper.AssinaturaWebMapper;
import com.globo.assinatura.application.port.in.CancelarAssinaturaInputPort;
import com.globo.assinatura.application.port.in.CriarAssinaturaInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assinaturas")
@Tag(name = "Assinaturas", description = "Cadastro e gestão de assinaturas")
public class AssinaturaController {

    private final CriarAssinaturaInputPort criarAssinaturaUseCase;
    private final CancelarAssinaturaInputPort cancelarAssinaturaUseCase;

    public AssinaturaController(
            CriarAssinaturaInputPort criarAssinaturaUseCase,
            CancelarAssinaturaInputPort cancelarAssinaturaUseCase) {
        this.criarAssinaturaUseCase = criarAssinaturaUseCase;
        this.cancelarAssinaturaUseCase = cancelarAssinaturaUseCase;
    }

    @PostMapping
    @Operation(summary = "Criar assinatura", description = "Cadastra uma nova assinatura para o usuário. Um usuário pode ter apenas uma assinatura ativa por vez.")
    public ResponseEntity<CriarAssinaturaResponseDTO> criarAssinatura(
            @RequestBody CriarAssinaturaRequestDTO request) {

        var usuario = AssinaturaWebMapper.toUsuario(request);
        var assinatura = criarAssinaturaUseCase.execute(usuario, request.plano());
        var response = AssinaturaWebMapper.toResponseDTO(assinatura);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/cancelar/{usuarioId}")
    @Operation(summary = "Cancelar assinatura", description = "Cancela a assinatura ativa do usuário. O usuário pode continuar usando o serviço até o fim do ciclo.")
    public ResponseEntity<CancelarAssinaturaResponseDTO> cancelarAssinatura(@PathVariable String usuarioId) {
        var assinatura = cancelarAssinaturaUseCase.executar(java.util.UUID.fromString(usuarioId));
        var response = AssinaturaWebMapper.toCancelarResponseDTO(assinatura);
        return ResponseEntity.ok(response);
    }
}
