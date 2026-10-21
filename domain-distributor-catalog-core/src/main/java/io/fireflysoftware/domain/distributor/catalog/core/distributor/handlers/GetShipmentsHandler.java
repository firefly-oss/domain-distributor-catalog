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

import org.fireflyframework.cqrs.annotations.QueryHandlerComponent;
import org.fireflyframework.cqrs.query.QueryHandler;
import io.fireflysoftware.core.distributor.sdk.api.ShipmentApi;
import io.fireflysoftware.core.distributor.sdk.model.ShipmentDTO;
import io.fireflysoftware.domain.distributor.catalog.core.distributor.queries.GetShipmentsQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@QueryHandlerComponent(cacheable = false)
public class GetShipmentsHandler extends QueryHandler<GetShipmentsQuery, Flux<ShipmentDTO>> {

    private final ShipmentApi shipmentApi;

    public GetShipmentsHandler(ShipmentApi shipmentApi) {
        this.shipmentApi = shipmentApi;
    }

    @Override
    protected Mono<Flux<ShipmentDTO>> doHandle(GetShipmentsQuery cmd) {
        return Mono.just(shipmentApi.getShipmentsByProductId(cmd.getProductId(), UUID.randomUUID().toString()));
    }
}