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

import org.fireflyframework.cqrs.annotations.CommandHandlerComponent;
import org.fireflyframework.cqrs.command.CommandHandler;
import io.fireflysoftware.core.distributor.sdk.api.LendingContractApi;
import io.fireflysoftware.domain.distributor.catalog.core.distributor.commands.UpdateLeasingContractCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class UpdateLeasingContractHandler extends CommandHandler<UpdateLeasingContractCommand, UUID> {

    private final LendingContractApi lendingContractApi;

    public UpdateLeasingContractHandler(LendingContractApi lendingContractApi) {
        this.lendingContractApi = lendingContractApi;
    }

    @Override
    protected Mono<UUID> doHandle(UpdateLeasingContractCommand cmd) {
        return lendingContractApi.updateLendingContract(cmd.getId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(leasingContractDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(leasingContractDTO)).getId());
    }
}