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

    private boolean invisible = false;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPickupable() {
        return pickupable;
    }

    public void setPickupable(boolean pickupable) {
        this.pickupable = pickupable;
        /*if (pickupable) {
            this.usable = true;
        }*/
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public boolean isPushable() {
        return pushable;
    }

    public void setPushable(boolean pushable) {
        this.pushable = pushable;
    }

    public boolean isMoveable() {
        return moveable;
    }

    public void setMoveable(boolean moveable) {
        this.moveable = moveable;
    }

    public boolean isInspectable() {
        return inspectable;
    }

    public void setInspectable(boolean inspectable) {
        this.inspectable = inspectable;
    }

    public boolean isUnlockable() {
        return unlockable;
    }

    public void setUnlockable(boolean unlockable) {
        this.unlockable = unlockable;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
        if (invisible) {
            this.pickupable = false;
        } else {
            this.pickupable = true;
        }
    }*/

    public boolean isInspected() {
        return inspected;
    }

    public void setInspected(boolean inspected) {
        this.inspected = inspected;
        /*if (inspected) {
            this.usable = true;
        }*/
    }

    public boolean isPushed() {
        return pushed;
    }

    public void setPushed(boolean push) {
        this.pushed = push;
    }

    public Set<String> getAlias() {
        return alias;
    }

    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

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
