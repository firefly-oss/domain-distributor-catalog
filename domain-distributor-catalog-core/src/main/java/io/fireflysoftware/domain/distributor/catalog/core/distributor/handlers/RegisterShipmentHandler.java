package io.fireflysoftware.domain.distributor.catalog.core.distributor.handlers;

import org.fireflyframework.cqrs.annotations.CommandHandlerComponent;
import org.fireflyframework.cqrs.command.CommandHandler;
import io.fireflysoftware.core.distributor.sdk.api.ShipmentApi;
import io.fireflysoftware.domain.distributor.catalog.core.distributor.commands.RegisterShipmentCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RegisterShipmentHandler extends CommandHandler<RegisterShipmentCommand, UUID> {

    private final ShipmentApi shipmentApi;

    public RegisterShipmentHandler(ShipmentApi shipmentApi) {
        this.shipmentApi = shipmentApi;
    }

    @Override
    protected Mono<UUID> doHandle(RegisterShipmentCommand cmd) {
        return shipmentApi.createShipment(cmd, UUID.randomUUID().toString())
                .mapNotNull(shipmentDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(shipmentDTO)).getId());
    }
}