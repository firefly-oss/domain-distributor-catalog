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


package io.fireflysoftware.domain.distributor.catalog.core.distributor.services;

import io.fireflysoftware.core.distributor.sdk.model.DistributorSimulationDTO;
import io.fireflysoftware.domain.distributor.catalog.core.distributor.commands.CreateSimulationCommand;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Service interface for distributor simulation operations.
 */
public interface SimulationService {

    /**
     * Creates a new simulation for the specified distributor.
     *
     * @param distributorId the unique identifier of the distributor
     * @param command the command containing simulation details
     * @return a {@code Mono} emitting the unique identifier of the created simulation
     */
    Mono<UUID> createSimulation(UUID distributorId, CreateSimulationCommand command);

    /**
     * Retrieves a simulation by its identifier for a given distributor.
     *
     * @param distributorId the unique identifier of the distributor
     * @param simulationId the unique identifier of the simulation
     * @return a {@code Mono} emitting the {@link DistributorSimulationDTO} for the requested simulation
     */
    Mono<DistributorSimulationDTO> getSimulation(UUID distributorId, UUID simulationId);
}
