/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps2.network.vehicles;

import ps2.network.Vehicle;
import ps2.network.connections.Airway;

/**
 *
 * @author inf102691
 */
public class Plane extends Vehicle {

    protected final int speed;

    public Plane(int speedValue) {
        this.speed = speedValue;
    }

    @Override
    public int getMaxSpeed() {
        return this.speed;
    }

    /**
     *
     * gibt an ob der Weg eine Luftstraße ist
     *
     * @param myAirway gibt den zu prüfenden Weg an
     * @return true, wenn es eine Luftstraße ist.
     */
    @Override
    public boolean likes(Airway myAirway) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Plane)) {
            return false;
        } else {
            Plane myPlane = (Plane) o;
            return myPlane.getMaxSpeed() == this.getMaxSpeed();

        }

    }

}
