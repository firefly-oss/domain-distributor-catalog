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


package io.fireflysoftware.domain.distributor.catalog.web.controller;

import io.fireflysoftware.core.distributor.sdk.model.DistributorSimulationDTO;
import io.fireflysoftware.domain.distributor.catalog.core.distributor.commands.CreateSimulationCommand;
import io.fireflysoftware.domain.distributor.catalog.core.distributor.services.SimulationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/distributors/{distributorId}/simulations")
@RequiredArgsConstructor
@Tag(name = "Simulations", description = "Distributor simulation operations")
public class SimulationController {

    private final SimulationService simulationService;

    @Operation(summary = "Create simulation", description = "Create a new simulation for the specified distributor.")
    @PostMapping
    public Mono<ResponseEntity<UUID>> createSimulation(
            @PathVariable UUID distributorId,
            @Valid @RequestBody CreateSimulationCommand command) {
        return simulationService.createSimulation(distributorId, command)
                .map(simulationId -> ResponseEntity.status(HttpStatus.CREATED).body(simulationId));
    }

    @Operation(summary = "Get simulation", description = "Retrieve a simulation by its identifier for a given distributor.")
    @GetMapping(value = "/{simulationId}")
    public Mono<ResponseEntity<DistributorSimulationDTO>> getSimulation(
            @PathVariable UUID distributorId,
            @PathVariable UUID simulationId) {
        return simulationService.getSimulation(distributorId, simulationId)
                .map(ResponseEntity::ok);
    }
}
