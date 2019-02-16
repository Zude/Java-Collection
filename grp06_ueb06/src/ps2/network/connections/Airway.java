/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps2.network.connections;

import ps2.network.Connection;
import ps2.network.Network;
import ps2.network.Vehicle;

/**
 *
 * @author inf102691
 */
public class Airway extends Connection {

    protected int travelTimeTo;
    protected String totalTravelTime;
    protected int travelTimeFrom;

    protected int distance;

    public Airway(int distance, int travelTimeTo, int travelTimeFrom) {
        this.distance = distance;
        this.travelTimeTo = travelTimeTo;
        this.travelTimeFrom = travelTimeFrom;

    }

    /**
     *
     * kopiert die aktuelle Verbindung eines bestimmtes Netzwerkes
     *
     * @param target das Netzwerk der Verbindung
     * @return die Verbindung
     */
    @Override
    public Connection copy(Network target) {
        Airway c = new Airway(this.distance, this.travelTimeTo, this.travelTimeFrom);

        target.addConnection(c, this.from, this.to);

        return c;
    }

    /**
     *
     * berechnet die Zeit, die das Fahrzeug benötigt, um die Verbindung zu
     * passieren.
     *
     * @param v gibt das Fahzeug an
     * @return die Reisezeit
     */
    protected int traveltime(Vehicle v) {

        return (this.distance / v.getMaxSpeed()) + this.travelTimeTo + this.travelTimeFrom;
    }

    @Override
    public Integer arrive(Vehicle v, int start) {

        if (v.likes(this)) {
        	return start + traveltime(v);
        } else {
        	return null;
        }

    }
}
