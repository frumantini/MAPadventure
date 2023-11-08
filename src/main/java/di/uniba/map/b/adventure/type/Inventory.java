/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta l'inventario.
 * @author fra
 */
public class Inventory {

    private List<AdvObject> list = new ArrayList<>();

    /**
     * getter della lista di oggetti.
     * @return list lista di oggetti
     */
    public List<AdvObject> getList() {
        return list;
    }

    /**
     * setter della lista di oggetti.
     * @param list lista di oggetti
     */
    public void setList(List<AdvObject> list) {
        this.list = list;
    }

    /**
     * Metodo che aggiunge un oggetto all'inventario.
     * @param o oggetto da aggiungere
     */
    public void add(AdvObject o) {
        list.add(o);
    }

    /**
     * Metodo che rimuove un oggetto dall'inventario.
     * @param o oggetto da rimuovere
     */
    public void remove(AdvObject o) {
        list.remove(o);
    }
}
