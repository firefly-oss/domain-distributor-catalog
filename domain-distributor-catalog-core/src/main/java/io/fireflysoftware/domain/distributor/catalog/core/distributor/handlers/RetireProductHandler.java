package io.fireflysoftware.domain.distributor.catalog.core.distributor.handlers;

import org.fireflyframework.cqrs.annotations.CommandHandlerComponent;
import org.fireflyframework.cqrs.command.CommandHandler;
import io.fireflysoftware.core.distributor.sdk.api.ProductApi;
import io.fireflysoftware.domain.distributor.catalog.core.distributor.commands.RetireProductCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RetireProductHandler extends CommandHandler<RetireProductCommand, UUID> {

    private final ProductApi productApi;

    public RetireProductHandler(ProductApi productApi) {
        this.productApi = productApi;
    }

    @Override
    protected Mono<UUID> doHandle(RetireProductCommand cmd) {
        return productApi.updateProduct(cmd.getDistributorId(), cmd.getId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(partyStatusDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(partyStatusDTO).getId()));
    }
}