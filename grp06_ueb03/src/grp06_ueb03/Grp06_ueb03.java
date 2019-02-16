/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp06_ueb03;


/**
 *
 * @author Alexander Löffler / André Kloodt
 */
public class Grp06_ueb03 {

    /**
     * @param args the command line arguments
     */
    
   private static MyList createTestList(int[] data) {
        MyList list = null;
        if (data != null) {
            list = new MyList();
            for (int i = 0; i < data.length; i++) {
                list.appendElement(data[i]);
            }
        }
        return list;
    }

    private static void testSize() {
        System.out.println("teste size()");
        // leere Liste
        MyList list = new MyList();
        System.out.println("erwarte: 0");
        System.out.println("bekomme: " + list.size() + (0 == list.size() ? "" : "***"));

        // volle Liste
        list = createTestList(new int[] {10, 20, 30, 40, 50});
        System.out.println("erwarte: 5");
        System.out.println("bekomme: " + list.size() + (5 == list.size() ? "" : "***"));
        System.out.println("");
    }

    private static void testSum() {
        System.out.println("teste sum()");

        // leere Liste
        MyList list = new MyList();
        System.out.println("erwarte: 0");
        System.out.println("bekomme: " + list.sum() + (0 == list.sum()? "" : "***"));

        // volle Liste
        list = createTestList(new int[] {10, 20, 30, 40, 50});
        System.out.println("erwarte: 150");
        System.out.println("bekomme: " + list.sum() + (150 == list.sum() ? "" : "***"));
        System.out.println("");
    }

    private static void testIsSorted() {
        System.out.println("teste isSorted()");

        // leere Liste
        MyList list = new MyList();
        System.out.println("erwarte: true");
        System.out.println("bekomme: " + list.isSorted() + (list.isSorted() ? "" : "***"));

        // sortierte Liste -----------------------------------
        list = createTestList(new int[] {10, 20, 30, 40, 50});
        System.out.println("erwarte: true");
        System.out.println("bekomme: " + list.isSorted() + (list.isSorted() ? "" : "***"));
        
        // gleiche Werte sind auch sortiert
        list = createTestList(new int[] {10, 10, 20});
        System.out.println("erwarte: true");
        System.out.println("bekomme: " + list.isSorted() + (list.isSorted() ? "" : "***"));
        
        list = createTestList(new int[] {10, 20, 20});
        System.out.println("erwarte: true");
        System.out.println("bekomme: " + list.isSorted() + (list.isSorted() ? "" : "***"));
        
        // unsortierte Liste ----------------------------------
        // Sortierung an erster Stelle falsch
        list = createTestList(new int[] {3, 1, 2});
        System.out.println("erwarte: false");
        System.out.println("bekomme: " + list.isSorted() + (!list.isSorted() ? "" : "***"));
        
        // Sortierung an letzter Stelle falsch
        list = createTestList(new int[] {1, 3, 2});
        System.out.println("erwarte: false");
        System.out.println("bekomme: " + list.isSorted() + (!list.isSorted() ? "" : "***"));

        // Sortierung an letzter Stelle falsch
        list = createTestList(new int[] {2, 3, 1});
        System.out.println("erwarte: false");
        System.out.println("bekomme: " + list.isSorted() + (!list.isSorted() ? "" : "***"));
        System.out.println("");
    }

    private static void testExistsElement() {
        System.out.println("teste existsElement()");
        MyList list = createTestList(new int[] {10, 20, 30, 40, 50});

        // wird erstes, mittleres und letztes Element gefunden?
        System.out.println("erwarte: true");
        System.out.println("bekomme: " + list.existsElement(10) + (list.existsElement(10) ? "" : "***"));
        System.out.println("erwarte: true");
        System.out.println("bekomme: " + list.existsElement(30) + (list.existsElement(30) ? "" : "***"));        
        System.out.println("erwarte: true");
        System.out.println("bekomme: " + list.existsElement(50) + (list.existsElement(50) ? "" : "***"));

        // nicht enthaltene Elemente sollen nicht gefunden werden
        System.out.println("erwarte: false");
        System.out.println("bekomme: " + list.existsElement(0) + (!list.existsElement(0) ? "" : "***"));
        System.out.println("erwarte: false");
        System.out.println("bekomme: " + list.existsElement(25) + (!list.existsElement(25) ? "" : "***"));
        System.out.println("erwarte: false");
        System.out.println("bekomme: " + list.existsElement(60) + (!list.existsElement(60) ? "" : "***"));

        System.out.println("");
    }

    private static void testShowValues() {
        System.out.println("teste showValues()");

        // leere Liste
        MyList list = new MyList();
        System.out.println("erwarte: {}");
        System.out.println("bekomme: " + list.showValues() + ("{}".equals(list.showValues()) ? "" : "***"));

        // volle Liste
        list = createTestList(new int[] {10, 20, 30, 40, 50});
        System.out.println("erwarte: {10 20 30 40 50}");
        System.out.println("bekomme: " + list.showValues() + ("{10 20 30 40 50}".equals(list.showValues()) ? "" : "***"));
        System.out.println("");
    }

    private static void testGetValueAt() {
        System.out.println("teste getValueAt()");
        // am Anfang
        MyList list = createTestList(new int[] {10, 20, 30, 40, 50});
        System.out.println("erwarte: 10");
        System.out.println("bekomme: " + list.getValueAt(0) + (10 == list.getValueAt(0) ? "" : "***"));

        // in der Mitte
        list = createTestList(new int[] {10, 20, 30, 40, 50});
        System.out.println("erwarte: 30");
        System.out.println("bekomme: " + list.getValueAt(2) + (30 == list.getValueAt(2) ? "" : "***"));

        // am Ende
        list = createTestList(new int[] {10, 20, 30, 40, 50});
        System.out.println("erwarte: 50");
        System.out.println("bekomme: " + list.getValueAt(4) + (50 == list.getValueAt(4) ? "" : "***"));

        System.out.println("");
    }

    private static void testInsertValueAt() {
        System.out.println("teste insertValueAt()");

        // am Ende einfügen
        MyList list = createTestList(new int[] {10, 20, 30});
        list.insertElementAt(99, 3);
        System.out.println("erwarte: {10 20 30 99}");
        System.out.println("bekomme: " + list.showValues() + ("{10 20 30 99}".equals(list.showValues()) ? "" : "***"));

        // in der Mitte einfügen
        list = createTestList(new int[] {10, 20, 30});
        list.insertElementAt(99, 2);
        System.out.println("erwarte: {10 20 99 30}");
        System.out.println("bekomme: " + list.showValues() + ("{10 20 99 30}".equals(list.showValues()) ? "" : "***"));

        // am Anfang einfügen
        list = createTestList(new int[] {10, 20, 30});
        list.insertElementAt(99, 0);
        System.out.println("erwarte: {99 10 20 30}");
        System.out.println("bekomme: " + list.showValues() + ("{99 10 20 30}".equals(list.showValues()) ? "" : "***"));

        System.out.println("");
    }

    private static void testInsertValueAtFront() {
        System.out.println("teste insertValueAtFront()");

        // zu Beginn einer leeren Liste anfügen
        MyList list = new MyList();
        list.insertElementAtFront(99);
        System.out.println("erwarte: {99}");
        System.out.println("bekomme: " + list.showValues() + ("{99}".equals(list.showValues()) ? "" : "***"));

        // am Anfang einer bestehenden Liste einfügen
        list = createTestList(new int[] {10, 20});
        list.insertElementAtFront(99);
        System.out.println("erwarte: {99 10 20}");
        System.out.println("bekomme: " + list.showValues() + ("{99 10 20}".equals(list.showValues()) ? "" : "***"));
        System.out.println("Nun ist die Liste nicht mehr sortiert: " + !list.isSorted() + (list.isSorted() ? "***" : ""));
        System.out.println("");
    }

    private static void testInsertSortedIfUnique() {
        System.out.println("teste insertValueIfUnique()");

        // in leere Liste muss auf jeden Fall eingefügt werden
        MyList list = new MyList();
        list.insertSortedIfUnique(42);
        System.out.println("erwarte: {42}");
        System.out.println("bekomme: " + list.showValues() + ("{42}".equals(list.showValues()) ? "" : "***"));

        // am Anfang enthaltenes Element darf nicht eingefügt werden
        list = createTestList(new int[] {10, 20, 30, 40, 50});
        list.insertSortedIfUnique(10);
        System.out.println("erwarte: {10 20 30 40 50}");
        System.out.println("bekomme: " + list.showValues() + ("{10 20 30 40 50}".equals(list.showValues()) ? "" : "***"));

        // in Mitte enthaltenes Element darf nicht eingefügt werden
        list = createTestList(new int[] {10, 20, 30, 40, 50});
        list.insertSortedIfUnique(30);
        System.out.println("erwarte: {10 20 30 40 50}");
        System.out.println("bekomme: " + list.showValues() + ("{10 20 30 40 50}".equals(list.showValues()) ? "" : "***"));

        // am Ende enthaltenes Element darf nicht eingefügt werden
        list = createTestList(new int[] {10, 20, 30, 40, 50});
        list.insertSortedIfUnique(50);
        System.out.println("erwarte: {10 20 30 40 50}");
        System.out.println("bekomme: " + list.showValues() + ("{10 20 30 40 50}".equals(list.showValues()) ? "" : "***"));

        // kleineres Element muss am Anfang eingefügt werden
        list = createTestList(new int[] {10, 20, 30, 40, 50});
        list.insertSortedIfUnique(8);
        System.out.println("erwarte: {8 10 20 30 40 50}");
        System.out.println("bekomme: " + list.showValues() + ("{8 10 20 30 40 50}".equals(list.showValues()) ? "" : "***"));

        // mittleres Element muss in der Mitte eingefügt werden
        list = createTestList(new int[] {10, 20, 30, 40, 50});
        list.insertSortedIfUnique(25);
        System.out.println("erwarte: {10 20 25 30 40 50}");
        System.out.println("bekomme: " + list.showValues() + ("{10 20 25 30 40 50}".equals(list.showValues()) ? "" : "***"));

        // größeres Element muss angehängt werden
        list = createTestList(new int[] {10, 20, 30, 40, 50});
        list.insertSortedIfUnique(55);
        System.out.println("erwarte: {10 20 30 40 50 55}");
        System.out.println("bekomme: " + list.showValues() + ("{10 20 30 40 50 55}".equals(list.showValues()) ? "" : "***"));

        System.out.println("");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testSize();
        testSum();
        testIsSorted();
        testExistsElement();
        testShowValues();
        testGetValueAt();
        testInsertValueAt();
        testInsertValueAtFront();
        testInsertSortedIfUnique();
        
    }
    
}