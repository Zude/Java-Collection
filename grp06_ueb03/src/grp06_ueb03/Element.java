/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp06_ueb03;

/**
 * wir arbiten mit den einzelnen Elementen einer Liste
 * 
 * @author Alexander Löffler / André Kloodt FERTIG
 */
public class Element {

    private int value;
    private Element next;

    
    /**
     * wir setzen einen Wert
     *
     * @param value ist der einzusetzende Wert
     */
    
    public void setValue(int value) { 
        this.value = value;
    }
    
    /**
     * wir geben einen Wert aus
     *
     * @return den zu übergebenen Wert
     */
    
    public int getValue(){
        return value;
    } 
    
    /**
     * wir setzen das Next der Liste
     *
     * @param next ist der neue Wert
     */
    
    public void setNext(Element next) {
        this.next = next;
    }
    
   
    
    
    /**
     * wir fügen ein Element sortiert in eine Liste ein
     *
     * @param value ist der einzufügende Wert für das Element
     * @return wir übergeben die Liste mit dem neuen Element
     * 
     * @author Häuslein
     */

    public Element insertElement(int value) {
        if (this.value >= value) {
            Element newElement = new Element();
            newElement.setValue(value);
            newElement.setNext(this);
            return newElement;
        } else if (this.next == null) {
            Element newElement = new Element();
            newElement.setValue(value);
            this.next = newElement;
            return this;
        } else {
            this.next = this.next.insertElement(value);
            return this;
        }
    }
    
    /**
     * wir fügen ein neues Element an die Liste 
     *
     * @param value ist der Wert für das anzufügende Element
     * @return wir übergeben die Liste mit dem angefügten Element
     */

    public Element appendElement(int value) {
        if (this.next == null) {
            Element newElement = new Element();
            newElement.setValue(value);
            this.next = newElement;
        } else {
            this.next = this.next.appendElement(value);
        }
        return this;
    }
    
    /**
     * wir löschen ein Element aus der Liste
     *
     * @param value ist der Wert für das zu löschende Element
     * @return die Liste ohne das Element
     */

    public Element deleteElement(int value) {
        if (this.value == value) {
            return this.next;
        } else {
            if (this.next != null) {
                this.next = this.next.deleteElement(value);
            }
            return this;
        }
    }
 
    /******************Hier kommt unser Code*****************/
    
    /**
     * wir zählen die Anzahl der Elemente
     *
     * @return die Anzahl der Elemente
     */
    
    public int size(){
                
        if (this.next == null){
            return 1;
        }
        else {
            return 1 + this.next.size();
        }   
    }
    
    /**
     * wir summieren die Werte
     *
     * @return wir geben die Summe zurück
     */
    
    public int sum(){
        
        if (this.next == null){
            return value;
        }
        else {
            return this.value + this.next.sum();
        }
    }
    
    /**
     * wir prüfen ob die Liste sortiert ist
     *
     * @return, wenn = true, dann ist es sortiert
     */
    
    public boolean isSorted(){
        return this.next == null 
                || (this.value <= this.next.value 
                && this.next.isSorted());
    }
    
    /**
     * wir prüfen ob der Wert des Elementes in der Liste exestiert
     *
     * @param value ist der zu prüfende Wert
     * @return, wenn = true, dann exestiert der Wert
     */
    
    public boolean existsElement(int value){
        if ((this.next == null) && (this.value != value)){
            return false;
        }
        if ((this.next == null) && (this.value == value)){
        return true;
        }        
        return (this.value == value) || (this.next.existsElement(value));
    }
    
    /**
     * wir geben alle Werte der Liste aus
     *
     * @return die Werte der Liste
     */
    
    public String showValues(){
        
        if (this.next == null){
            return String.valueOf(this.value);
        }
        else {
            return String.valueOf(this.value)+ " " + this.next.showValues();
        }
    }
    
    /**
     * wir geben eine bestimmte Zahl an einer gewünschten indexstelle aus
     *
     * @param index gibt die Stelle des Elementes an
     * @return den Wert des Elementes, oder Integer.MAX_VALUE wenn nicht gefunden
     */
    
    public int getValueAt(int index){
        int valueAt = Integer.MAX_VALUE;
        if (index >= 0 && index < this.size()){
            if (index == 0){
                valueAt = this.value;                    
            }
            if (index != 0){
                valueAt = this.next.getValueAt(index-1);            
            }
        } 
        return valueAt;
    }
    
    /**
     * wir fügen ein Element an der gewünschten Stelle ein
     *
     * @param value ist der einzufügende Wert
     * @param index gibt die Stelle des einzufügenden Wertes an
     * @return die Liste mit dem neuen Element an
     */
    
    public Element insertElementAt(int value, int index) {
        
        // TODO DONE bitte nicht jedesmal prüfen ob der Index gültig ist

        if (index >= 0) {
            if (index == 0) {                        // Abruchbedingung
                return insertElementAtFront(value);
            } else {
                if (index == 1 && this.next == null) {
                    appendElement(value);
                } else {
                    this.next = this.next.insertElementAt(value, index - 1);
                }
                return this;
            }
        } 
        return this;
    }
    
    /**
     * wir fügen ein Element vorne ein
     *
     * @param value ist der einzufügende Wert
     * @return die Liste mit dem neuen Element an
     */
    
    public Element insertElementAtFront(int value){
        
            Element newElement = new Element();
            newElement.setValue(value);
            newElement.setNext(this);
            return newElement;
            
    }
    
}
    
