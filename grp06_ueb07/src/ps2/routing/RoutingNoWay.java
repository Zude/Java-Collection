package ps2.routing;

/**
 * A EmptyElement to implement a list for Routing.
 * @author uhl
 */
public class RoutingNoWay extends RoutingEmpty {

    /**
     * an empty element.
     */
    public final static Routing NOWAY = new RoutingNoWay();

    /**
     * the constructor must exist and does nothing.
     */
    protected RoutingNoWay() {
    }


    /**
     * gets the message of reaching the target because the routing ends here.
     * @return the message the target is reached
     */
    @Override
    public String toString() {
        return "kein Weg vorhanden!";
    }

}
