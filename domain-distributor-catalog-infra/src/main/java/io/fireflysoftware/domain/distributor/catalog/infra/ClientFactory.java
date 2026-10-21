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


package io.fireflysoftware.domain.distributor.catalog.infra;

import io.fireflysoftware.core.distributor.sdk.api.*;
import io.fireflysoftware.core.distributor.sdk.invoker.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Default implementation of the ClientFactory interface.
 * Creates client service instances using the appropriate API clients and dependencies.
 */
@Component
public class ClientFactory {

    private final ApiClient apiClient;

    @Autowired
    public ClientFactory(
            DistributorCatalogProperties distributorCatalogProperties) {
        this.apiClient = new ApiClient();
        this.apiClient.setBasePath(distributorCatalogProperties.getBasePath());
    }

    @Bean
    public ProductApi productApi() {
        return new ProductApi(apiClient);
    }

    @Bean
    public ProductCategoryApi productCategoryApi() {
        return new ProductCategoryApi(apiClient);
    }

    @Bean
    public LendingTypeApi lendingTypeApi() {
        return new LendingTypeApi(apiClient);
    }

    @Bean
    public LendingConfigurationApi lendingConfigurationApi() {
        return new LendingConfigurationApi(apiClient);
    }

    @Bean
    public LendingContractApi lendingContractApi() {
        return new LendingContractApi(apiClient);
    }

    @Bean
    public ShipmentApi shipmentApi() {
        return new ShipmentApi(apiClient);
    }

    @Bean
    public DistributorSimulationsApi distributorSimulationsApi() {
        return new DistributorSimulationsApi(apiClient);
    }

}
