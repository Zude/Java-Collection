/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps2.network;

import java.util.Arrays;

/**
 *
 * @author inf102691
 */
public class Network {

    protected Location[] locations;
    
    // TODO DONE: 
   

    public Network() {
        this.locations = new Location[0];
       
    }

    /**
     *
     * liefert die Anzahl der enthaltenen Standorte.
     *
     * @return die Anzahl der STandorte
     */
    public int getLocationCount() {
        return locations.length;
    }

    /**
     *
     * prüft nach dem Schlüssel, ob der Standort bereits exestiert.
     *
     * @param key ist der Schlüssel des zu prüfenden Standortes
     *
     * @return true, wenn der Standort vorhanden ist
     */
    private boolean locationExist(String key) {
        boolean marker = false;

        for (Location location : this.locations) {
            if (location.getKey() == key) {
                marker = true;
            }
        }
        return marker;
    }

    /**
     *
     * prüft ob ein Standort bereits exestiert.
     *
     * @param loc ist der zu prüfende Standort
     *
     * @return true, wenn der Standort vorhanden ist
     */
    private boolean locationExist(Location loc) {
        boolean marker = false;

        for (Location location : this.locations) {
            if (location.key.equals(loc.key)) {
                marker = true;
            }
        }
        return marker;
    }

    /**
     *
     * Gibt einen Standort ab einem bestimmten index aus.
     *
     * @param index gibt an welcher Standort ausgegeben werden soll
     *
     * @return den gewünschten Standort
     */
    public Location getLocation(int index) {
        assert (this.locations != null) : "locations leer";
        assert ((index >= 0) && (index < this.locations.length)) : "kein guter index";

        return this.locations[index];

    }

    /**
     *
     * Gibt einen Standort von einem betsimmten Schlüssel aus
     *
     * @param key ist der Schlüssel des Standortes
     *
     * @return den gewünschten Standort
     */
    public Location getLocation(String key) {
        int breakindex = -1;

        for (int index = 0; index < this.locations.length; index++) {
            if (this.locations[index].key.equals(key)) {
                breakindex = index;
                index = this.locations.length;
            }
        }
        if (breakindex >= 0) {

            return this.locations[breakindex];
        } else {
            return null;
        }

    }

    /**
     *
     * Fügt einen bestimmten Standort dem Netzwerk hinzu.
     *
     * @param loc gibt den Standort an
     *
     * @return das NEtzwerk mit dem neuen Standort
     */
    public Network addLocation(Location loc) {
        assert this.getLocation(loc.getKey()) == null : "Es darf noch kein gleicher Ort vorhanden sein";

        if (this.locations.length == 0) {
            this.locations = new Location[1];
            locations[0] = loc;

        } else {
            Location[] myArray = Arrays.copyOf(this.locations, this.locations.length + 1);

            myArray[this.locations.length] = loc;

            this.locations = myArray;
        }
        return this;
    }

    /**
     *
     * entfernt einen bestimmten Standort aus dem Netzwerk.
     *
     * @param loc gibt den Standort an
     *
     * @return das Netzwerk ohne den Standort
     */
    public Network rmvLocation(Location loc) {
        /*
         * Auch hier müsst ihr die entfernten Verbinfungen aus den anderen Orten entfernen.
         * Haben wir das Netzwerk aus der Vorstellung so muss bei entfernen von Hamburg 
         * das Inbound aus Rom und das Outbound aus London entfernen.
         */

        int index1 = 0;
        int index2 = 0;

        if (!this.locationExist(loc)) {
            return this;
        } else {
            
            Location[] newLocations = new Location[this.locations.length - 1];
            for (int index = 0; index < this.locations.length; index++) {
                if (!this.locations[index].equals(loc)) {
                    newLocations[index1] = this.getLocation(index);
                    index1++;
                }
            }
            this.locations = newLocations;

           

            for (int index = 0; index < this.locations.length; index++) {
                this.locations[index].rmvConnectionsWith(loc);
            }

            
            return this;
        }
    }

    /**
     *
     * Fügt dem Netzwerk eine neue Verbindung hinzu und liefert es zurück.
     *
     * @param c gibt die Verbindung an
     * @param from gibt den Startpunkt an
     * @param to gibt das Ziel an
     *
     * @return gibt das NEtzwerk zurück.
     */
    public Network addConnection(Connection c, Location from, Location to) {
        assert this.getLocation(from.getKey()) != null;
        assert this.getLocation(to.getKey()) != null;
        
        c.setFrom(from);
        c.setTo(to);

      

        this.getLocation(c.from().getKey()).addOutbound(c);
        this.getLocation(c.to().getKey()).addInbound(c);

        return this;
    }

    /**
     *
     * fügt dem Netzwerk eine Verbindung, der durch die Schlüssel benannten
     * Orte, hinzu.
     *
     * @param c gibt die Verbindung an
     * @param from_key ist der Schlüssel für den ersten Startort
     * @param to_key ist der Schlüssel für den Zielort
     *
     * @return das Netzwerk mit der hinzugefügten Verbindung
     */
    public Network addConnection(Connection c, String from_key, String to_key) {
        assert this.locationExist(from_key) : "Ort nicht in Locations enthalten";
        assert this.locationExist(to_key) : "Ort nicht in Locations enthalten";

        for (Location location : this.locations) {
            if (location.getKey().equals(from_key)) {
                c.setFrom(location);
            }
            if (location.getKey().equals(to_key)) {
                c.setTo(location);
            }
        }

        

        this.getLocation(c.from().getKey()).addOutbound(c);
        this.getLocation(c.to().getKey()).addInbound(c);

        return this;

    }
}
