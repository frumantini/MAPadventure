/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure.type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author fra
 */
public class AdvObject {

    private final int id;

    private String name;

    private String description;

    private Set<String> alias;

    private boolean pickupable = true;

    private boolean usable = true;

    private boolean moveable = false;

    private boolean inspectable = false;

    private boolean pushable = false;

    private boolean unlockable = false;

    private boolean inspected = false;

    private boolean pushed = false;

    private String password = null;

    public AdvObject(int id) {
        this.id = id;
    }

    public AdvObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public AdvObject(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * getter del nome dell'oggetto
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * setter del nome dell'oggetto
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter della descrizione dell'oggetto
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter della descrizione dell'oggetto
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * getter della possibilità di prendere l'oggetto
     * @return true se l'oggetto è prendibile, false altrimenti
     */
    public boolean isPickupable() {
        return pickupable;
    }

    /**
     * setter della possibilità di prendere l'oggetto
     * @param pickupable
     */
    public void setPickupable(boolean pickupable) {
        this.pickupable = pickupable;
    }

    /**
     * getter della possibilità di usare l'oggetto
     * @return true se l'oggetto è usabile, false altrimenti
     */
    public boolean isUsable() {
        return usable;
    }

    /**
     * setter della possibilità di usare l'oggetto
     * @param usable
     */
    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    /**
     * getter della possibilità di spingere l'oggetto
     * @return true se l'oggetto è spingibile, false altrimenti
     */
    public boolean isPushable() {
        return pushable;
    }

    /**
     * setter della possibilità di spingere l'oggetto
     * @param pushable
     */
    public void setPushable(boolean pushable) {
        this.pushable = pushable;
    }

    /**
     * getter della possibilità di spostare l'oggetto
     * @return true se l'oggetto è spostabile, false altrimenti
     */
    public boolean isMoveable() {
        return moveable;
    }

    /**
     * setter della possibilità di spostare l'oggetto
     * @param moveable
     */
    public void setMoveable(boolean moveable) {
        this.moveable = moveable;
    }

    /**
     * getter della possibilità di ispezionare l'oggetto
     * @return true se l'oggetto è ispezionabile, false altrimenti
     */
    public boolean isInspectable() {
        return inspectable;
    }

    /**
     * setter della possibilità di ispezionare l'oggetto
     * @param inspectable
     */
    public void setInspectable(boolean inspectable) {
        this.inspectable = inspectable;
    }

    /**
     * getter della possibilità di sbloccare l'oggetto
     * @return true se l'oggetto è sbloccabile, false altrimenti
     */
    public boolean isUnlockable() {
        return unlockable;
    }

    /**
     * setter della possibilità di sbloccare l'oggetto
     * @param unlockable
     */
    public void setUnlockable(boolean unlockable) {
        this.unlockable = unlockable;
    }

    /**
     * getter della password dell'oggetto
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * setter della password dell'oggetto
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getter che comunica se l'oggetto è stato ispezionato
     * @return true se l'oggetto è stato ispezionato, false altrimenti
     */
    public boolean isInspected() {
        return inspected;
    }

    /**
     * setter che comunica se l'oggetto è stato ispezionato
     * @param inspected
     */
    public void setInspected(boolean inspected) {
        this.inspected = inspected;
    }

    /**
     * getter che comunica se l'oggetto è stato spinto
     * @return true se l'oggetto è stato spinto, false altrimenti
     */
    public boolean isPushed() {
        return pushed;
    }

    /**
     * setter che comunica se l'oggetto è stato spinto
     * @param push
     */
    public void setPushed(boolean push) {
        this.pushed = push;
    }

    /**
     * getter degli alias dell'oggetto
     * @return
     */
    public Set<String> getAlias() {
        return alias;
    }

    /**
     * setter degli alias dell'oggetto
     * @param alias
     */
    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    /**
     * setter degli alias dell'oggetto
     * @param alias
     */
    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    /**
     * getter dell'id dell'oggetto
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo che converte in hascode l'id dell'oggetto.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    /**
     * Metodo che confronta l'id dell'oggetto.
     * @param obj Oggetto da confrontare.
     * @return true se l'id è uguale, false altrimenti.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AdvObject other = (AdvObject) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
