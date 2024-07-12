package org.example.configuration

import io.opentelemetry.context.Context
import org.apache.camel.CamelContext
import org.apache.camel.ThreadPoolRejectedPolicy
import org.apache.camel.builder.ThreadPoolProfileBuilder
import org.apache.camel.spi.ExecutorServiceManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ExecutorService


@Configuration
class ExecutorServiceConfiguration(val camelContext: CamelContext) {

    @Bean()
    fun wrappedExecutorService(): ExecutorService? {
        val manager: ExecutorServiceManager = camelContext.getExecutorServiceManager()
        val profile = ThreadPoolProfileBuilder("wrappedExecutorService")
            .poolSize(20)
            .maxPoolSize(100)
            .keepAliveTime(60)
            .maxQueueSize(100)
            .allowCoreThreadTimeOut(false)
            .rejectedPolicy(ThreadPoolRejectedPolicy.Abort)
            .build()
        return Context.taskWrapping(manager.newThreadPool(this, "executorService", profile))
    }
}