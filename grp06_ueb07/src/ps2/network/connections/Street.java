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
public class Street extends Connection {

    protected int distance;
    protected int maxSpeed;

    public Street(int distance, int maxSpeed) {
        this.distance = distance;
        this.maxSpeed = maxSpeed;
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
        Street c = new Street(this.distance, this.maxSpeed);

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
        if (v.getMaxSpeed() < this.maxSpeed) {
            return this.distance / v.getMaxSpeed();
        } else {
            return this.distance / this.maxSpeed;
        }
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
