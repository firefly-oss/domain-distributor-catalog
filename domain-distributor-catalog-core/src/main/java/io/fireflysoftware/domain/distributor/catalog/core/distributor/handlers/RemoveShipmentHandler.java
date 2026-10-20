package io.fireflysoftware.domain.distributor.catalog.core.distributor.handlers;

import org.fireflyframework.cqrs.annotations.CommandHandlerComponent;
import org.fireflyframework.cqrs.command.CommandHandler;
import io.fireflysoftware.core.distributor.sdk.api.ShipmentApi;
import io.fireflysoftware.domain.distributor.catalog.core.distributor.commands.RemoveShipmentCommand;
import reactor.core.publisher.Mono;
import java.util.UUID;

@CommandHandlerComponent
public class RemoveShipmentHandler extends CommandHandler<RemoveShipmentCommand, Void> {

    private final ShipmentApi shipmentApi;

    public RemoveShipmentHandler(ShipmentApi shipmentApi) {
        this.shipmentApi = shipmentApi;
    }

    @Override
    protected Mono<Void> doHandle(RemoveShipmentCommand cmd) {
        return shipmentApi.deleteShipment(cmd.shipmentId(), UUID.randomUUID().toString());
    }
}