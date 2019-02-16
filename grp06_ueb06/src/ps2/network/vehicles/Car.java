/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps2.network.vehicles;

import ps2.network.Vehicle;
import ps2.network.connections.Street;

/**
 *
 * @author inf102691
 */
public class Car extends Vehicle {

    protected final int speed;

    public Car(int speedValue) {
        this.speed = speedValue;
    }

    @Override
    public int getMaxSpeed() {
        return this.speed;
    }

    @Override
    public boolean likes(Street myStreet) {
        return true;
    }

    //TODOequals machen DONE
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Car)) {
            return false;
        } else {
            Car myCar = (Car) o;
            return myCar.getMaxSpeed() == this.getMaxSpeed();

        }
    }
}
