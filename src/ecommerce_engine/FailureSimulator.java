package ecommerce_engine;

public class FailureSimulator {

    public static void trigger() {

        System.out.println("Triggering failure mode...\n");

        try {
            simulateFailure();
        } catch (Exception e) {
            System.out.println("Failure occurred: " + e.getMessage());
            AuditLogger.log("System failure triggered");
        }
    }

    private static void simulateFailure() {
        throw new RuntimeException("Simulated system crash!");
    }
}