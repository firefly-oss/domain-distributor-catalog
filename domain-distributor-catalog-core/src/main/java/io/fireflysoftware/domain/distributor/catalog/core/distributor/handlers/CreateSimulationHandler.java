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

import io.fireflysoftware.core.distributor.sdk.api.DistributorSimulationsApi;
import io.fireflysoftware.domain.distributor.catalog.core.distributor.commands.CreateSimulationCommand;
import org.fireflyframework.cqrs.annotations.CommandHandlerComponent;
import org.fireflyframework.cqrs.command.CommandHandler;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

/**
 * Handler that processes {@link CreateSimulationCommand} by delegating to the
 * distributor simulations API.
 */
@CommandHandlerComponent
public class CreateSimulationHandler extends CommandHandler<CreateSimulationCommand, UUID> {

    private final DistributorSimulationsApi distributorSimulationsApi;

    public CreateSimulationHandler(DistributorSimulationsApi distributorSimulationsApi) {
        this.distributorSimulationsApi = distributorSimulationsApi;
    }

    @Override
    protected Mono<UUID> doHandle(CreateSimulationCommand cmd) {
        return distributorSimulationsApi.createDistributorSimulation(cmd.getDistributorId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(simulationDTO -> Objects.requireNonNull(simulationDTO).getId());
    }
}
