package demo;

import org.tinylog.Level;
import org.tinylog.Logger;

/**
 * Demo application for demonstrating reconfiguring tinylog at runtime.
 */
public class Application {

    public static void main(String[] args) throws Exception {
        reconfigure(Level.INFO, "-");
        Logger.debug("First Log Entry");
        Logger.info("First Log Entry");

        reconfigure(Level.DEBUG, "-");
        Logger.trace("Second Log Entry");
        Logger.debug("Second Log Entry");

        reconfigure(Level.INFO, "foo");
        Logger.tag("foo").info("Third Log Entry");
        Logger.tag("bar").info("Third Log Entry");

        reconfigure(Level.INFO, "bar");
        Logger.tag("foo").info("Fourth Log Entry");
        Logger.tag("bar").info("Fourth Log Entry");
    }

    private static void reconfigure(Level level, String tag) throws Exception {
        // Here, the original entries of `tinylog.properties` are overwritten via system properties. However, you can
        // also manipulate your `tinylog.properties` file directly, if it is in a writable location and not part of the
        // classpath.

        System.setProperty("tinylog.writerConsole.level", level.toString());
        System.setProperty("tinylog.writerConsole.tag", tag);

        ReconfigurableLoggingProvider.reload();
    }

}