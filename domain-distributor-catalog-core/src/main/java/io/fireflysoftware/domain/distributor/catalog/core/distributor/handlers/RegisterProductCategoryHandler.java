package io.fireflysoftware.domain.distributor.catalog.core.distributor.handlers;

import org.fireflyframework.cqrs.annotations.CommandHandlerComponent;
import org.fireflyframework.cqrs.command.CommandHandler;
import io.fireflysoftware.core.distributor.sdk.api.ProductCategoryApi;
import io.fireflysoftware.domain.distributor.catalog.core.distributor.commands.RegisterProductCategoryCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RegisterProductCategoryHandler extends CommandHandler<RegisterProductCategoryCommand, UUID> {

    private final ProductCategoryApi productCategoryApi;

    public RegisterProductCategoryHandler(ProductCategoryApi productCategoryApi) {
        this.productCategoryApi = productCategoryApi;
    }

    @Override
    protected Mono<UUID> doHandle(RegisterProductCategoryCommand cmd) {
        return productCategoryApi.createProductCategory(cmd, UUID.randomUUID().toString())
                .mapNotNull(productCategoryDTO ->
                        Objects.requireNonNull(productCategoryDTO).getId());
    }
}