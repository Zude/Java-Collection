package ps2.routing;

import ps2.network.Connection;
import ps2.network.Vehicle;

/**
 * A EmptyElement to implement a list for Routing.
 * @author uhl
 */
public class RoutingEmpty implements Routing {

    /**
     * an empty element.
     */
    public final static Routing EMPTY = new RoutingEmpty();

    /**
     * the constructor must exist and does nothing.
     */
    protected RoutingEmpty() {
    }

    /**
     * adds a routingStep to the front with the given connection.                            
     * @param c the connection to add
     * @return the new RoutingStep with the former steps connected
     */
    @Override
    public Routing addToFront(Connection c) {
        return new RoutingStep(c ,this); 
    }

    /**
     * tail() shouldn't be called on empty list.
     * @return false
     */
    @Override
    public Routing tail() {
        assert false : "tail() shouldn't be called on empty list.";
        return null;
    }

    /**
     * head() shouldn't be called on empty list.
     * @return false
     */
    @Override
    public Connection head() {
        assert false : "head() shouldn't be called on empty list.";
        return null;    
	}

    /**
     * gets the count of steps
     * @return 0
     */
    @Override
    public int steps() {
        return 0;
    }

    /**
     * gets the arrival of the vehicle. Here it has to be the start, because
     * there are no steps.
     * @param v     vehicle to use the routing
     * @param start start of the routing
     * @return arrival of the vehicle.
     */
    @Override
    public Integer arrival(Vehicle v, int start) {
        return start;   
    }

    /**
     * gets whether the routing-list is empty.
     * @return true
     */
    @Override
    public boolean isempty() {
        return true;
    }

    /**
     * gets the message of reaching the target because the routing ends here.
     * @return the message the target is reached
     */
    @Override
    public String toString() {
        return " am Ziel!";
    }

}
