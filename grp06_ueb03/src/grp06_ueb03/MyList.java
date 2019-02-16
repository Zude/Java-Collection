package grp06_ueb03;

/**
 * wir arbeiten hier mit Listen
 * 
 * @author Alexander Löffler / André Kloodt
 */
public class MyList {

    private Element elements;

    /**
     * wir prüfen ob die Liste leer ist
     *
     * @return, wenn = true, dann ist Liste leer
     */
    
    public boolean isEmpty() {
        return elements == null;
    }
    
    /**
     * wir fügen ein Element an die Liste an
     *
     * @param value ist der einzuhängende Wert
     */

    public void appendElement(int value) {
        if (this.isEmpty()) {
            elements = new Element();
            elements.setValue(value);
        } else {
            elements = elements.appendElement(value);
        }
    }
    
    /**
     * wir fügen ein Element in die Liste ein
     *
     * @param value ist der einzufügende Wert
     */

    public void insertElement(int value) {
        if (this.isEmpty()) {
            elements = new Element();
            elements.setValue(value);
        } else {
            elements = elements.insertElement(value);
        }
    }
    
    /**
     * wir löschen ein Element aus der Liste
     *
     * @param value ist der Wert des zu löschenden Elements 
     */

    public void deleteElement(int value) {
        if (!isEmpty()) {
            elements = elements.deleteElement(value);
        }
    }
    
    /******************Hier kommt unser Code*****************/
    
    /**
     * wir zählen die Anzahl der Elemente
     *
     * @return die Anzahl der Elemente
     */
    
    public int size() {

        if (this.isEmpty()) {
            return 0;
        } else {
            return elements.size();
        }
    }

    /**
     * wir summieren die Werte
     *
     * @return wir geben die Summe zurück
     */
    
    public int sum(){
        
        if (this.isEmpty()){
           return 0; 
        }
           return elements.sum();
    }
    
    /**
     * wir prüfen ob die Liste sortiert ist
     *
     * @return, wenn = true, dann ist es sortiert
     */
    
    public boolean isSorted(){
        
        if (this.isEmpty()){
            return true;
        }
            return elements.isSorted();    
    }

    /**
     * wir prüfen ob der Wert des Elementes in der Liste exestiert
     *
     * @param value ist der zu prüfende Wert
     * @return, wenn = true, dann exestiert der Wert
     */
    
    public boolean existsElement(int value){
        
        if (this.isEmpty()){
           return false; 
        }
        return elements.existsElement(value);
    }
    
    /**
     * wir geben alle Werte der Liste aus
     *
     * @return die Werte der Liste
     */
    
    public String showValues(){
        
        if (this.isEmpty()){           
            return "{}";
        }
        return "{" + elements.showValues() + "}";
    }
    
    /**
     * wir geben einen gewünschten Wert aus der Liste an
     *
     * @param index übergibt den Index des Elementes
     * @return den gewünschten Wert aus der Liste
     */
    public int getValueAt(int index) {

        if (this.isEmpty()) {
            return 0;
        }
        return elements.getValueAt(index);
    }
    
    /**
     * wir fügen ein Element an der gewünschetn Stelle ein
     *
     * @param value ist der einzufügende Wert
     * @param index gibt die Stelle des einzufügenden Wertes an
     */
    public void insertElementAt(int value,int index){
        
        if (this.isEmpty()){
            elements = new Element();
            elements.setValue(value);
        }
        else {
            elements = elements.insertElementAt(value, index);
        }
        
    }
    
    /**
     * wir fügen ein Element am Anfang der Liste ein
     *
     * @param value ist der einzufügende Wert
     */
    public void insertElementAtFront(int value){
        
        if (this.isEmpty()){
            elements = new Element();
            elements.setValue(value);
        }
        else {
            elements = elements.insertElementAtFront(value);
        }
        
    }
    
    /**
     * wir fügen ein Element in die Liste ein, wenn der Wert des Elements nicht vorhanden ist
     *
     * @param value ist der einzufügende Wert
     */
    public void insertSortedIfUnique(int value){
        // TODO DONE Bitte insertElement benutzen
        
        if (this.isEmpty()) {
            elements = new Element();
            elements.setValue(value);
        } else if (!this.existsElement(value)) {
            elements = elements.insertElement(value);

        }
        
    }
    
    
}
