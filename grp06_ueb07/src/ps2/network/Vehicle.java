/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps2.network;

import ps2.network.connections.*;

/**
 *
 * @author inf102691
 */
public abstract class Vehicle {

    /**
     *
     * gibt die Maximalgeschwindigkeit zurück
     *
     * @return die Maximalgeschwindigkeit
     */
    public int getMaxSpeed() {
        return -1;
    }

    /**
     *
     * gibt an ob der Weg eine Straße ist
     *
     * @param myStreet gibt den zu prüfenden Weg an
     * @return true, wenn es eine Straße ist.
     */
    public boolean likes(Street myStreet) {

        return false;
    }

    /**
     *
     * gibt an ob der Weg eine Wasserstraße ist
     *
     * @param myWaterWay gibt den zu prüfenden Weg an
     * @return true, wenn es eine Wasserstraße ist.
     */
     public  boolean likes(Waterway myWaterWay) {

        return false;
    }

    /**
     *
     * gibt an ob der Weg eine Luftstraße ist
     *
     * @param myAirway gibt den zu prüfenden Weg an
     * @return true, wenn es eine Luftstraße ist.
     */
    public boolean likes(Airway myAirway) {

        return false;
    }

}
