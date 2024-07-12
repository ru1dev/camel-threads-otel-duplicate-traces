package org.example.route


import io.opentelemetry.api.trace.Span
import org.apache.camel.Processor
import org.apache.camel.builder.RouteBuilder
import org.springframework.stereotype.Component


@Component
class TestRoute : RouteBuilder() {
    override fun configure() {

        from("cxfrs:bean:restService")
            .id("cxfrs.route")
            .to("direct:mainRoute")


        from("direct:mainRoute")
            .id("main.route")
            .threads(2)
            .to("direct:childRoute")

        from("direct:childRoute")
            .id("childRoute")
            .process(Processor { log.info("trace_id: ${Span.current().spanContext.traceId}") })
            .bean("testService", "getTraceId")

    }
}