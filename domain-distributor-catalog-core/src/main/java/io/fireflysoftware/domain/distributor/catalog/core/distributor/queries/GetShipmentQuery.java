package io.fireflysoftware.domain.distributor.catalog.core.distributor.queries;

import io.fireflysoftware.core.distributor.sdk.model.ShipmentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fireflyframework.cqrs.query.Query;

import java.util.UUID;

/**
 * Query to retrieve a single shipment by its identifier.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetShipmentQuery implements Query<ShipmentDTO> {
    private UUID shipmentId;
}
