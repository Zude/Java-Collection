package ps2.routing;

import ps2.network.Connection;
import ps2.network.Location;
import ps2.network.Network;
import ps2.network.Vehicle;

/**
 * A DijkstraRoutingFinder holds the start, the vehicle and the DijkstraNework.
 *
 * @author uhl
 */
public class DijkstraRoutingFinder implements RoutingFinder {

    /**
     * the location it starts from
     */
    private Location from;
    /**
     * the vehicle that uses the route
     */
    private Vehicle v;
    /**
     * the Dijkstra network to use
     */
    private DijkstraNetwork net;

    /**
     * the constructor sets its net to a DijkstraNetwork constructed of n
     *
     * @param n the network to use
     */
    public DijkstraRoutingFinder(Network n) {
        this.net = new DijkstraNetwork(n);
    }

    /**
     * performs the Dijkstra-algorithm starting at location from with the
     * vehicle v. Sets the parameters to the class-attributes. Creates a
     * DijkstraLocation to start got by the from-key and sets its arrival to 0
     * and predecessor to null. Beginning with the minNonVisitedLocation all
     * connected locations are visited. A message on System.out reports the
     * computing of a new Dijkstra network.
     *
     * @param from the start
     * @param v the used vehicle
     */
    private void performDijkstra(Location from, Vehicle v) {

        DijkstraLocation current;

        //Schritt 1: Alle Orte mit Null Initialiesieren 
        for (int index = 0; index < this.net.getLocationCount(); index++) {
            this.net.getLocation(this.net.getLocation(index).getKey()).setArrival(null);
            this.net.getLocation(this.net.getLocation(index).getKey()).setVisited(false);
            this.net.getLocation(this.net.getLocation(index).getKey()).setPredecessor(null);

        }

        //Schritt 2: Anfangsort Arrival auf 0 setzen 
        this.net.getLocation(from.getKey()).setArrival(0);
        this.net.getLocation(from.getKey()).setPredecessor(null);

        //Schritt 3: Solange es noch unbesuchte Orte gibt algo machen
        
        while ((this.minNonVisitedLocation() != null) && (this.minNonVisitedLocation().getArrival() != null)) {

            current = this.minNonVisitedLocation();

            current.setVisited(true);
            this.visitLocation(current);

        }
        System.out.println("computed new Dijkstra network!");
    }

    /**
     * gets the location with the minimum arrival of the not visited.
     *
     * @return the location with the minimum arrival of the not visited; null,
     * if no not-visited location is left
     */
    private DijkstraLocation minNonVisitedLocation() {
        DijkstraLocation result = null;

        for (int index = 0; index < this.net.getLocationCount(); index++) {

            DijkstraLocation currentLoc = this.net.getLocation(this.net.getLocation(index).getKey());

            if (!currentLoc.isVisited()) {
                if ((result == null)
                        || (result.getArrival() == null && currentLoc.getArrival() != null)
                        || ((result.getArrival() != null && currentLoc.getArrival() != null) && (currentLoc.getArrival() < result.getArrival()))) {

                    result = currentLoc;

                }
            }

        }
        return result;
    }

    /**
     * visits the given location. Checks all outbound-connections and changes
     * the arrival and predecessor of each to-location if the arrival by this
     * connection is earlier than its former arrival-time.
     *
     * @param dl the location to visit
     */
    private void visitLocation(DijkstraLocation dl) {

        for (int index = 0; index < dl.getOutbound().length; index++) {

            Integer newArrival = dl.getOutbound()[index].arrive(this.v, dl.getArrival());
            Integer oldArrival = net.getLocation(dl.getOutbound()[index].to().getKey()).getArrival();

            if ((oldArrival == null && newArrival != null)
                    || (newArrival < oldArrival)) {
                net.getLocation(dl.getOutbound()[index].to().getKey()).setArrival(newArrival);
                net.getLocation(dl.getOutbound()[index].to().getKey()).setPredecessor(dl.getOutbound()[index]);

            }

        }
    }

    /**
     * calculates the shortest way from one location to another with the given
     * vehicle. The Dijksta-algorithm has to be performed if it wasn't performed
     * yet or a new vehicle or from-location is given. The Routing is
     * constructed by starting at the to-location and adding every predecessor
     * to the Routing till reaching the start.
     *
     * @param v the vehicle to use
     * @param from the location to start from
     * @param to the location to reach
     * @return the routing list with the shortest way; NOWAY if there is no way.
     */
    @Override
    public Routing findBest(Vehicle v, Location from, Location to) {
        Routing result = RoutingEmpty.EMPTY;
        DijkstraLocation counter = this.net.getLocation(to.getKey());

        if (!v.equals(this.v) || from != this.from) {

            this.v = v;
            this.from = from;
            this.performDijkstra(from, v);

        }

        if (this.net.getLocation(to.getKey()).getPredecessor() == null) {
            return RoutingNoWay.NOWAY;
        } else {
            do {

                result = result.addToFront(counter.getPredecessor());
                counter = this.net.getLocation(counter.getPredecessor().from().getKey());

            } while (counter.getPredecessor() != null);

        }

        return result.head().from().equals(from)
                ? result
                : RoutingNoWay.NOWAY;
    }

}
