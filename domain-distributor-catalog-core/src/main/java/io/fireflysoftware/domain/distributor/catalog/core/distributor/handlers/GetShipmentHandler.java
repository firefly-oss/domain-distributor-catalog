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
import io.fireflysoftware.domain.distributor.catalog.core.distributor.queries.GetShipmentQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fireflyframework.cqrs.annotations.QueryHandlerComponent;
import org.fireflyframework.cqrs.query.QueryHandler;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Handler that processes {@link GetShipmentQuery} by delegating to the
 * shipment API to retrieve a single shipment by its identifier.
 */
@Slf4j
@RequiredArgsConstructor
@QueryHandlerComponent(cacheable = false)
public class GetShipmentHandler extends QueryHandler<GetShipmentQuery, ShipmentDTO> {

    private final ShipmentApi shipmentApi;

    @Override
    protected Mono<ShipmentDTO> doHandle(GetShipmentQuery query) {
        log.debug("Fetching shipment with id: {}", query.getShipmentId());
        return shipmentApi.getShipmentById(query.getShipmentId(), UUID.randomUUID().toString());
    }
}
