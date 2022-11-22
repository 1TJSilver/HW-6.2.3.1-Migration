import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws IOException, LifecycleException {
        final Tomcat tomcat = new Tomcat();
        var basedir = Files.createTempDirectory("tomcat");
        basedir.toFile().deleteOnExit();
        tomcat.setBaseDir(basedir.toAbsolutePath().toString());

        final var connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp("", ".");

        tomcat.start();
        tomcat.getServer().await();
    }
}
