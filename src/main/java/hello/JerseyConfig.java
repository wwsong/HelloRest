package hello; /**
 * Created by Administrator on 2016/5/3.
 */


import hello.HelloResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        // Register endpoints, providers, ...
        this.registerEndpoints();
    }

    private void registerEndpoints() {
        this.register(HelloResource.class);
        // Available at /<Jersey's servlet path>/application.wadl
        this.register(WadlResource.class);
    }
}
