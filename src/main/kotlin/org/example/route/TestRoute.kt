package org.example.route


import io.opentelemetry.api.trace.Span
import org.apache.camel.Processor
import org.apache.camel.builder.RouteBuilder
import org.springframework.stereotype.Component
import java.util.concurrent.ExecutorService


@Component
class TestRoute(val wrappedExecutorService: ExecutorService) : RouteBuilder() {
    override fun configure() {

        from("cxfrs:bean:restService")
            .id("cxfrs.route")
            .to("direct:mainRoute")


        from("direct:mainRoute")
            .id("main.route")
            .threads()
            .executorService(wrappedExecutorService)
            .to("direct:childRoute")

        from("direct:childRoute")
            .id("childRoute")
            .process(Processor { log.info("trace_id: ${Span.current().spanContext.traceId}") })
            .bean("testService", "getTraceId")

    }
}