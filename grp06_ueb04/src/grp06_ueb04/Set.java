package grp06_ueb04;

/**
 *
 * @author Andre und Alex
 */
public class Set {

    private Element ourSet;

    /**
     * Wir prüfen, ob die Liste dieMenge leer ist
     *
     * @return wenn leer = true
     */
    public boolean isEmpty() {
        return ourSet == null;
    }

    /**
     * Wir fügen ein Element in die Liste dieMenge ein
     *
     * @param value ist der Wert, den das neue Element erhalten soll
     */
    public void addElement(int value) {
        if (this.isEmpty()) {
            this.ourSet = new Element();
            this.ourSet.setValue(value);
        } else {
            if (!(this.ourSet.existsElement(value))) {
                this.ourSet = this.ourSet.insertElement(value);
            }
        }
    }

    /**
     * Wir überprüfen, ob es bereits den Wert den wir übergeben in der Liste
     * gibt
     *
     * @param value ist der Wert, nachdem gesucht wird
     * @return wenn es bereits den Wert gibt = true
     */
    public boolean existsElement(int value) {
        if (this.isEmpty()) {
            return false;
        } else {
            return ourSet.existsElement(value);
        }
    }

    /**
     * Wir löschen das Element, welches den übergebenen Wert enthält
     *
     * @param value ist der Wert des zu löschenden Elements
     */
    public void deleteElement(int value) {
        if (!this.isEmpty()) {

            if (ourSet.existsElement(value)) {
                ourSet = ourSet.deleteElement(value);
            }
        }
    }

    /**
     * Wir schreiben die Werte der Menge in einem String
     *
     * @return wir geben den String zurück
     */
    public String showValues() {

        if (this.isEmpty()) {
            return "{}";
        }
        return "{" + ourSet.showValues() + "}";
    }

    /**
     * Wir bilden die Vereinigung zweier Mengen
     *
     * @param other ist die zweite Menge vom Typ Set
     * @return wir geben die Vereinigungsmenge zurück
     */
    public Set union(Set other) {
        Set vSet = new Set();

        // TODO: Wie wäre es mit addElementList?
        if (this.isEmpty()) {
            return other;
        }
        if (other.isEmpty()) {
            return this;
        }
        vSet.addElementList(this.ourSet);
        vSet.addElementList(other.ourSet);
        return vSet;
    }

    /**
     * Wir bilden die Schnittmenge zweier Mengen
     *
     * @param other ist die zweite Menge vom Typ Set
     * @return wir geben die Schnittmenge zurück
     */
    public Set intersection(Set other) {
        Set cutSet = new Set();

        if ((this.isEmpty()) || (other.isEmpty())) {
            cutSet.ourSet = null;
        } else {
            for (int index = 0; index < ourSet.size(); index++) {
                if (other.existsElement(ourSet.getValueAt(index))) {
                    cutSet.addElement(ourSet.getValueAt(index));
                }
            }
        }
        return cutSet;
    }

    /**
     * Wir bilden dieDifferenz zweier Mengen
     *
     * @param other ist die zweite Menge vom Typ Set
     * @return wir geben die Differenzmenge zurück
     */
    public Set diff(Set other) {
        Set difSet = new Set();

        if ((this.isEmpty()) || (other.isEmpty())) {
            return this;
        }

        for (int index = 0; index < ourSet.size(); index++) {
            if (!(other.ourSet.existsElement(ourSet.getValueAt(index)))) {
                difSet.addElement(ourSet.getValueAt(index));
            }
        }
        return difSet;
    }

    /**
     * Wir prüfen, ob die Mengen genau gleich sind
     *
     * @param other ist die zweite Menge vom Typ Set
     * @return wenn sie gleich sind = true
     */
    public boolean isSame(Set other) {

        return (this.isEmpty() && other.isEmpty()) || (this.ourSet.isSame(other.ourSet));

    }

    /**
     * Wir prüfen, ob die zweite Menge eine echte Teilmenge der ersten ist
     *
     * @param other ist die zweite Menge vom Typ Set
     * @return wenn sie eine Echte Teilmenge ist = true
     */
    public boolean isProperSubSet(Set other) {


        // TODO: Arbeiten mit intersect()
        // 1) Rechte Seite leer
        // 2) Beide sind gleich
      
        if (this.isEmpty()){
            return false;
        }

        else {
        return ((other.isSame(this.intersection(other))) && (!(other.isSame(this))));       
        }
    }

    /**
     * Wir fügen alle noch nicht erhaltenen Werte in unsere Menge ein
     *
     * @param list ist die Übergebene Liste mit den Werten die eingefügt werden
     */
    private void addElementList(Element list) {

        int endWall = list.size();

        for (int index = 0; index < endWall; index++) {
            if (!(this.existsElement(list.getValueAt(index)))) {
                this.addElement(list.getValueAt(index));
            }
        }
    }

}
