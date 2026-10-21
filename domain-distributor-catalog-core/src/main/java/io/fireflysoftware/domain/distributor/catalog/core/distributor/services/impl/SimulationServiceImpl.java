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


package io.fireflysoftware.domain.distributor.catalog.core.distributor.services.impl;

import io.fireflysoftware.core.distributor.sdk.model.DistributorSimulationDTO;
import io.fireflysoftware.domain.distributor.catalog.core.distributor.commands.CreateSimulationCommand;
import io.fireflysoftware.domain.distributor.catalog.core.distributor.queries.GetSimulationQuery;
import io.fireflysoftware.domain.distributor.catalog.core.distributor.services.SimulationService;
import org.fireflyframework.cqrs.command.CommandBus;
import org.fireflyframework.cqrs.query.QueryBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Default implementation of {@link SimulationService} that delegates to the
 * CQRS command and query buses.
 */
@Service
public class SimulationServiceImpl implements SimulationService {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    @Autowired
    public SimulationServiceImpl(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @Override
    public Mono<UUID> createSimulation(UUID distributorId, CreateSimulationCommand command) {
        return commandBus.send(command.withDistributorId(distributorId));
    }

    @Override
    public Mono<DistributorSimulationDTO> getSimulation(UUID distributorId, UUID simulationId) {
        return queryBus.query(GetSimulationQuery.builder()
                .distributorId(distributorId)
                .simulationId(simulationId)
                .build());
    }
}
