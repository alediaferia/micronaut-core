/*
 * Copyright 2017-2019 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.http.originatingips;

import io.micronaut.context.annotation.Requires;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.HttpRequest;

import javax.annotation.Nonnull;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * {@link IpAddressesResolver} implementation based on {@link HttpRequest#getRemoteAddress} method.
 * @author Sergio del Amo
 * @since 1.2.0
 */
@Requires(property = RemoteAddressIpAddressesResolver.PREFIX + ".enabled", notEquals = StringUtils.FALSE)
@Singleton
public class RemoteAddressIpAddressesResolver implements IpAddressesResolver {
    public static final String PREFIX = IpAddressesResolver.PREFIX + ".remote-address";

    /**
     *
     * @param request The Http Request
     * @return a List of IP Addresses.
     */
    @Nonnull
    @Override
    public List<String> originatingIpAddres(HttpRequest<?> request) {
        return Optional.ofNullable(request.getRemoteAddress().getAddress())
                .map(inetAddress -> Collections.singletonList(inetAddress.getHostAddress()))
                .orElseGet(Collections::emptyList);
    }
}
