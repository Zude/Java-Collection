/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps2.network.vehicles;

import ps2.network.Vehicle;
import ps2.network.connections.Waterway;

/**
 *
 * @author inf102691
 */
public class Boat extends Vehicle {

    protected final int speed;

    public Boat(int speedValue) {
        this.speed = speedValue;
    }

    @Override
    public int getMaxSpeed() {
        return this.speed;
    }

    /**
     *
     * gibt an ob der Weg eine Wasserstraße ist
     *
     * @param myWaterway gibt den zu prüfenden Weg an
     * @return true, wenn es eine Wasserstraße ist.
     */
    @Override
    public boolean likes(Waterway myWaterway) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Boat)) {
            return false;
        } else {
            Boat myBoat = (Boat) o;
            return myBoat.getMaxSpeed() == this.getMaxSpeed();

        }

    }

}
