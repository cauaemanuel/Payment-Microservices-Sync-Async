package com.payment_api_service.infrastructure.controller;

import com.payment_api_service.application.interactors.InitiateTransferUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Pagamentos", description = "Operações de pagamento")
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private InitiateTransferUseCase initiateTransferUseCase;

    public PaymentController(InitiateTransferUseCase initiateTransferUseCase) {
        this.initiateTransferUseCase = initiateTransferUseCase;
    }

    @Operation(
            summary = "Realiza transferência de pagamento",
            description = "Transfere um valor de uma conta de origem para uma conta de destino.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Transferência realizada com sucesso")
            }
    )
    @PostMapping("/transfer")
    public ResponseEntity transfer(
            @Parameter(description = "ID do destinatário", required = true)
            @RequestParam String destinationId,
            @Parameter(description = "ID da fonte", required = true)
            @RequestParam String sourceId,
            @Parameter(description = "Valor a ser transferido", required = true)
            @RequestParam double amount) {
        initiateTransferUseCase.execute(destinationId, sourceId, amount);
        return ResponseEntity.ok("Payment transferred successfully");
    }
}