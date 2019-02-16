package ps2.routing;

import ps2.network.Connection;
import ps2.network.Location;

/**
 * A Location for Dijkstra-network has to save
 * <ul>
 * <li>wether it was visited</li>
 * <li>when the arrival will be</li>
 * <li>the preceding connection</li>
 * </ul>
 *
 * @author uhl
 */
public class DijkstraLocation extends Location {

    /**
     * true, if the location was visited
     */
    private boolean visited;
    /**
     * the time of arrival
     */
    private Integer arrival;
    /**
     * the preceding connection
     */
    private Connection predecessor;

    /**
     * constructs a DijkstraLocation with the given key and caption.
     * @param key     key of the location
     * @param caption caption of the location
     */
    public DijkstraLocation(String key, String caption) {
        super(key, caption);
    }

    /**
     * gets the arrival of this location
     * @return the arrival of this location
     */
    public Integer getArrival() {
        return arrival;
    }

    /**
     * sets the arrival of this location
     * @param arrival the arrival of this location
     */
    public void setArrival(Integer arrival) {
        this.arrival = arrival;
    }

    /**
     * gets the preceding connection.
     * @return the preceding connection
     */
    public Connection getPredecessor() {
        return predecessor;
    }

    /**
     * sets the preceding connection.
     * @param predecessor the preceding connection
     */
    public void setPredecessor(Connection predecessor) {
        this.predecessor = predecessor;
    }

    /**
     * gets, whether this location is visited
     * @return true, if it is visited, false otherwise
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * sets, whether this location is visited
     * @param visited true, if it is visited, false otherwise
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

}
