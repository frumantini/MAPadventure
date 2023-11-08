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

    /**
     * getter dell'id della stanza.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * getter del nome della stanza.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setter del nome della stanza.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter della descrizione della stanza.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter della descrizione della stanza.
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * metodo che restituisce se la stanza è visibile o meno.
     * @return visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * metodo che setta se la stanza è visibile o meno.
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * metodo che restituisce se la stanza è bloccata o meno.
     * @return locked
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * metodo che setta se la stanza è bloccata o meno.
     * @param locked
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * metodo che restituisce se la stanza è buia o meno.
     * @return dark
     */
    public boolean isDark(){
        return dark;
    }

    /**
     * metodo che setta se la stanza è buia o meno.
     * @param dark
     */
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

    /**
     * getter della stanza a sud.
     * @return south
     */
    public Room getSouth() {
        return south;
    }

    /**
     * setter della stanza a sud.
     * @param south
     */
    public void setSouth(Room south) {
        this.south = south;
    }

    /**
     * getter della stanza a nord.
     * @return north
     */
    public Room getNorth() {
        return north;
    }

    /**
     * setter della stanza a nord.
     * @param north
     */
    public void setNorth(Room north) {
        this.north = north;
    }

    /**
     * getter della stanza ad est.
     * @return east
     */
    public Room getEast() {
        return east;
    }

    /**
     * setter della stanza ad est.
     * @param east
     */
    public void setEast(Room east) {
        this.east = east;
    }

    /**
     * getter della stanza ad ovest.
     * @return west
     */
    public Room getWest() {
        return west;
    }

    /**
     * setter della stanza ad ovest.
     * @param west
     */
    public void setWest(Room west) {
        this.west = west;
    }

    /**
     * getter della lista di oggetti contenuti nella stanza.
     * @return objects
     */
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
