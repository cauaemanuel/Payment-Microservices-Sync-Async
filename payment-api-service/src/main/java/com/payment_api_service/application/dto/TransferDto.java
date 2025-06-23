package com.payment_api_service.application.dto;

public record TransferDto(String destinationId, String sourceId, Double amount) {


}
