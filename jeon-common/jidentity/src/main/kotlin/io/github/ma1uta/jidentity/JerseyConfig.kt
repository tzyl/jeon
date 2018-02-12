package io.github.ma1uta.jidentity

import io.github.ma1uta.jeon.exception.ExceptionHandler
import io.github.ma1uta.matrix.identity.api.IdentityApi
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.boot.autoconfigure.jersey.ResourceConfigCustomizer
import org.springframework.stereotype.Component

@Component
class JerseyConfig(val clientApis: List<IdentityApi>, val exceptionHandler: ExceptionHandler) : ResourceConfigCustomizer {
    override fun customize(config: ResourceConfig?) {
        clientApis.forEach { config!!.register(it::class.java) }
        config!!.register(exceptionHandler)
    }
}
