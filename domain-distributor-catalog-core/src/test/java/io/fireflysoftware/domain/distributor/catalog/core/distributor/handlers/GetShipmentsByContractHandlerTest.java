/*
 * Copyright 2025 Firefly Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package io.fireflysoftware.domain.distributor.catalog.core.distributor.handlers;

import io.fireflysoftware.core.distributor.sdk.api.ShipmentApi;
import io.fireflysoftware.core.distributor.sdk.model.ShipmentDTO;
import io.fireflysoftware.domain.distributor.catalog.core.distributor.queries.GetShipmentsByContractQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link GetShipmentsByContractHandler}.
 */
@ExtendWith(MockitoExtension.class)
class GetShipmentsByContractHandlerTest {

    @Mock
    private ShipmentApi shipmentApi;

    private GetShipmentsByContractHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GetShipmentsByContractHandler(shipmentApi);
    }

    @Test
    void doHandle_shouldReturnShipmentDTO_whenQuerySucceeds() {
        UUID lendingContractId = UUID.randomUUID();

        GetShipmentsByContractQuery query = GetShipmentsByContractQuery.builder()
                .lendingContractId(lendingContractId)
                .build();

        ShipmentDTO responseDTO = new ShipmentDTO(UUID.randomUUID(), null, null, null, null);

        when(shipmentApi.getShipmentsByLendingContractId(eq(lendingContractId), any(String.class)))
                .thenReturn(Mono.just(responseDTO));

        StepVerifier.create(handler.doHandle(query))
                .expectNext(responseDTO)
                .verifyComplete();
    }

    @Test
    void doHandle_shouldPropagateError_whenApiCallFails() {
        UUID lendingContractId = UUID.randomUUID();

        GetShipmentsByContractQuery query = GetShipmentsByContractQuery.builder()
                .lendingContractId(lendingContractId)
                .build();

        RuntimeException exception = new RuntimeException("API failure");

        when(shipmentApi.getShipmentsByLendingContractId(eq(lendingContractId), any(String.class)))
                .thenReturn(Mono.error(exception));

        StepVerifier.create(handler.doHandle(query))
                .expectError(RuntimeException.class)
                .verify();
    }
}
