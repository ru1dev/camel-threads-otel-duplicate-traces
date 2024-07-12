package org.example.jaxrs

import javax.ws.rs.*

@Path("")
interface JaxRsService {
    @GET
    @Path("test")
    fun testRequest(): Any?
}