package ps2.routing;

import ps2.network.Connection;
import ps2.network.Vehicle;

/**
 * A RoutingStep contains a connection and the rest-routing.
 *
 * @author uhl
 */
public class RoutingStep implements Routing {

    /**
     * the connection at this step
     */
    private Connection element;
    /*
     * the rest routing
     */
    private Routing tail;

    /**
     * constructs a routingStep with the given connection and the rest-routing.
     *
     * @param element the connection to add
     * @param tail the rest routing
     */
    public RoutingStep(Connection element, Routing tail) {
        this.element = element;
        this.tail = tail;
    }

    @Override
    public Routing addToFront(Connection c) {
        return new RoutingStep(c, this);
    }

    @Override
    public Routing tail() {

        return this.tail;
    }

    @Override
    public Connection head() {
        return this.element;
    }

    @Override
    public int steps() {

        return 1 + this.tail().steps();
    }

    @Override
    public Integer arrival(Vehicle v, int start) {
      
        return this.tail().arrival(v, this.head().arrive(v, start));
    }

    @Override
    public boolean isempty() {
        return false;
    }

    /**
     * gets a description of this connection and the rest routing
     *
     * @return a description of this connection and the rest routing
     */
    @Override
    public String toString() {
        return "[" + this.head().toString() + "], " + this.tail().toString() ;

    }

}
