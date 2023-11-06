package di.uniba.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * Classe che rappresenta una stanza.
 * @author fra
 */
public class Room {

    private final int id;

    private String name;

    private String description;

    private String look;

    private boolean visible = true;

    private boolean locked = false;

    private boolean dark = false;
    
    private boolean visited = false;

    private Room south = null;

    private Room north = null;

    private Room east = null;

    private Room west = null;
    /**
     * background della stanza.
     */
    private Image backgroundImage;

    /**
     * percorso del background della stanza.
     */
    private String backgroundImagePath;

    /**
     * Lista di oggetti contenuti nella stanza.
     */
    private final List<AdvObject> objects=new ArrayList<>();

    public Room(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        setBackgroundImage();
    }

    public int getId() {
        return id;
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isDark(){
        return dark;
    }

    public void setDark(boolean dark){
        this.dark = dark;
    }

    /**
     * metodo che restituisce se la stanza è stata visitata o meno.
     * @return visited
     */
    public boolean isVisited(){
        return this.visited;
    }
    /**
     * metodo che setta se la stanza è stata visitata o meno.
     * @param visited
     */
    public void setVisited(final boolean visited){
        this.visited = visited;
    }

    public Room getSouth() {
        return south;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public Room getNorth() {
        return north;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public Room getEast() {
        return east;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public Room getWest() {
        return west;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public List<AdvObject> getObjects() {
        return objects;
    }

    /**
     * metodo che aggiunge un oggetto alla stanza.
     * @param object
     */
    public void addObject(final AdvObjectContainer object){
        this.objects.add(object);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
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
        final Room other = (Room) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public String getLook() {
        return look;
    }

    public void setLook(String look) {
        this.look = look;
    }

    private void setBackgroundImage() {
        ImageIcon backgroundImageIcon = new ImageIcon("resources/"+this.id+".png");
        backgroundImagePath= "resources/"+this.id+".png";
        Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(backgroundImageIcon.getIconWidth(), backgroundImageIcon.getIconHeight(), Image.SCALE_SMOOTH);
        this.backgroundImage = backgroundImage;
    }

    public Image getBackgroundImage(){
        return this.backgroundImage;
    }

    public String getBackgroundImagePath(){
        return this.backgroundImagePath;
    }
}
