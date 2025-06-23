package com.payment_processor_service.service;

import com.payment_processor_service.entity.TransactionMessageDto;
import com.payment_processor_service.producer.PaymentProcessorProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
@Slf4j
public class PaymentService {

    private PaymentProcessorProducer producer;

    public PaymentService(PaymentProcessorProducer producer) {
        this.producer = producer;
    }

    public void processPayment(TransactionMessageDto dto) {
        // O banco realiza diversas validações internas (ex:  limites, bloqueios, etc).
        // Aqui simulamos com um valor aleatório.
        boolean permitido = new Random().nextBoolean();

        if (!permitido) {
            // Envia para a fila do RabbitMQ para registrar como pagamento rejeitado
            producer.sendRejectedTransactionMessage(dto);
            log.info("Pagamento rejeitado para o usuário: {}", dto.getSenderUserId());
        } else {
            // Processa normalmente o pagamento
            producer.sendAcceptedTransactionMessage(dto);
            log.info("Pagamento aceito para o usuário: {}", dto.getSenderUserId());
        }
    }
}