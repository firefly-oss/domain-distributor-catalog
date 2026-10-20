package io.fireflysoftware.domain.distributor.catalog.core.distributor.handlers;

import org.fireflyframework.cqrs.annotations.CommandHandlerComponent;
import org.fireflyframework.cqrs.command.CommandHandler;
import io.fireflysoftware.core.distributor.sdk.api.ProductCategoryApi;
import io.fireflysoftware.domain.distributor.catalog.core.distributor.commands.RemoveProductCategoryCommand;
import reactor.core.publisher.Mono;
import java.util.UUID;

@CommandHandlerComponent
public class RemoveProductCategoryHandler extends CommandHandler<RemoveProductCategoryCommand, Void> {

    private final ProductCategoryApi productCategoryApi;

    public RemoveProductCategoryHandler(ProductCategoryApi productCategoryApi) {
        this.productCategoryApi = productCategoryApi;
    }

    @Override
    protected Mono<Void> doHandle(RemoveProductCategoryCommand cmd) {
        return productCategoryApi.deleteProductCategory(cmd.getCategoryId(), UUID.randomUUID().toString());
    }
}