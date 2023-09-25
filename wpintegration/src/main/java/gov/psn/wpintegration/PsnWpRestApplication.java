
package gov.psn.wpintegration;

import io.helidon.microprofile.server.Server;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


@ApplicationPath("/psn/1.0/")
@ApplicationScoped
public class PsnWpRestApplication  extends Application{
	
	private static Logger logger = LoggerFactory.getLogger(PsnWpRestApplication.class);
	
    public PsnWpRestApplication() {
    }

    public static void main(final String[] args) throws IOException {

        Server server = startServer();
        logger.info("http://localhost:" + server.port());
    }

    public static Server startServer() {
        return Server.create().start();
    }
}
