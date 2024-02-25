package net.leskowsky.tinyap.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ErrorReporter {

    /**
     * Send application log messages to stdout by default
     */
    private List<StorageGateway> storageGatewayList = List.of(
            new ConsoleStorageGateway()
    );

    public ErrorReporter() {
    }

    @Autowired
    public ErrorReporter(List<StorageGateway> storageGatewayList) {
        this.storageGatewayList = storageGatewayList;
    }

    public void reportError(String msg, Throwable t, Map<String, String> errorDetails) {
        getStorageGatewayList().forEach(gw -> {
            gw.sendErrorReport(new ErrorReport(msg, t, errorDetails));
        });
    }

    public List<StorageGateway> getStorageGatewayList() {
        return storageGatewayList;
    }

    /**
     * Details about how specific storage strategies work (eg Console (stdout), Sentry, etc)
     */
    static interface StorageGateway {
        public void sendErrorReport(ErrorReport errorReport);
    }

    /**
     * Bridge between error reporter service, and storage gateways
     * @param msg
     * @param cause
     * @param errorDetails
     */
    static record ErrorReport(String msg, Throwable cause, Map<String, String> errorDetails) {
    }

    /**
     * Stdout logger
     */
    @Component
    static class ConsoleStorageGateway implements StorageGateway {

        private final Logger logger = LoggerFactory.getLogger(ConsoleStorageGateway.class);

        @Override
        public void sendErrorReport(ErrorReport errorReport) {
            var details = errorReport.errorDetails.keySet().stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(" "));

            logger.error(String.format("Details: %s, Message: %s", details, errorReport.msg()),
                    errorReport.cause());
        }
    }
}
