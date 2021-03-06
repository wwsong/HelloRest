package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloResource.class);

    @GET
    @Path("v1/hello/{name}")
    public Response getHelloVersionInUrl(@PathParam("name") String name) {
        LOGGER.info("getHelloVersionInUrl() v1");
        return this.getHello(name, "Version 1 - passed in URL");
    }

    @GET
    @Path("hello/{name}")
    @Consumes("application/vnd.asimio-v1+json")
    @Produces("application/vnd.asimio-v1+json")
    public Response getHelloVersionInAcceptHeader(@PathParam("name") String name) {
        LOGGER.info("getHelloVersionInAcceptHeader() v1");
        return this.getHello(name, "Version 1 - passed in Accept Header");
    }

    @GET
    @Path("html")
    @Produces(MediaType.TEXT_HTML)
    public InputStream getHtml() {
        GHello result = new GHello();
        InputStream stream = new ByteArrayInputStream(
                result.getMsg().getBytes(StandardCharsets.UTF_8));
        return stream;

        //return Response.status(Response.Status.OK).entity(result).build();
    }

    private Response getHello(String name, String partialMsg) {
        if ("404".equals(name)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Hello result = new Hello();
        result.setMsg(String.format("hello.Hello %s. %s", name, partialMsg));
        return Response.status(Response.Status.OK).entity(result).build();
    }
}