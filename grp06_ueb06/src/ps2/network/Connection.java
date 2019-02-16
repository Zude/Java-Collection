/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps2.network;

/**
 *
 * @author inf102691
 */
public class Connection {

    protected Location from;
    protected Location to;

    public Connection() {

    }

    void setFrom(Location location) {
        assert this.from == null : "Es darf noch kein Ort vorhanden sein";
        assert location != null : "Eine Locatio muss gesetzt werden";
        this.from = location;
    }

    void setTo(Location location) {
        assert this.to == null : "Es darf noch kein Ort vorhanden sein";
        assert location != null : "Eine Locatio muss gesetzt werden";
        this.to = location;
    }

    /**
     *
     * gibt den Startpunkt aus.
     *
     * @return den Startpunkt
     */
    public Location from() {
        return this.from;
    }

    /**
     *
     * gibt den Zielpunkt aus.
     *
     * @return den Zielpunkt
     */
    public Location to() {
        return this.to;
    }

    /**
     *
     * kopiert die aktuelle Verbindung eines bestimmtes Netzwerkes
     *
     * @param trgt das Netzwerk der Verbindung
     * @return die Verbindung
     */
    public Connection copy(Network trgt) {
        return null;
    }

    /**
     *
     * fügt die übergebene Verbindung mit den Orten der aktuellen Verbindung dem
     * Netzwerk zu
     *
     * @param c gibt die Verbindung an
     * @param trgt gibt das Netzwerk für die neue Verbindung an
     * @return die neue Verbindung im Netzwerk
     */
    public Connection copy(Connection c, Network trgt) {
        c.setFrom(this.from());
        c.setTo(this.to());

        trgt.addConnection(c, c.from(), c.to());

        return c;
    }

    /**
     *
     * liefert den Ankunftszeitpunkt, wenn das gegebene Fahrzeug zum
     * Startzeitpunkt die Verbindung zu passieren beginnt.
     *
     * @param v gibt das Fahrzeug an
     * @param start gibt den Startzeitpunkt an
     * @return der Ankunftszeitpunkt
     */
    public Integer arrive(Vehicle v, int start) {
        return null;
    }

    /**
     *
     * Verwandelt die aktuelle Verbindung in einen String und gibt sie aus
     *
     * @return die aktuelle Verbindung
     */
    @Override
    public String toString() {
        String Output;

        assert this.from != null : "from ist Null";
        assert this.to != null : "to ist Null";

        Output = ("<" + this.from.toString() + "->" + this.to.toString() + ">");

        return Output;
    }
}
