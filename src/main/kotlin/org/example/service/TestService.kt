package org.example.service

import io.opentelemetry.api.trace.Span
import org.springframework.stereotype.Service

@Service
class TestService {
    fun getTraceId() = Span.current().spanContext.traceId
}