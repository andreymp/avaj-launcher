package exceptions;

public class SimulationException extends Exception {
    public SimulationException() { super(); }
    public SimulationException(String msg) { super(msg); }
    public SimulationException(Throwable err) { super(err); }
    public SimulationException(String msg, Throwable err) { super(msg, err); }
}