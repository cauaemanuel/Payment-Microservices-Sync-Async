package com.wallet_service.domain.messaging;

import com.wallet_service.application.dto.TransactionMessageDto;

public interface WalletEventPublisher {

    void processSucessfulPayment(TransactionMessageDto dto);
}
