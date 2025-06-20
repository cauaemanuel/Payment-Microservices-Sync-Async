package com.payment_api_service.dto;

public record TransferDto(String destinationId, String sourceId, Double amount) {


}
