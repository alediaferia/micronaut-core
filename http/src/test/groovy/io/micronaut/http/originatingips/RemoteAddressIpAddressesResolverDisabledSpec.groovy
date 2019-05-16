package io.micronaut.http.originatingips

import io.micronaut.context.ApplicationContext
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class RemoteAddressIpAddressesResolverDisabledSpec extends Specification {
    @Shared
    @AutoCleanup
    ApplicationContext applicationContext = ApplicationContext.run([
            'micronaut.http.originating-ips.remote-address.enabled': false
    ])

    void "Bean RemoteAddressIpAddressesResolver can be disabled"() {
        expect:
        !applicationContext.containsBean(RemoteAddressIpAddressesResolver)
    }
}