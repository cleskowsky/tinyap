package net.leskowsky.tinyap.services;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ErrorReporterTests {

    // errors are logged by default
    @Test
    public void reportError() {
        // Given an error reporting service
        ErrorReporter errorReporter = new ErrorReporter();

        // When it is created without storage gateways
        // Then by default a console reporter is added
        assertEquals(1, errorReporter.getStorageGatewayList().size());
        assertEquals(ErrorReporter.ConsoleStorageGateway.class,
                errorReporter.getStorageGatewayList().getFirst().getClass());
    }

    // error reports can include details
    @Test
    public void reportErrorWithDetails() {
        // Given an error with details
        var msg = "An error";
        var cause = new RuntimeException("A cause");
        var details = Map.of(
                "key", "value"
        );

        // When error reporter is sent an error
        var mockedStorageGateway = mock(ErrorReporter.StorageGateway.class);
        ErrorReporter errorReporter = new ErrorReporter(List.of(mockedStorageGateway));
        errorReporter.reportError(msg, cause, details);

        // Then the storage gw receives an error report including all relevant details
        var expected = new ErrorReporter.ErrorReport(msg, cause, details);
        verify(mockedStorageGateway).sendErrorReport(expected);
    }

    // errors are sent to all storage gateways
}
