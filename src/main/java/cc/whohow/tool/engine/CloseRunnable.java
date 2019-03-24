package cc.whohow.tool.engine;

import javafx.event.Event;
import javafx.event.EventHandler;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CloseRunnable<E extends Event> implements AutoCloseable, Runnable, EventHandler<E> {
    private AutoCloseable closeable;

    public CloseRunnable(AutoCloseable closeable) {
        this.closeable = closeable;
    }

    @Override
    public void close() throws Exception {
        closeable.close();
    }

    @Override
    public void run() {
        try {
            closeable.close();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void handle(E event) {
        run();
    }
}
