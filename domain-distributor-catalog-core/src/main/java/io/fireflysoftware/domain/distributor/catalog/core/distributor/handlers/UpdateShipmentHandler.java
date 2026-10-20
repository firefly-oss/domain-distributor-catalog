package io.fireflysoftware.domain.distributor.catalog.core.distributor.handlers;

import org.fireflyframework.cqrs.annotations.CommandHandlerComponent;
import org.fireflyframework.cqrs.command.CommandHandler;
import io.fireflysoftware.core.distributor.sdk.api.ShipmentApi;
import io.fireflysoftware.domain.distributor.catalog.core.distributor.commands.UpdateShipmentCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class UpdateShipmentHandler extends CommandHandler<UpdateShipmentCommand, UUID> {

    private final ShipmentApi shipmentApi;

    public UpdateShipmentHandler(ShipmentApi shipmentApi) {
        this.shipmentApi = shipmentApi;
    }

    @Override
    protected Mono<UUID> doHandle(UpdateShipmentCommand cmd) {
        return shipmentApi.updateShipment(cmd.getId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(shipmentDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(shipmentDTO)).getId());
    }
}