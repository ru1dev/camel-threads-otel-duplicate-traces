package org.example.configuration


import org.apache.cxf.Bus
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean
import org.example.jaxrs.JaxRsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfiguration(val buz: Bus) {
    @Bean
    fun restService() = JAXRSServerFactoryBean().apply {
            setResourceClasses(JaxRsService::class.java)
            bus = buz
            address = "/rest"
        }
}