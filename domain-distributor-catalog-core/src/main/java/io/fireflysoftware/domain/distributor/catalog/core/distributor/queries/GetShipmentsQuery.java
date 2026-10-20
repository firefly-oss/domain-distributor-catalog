package io.fireflysoftware.domain.distributor.catalog.core.distributor.queries;

import org.fireflyframework.cqrs.query.Query;
import io.fireflysoftware.core.distributor.sdk.model.ShipmentDTO;
import lombok.Builder;
import lombok.Data;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Data
@Builder
public class GetShipmentsQuery implements Query<Flux<ShipmentDTO>> {
    private UUID distributorId;
    private UUID productId;

}