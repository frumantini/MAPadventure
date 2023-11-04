package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.Room;
import di.uniba.map.b.adventure.type.TimerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Classe che descrive il gioco
 * @author fra
 */
public abstract class GameDescription {

    /**
     * Lista di stanze
     */
    private final List<Room> rooms = new ArrayList<>();

    /**
     * Lista di comandi
     */
    private final List<Command> commands = new ArrayList<>();

    /**
     * Lista di oggetti
     */
    private List<AdvObject> objects = new ArrayList<>();

    /**
     * Lista di oggetti nell'inventario
     */
    private List<AdvObject> inventory = new ArrayList<>();

    private TimerListener timer; //!!!!!!!!!! NEW

    /**
     * Stanza corrente
     */
    private Room currentRoom;

    /**
     * Getter delle stanze
     * @return Lista di stanze
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /** NEW
     * Metodo che filtra le stanze in base ad un predicato
     * @param predicate predicato
     * @return lista di stanze filtrate
     */
    public List<Room> filterRoom(Predicate<Room> predicate)
    {
        List<Room> roomsList = new ArrayList<>();
        for(Room room : rooms)
        {
            if (predicate.test(room))
            {
                roomsList.add(room);
            }
        }
        return roomsList;
    }

    /**
     * Getter della stanza corrente
     * @return Stanza corrente
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Setter della stanza corrente
     * @param currentRoom Stanza corrente
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Getter degli oggetti
     * @return Lista di oggetti
     */
    public List<Command> getCommands() {
        return commands;
    }

    /** NEW
     * Getter della lista di oggetti nel gioco
     * @return lista di oggetti nel gioco
     */
    public List<AdvObject> getObjectsList() {
        return objects;
    }

    /** NEW
     * Metodo che filtra gli oggetti in base ad un predicato
     * @param predicate predicato
     * @return lista di oggetti filtrati
     */
    public List<AdvObject> filterObjects(Predicate<AdvObject> predicate)
    {
        List<AdvObject> objectsList = new ArrayList<>();
        for(AdvObject obj : objects)
        {
            if (predicate.test(obj))
            {
                objectsList.add(obj);
            }
        }
        return objectsList;
    }

    /**
     * Getter degli oggetti nell'inventario
     * @return Lista di oggetti nell'inventario
     */
    public List<AdvObject> getInventory() {
        return inventory;
    }

    /** NEW
     * Setter degli oggetti nell'inventario
     * @param inventoryObjects Lista di oggetti nell'inventario
     */
    public void setInventory(List<AdvObject> inventoryObjects)
    {
        this.inventory = inventoryObjects;
    }

    /** NEW
     * Setter del timer del gioco
     * @param timer timer del gioco
     */
    public void setTimer(TimerListener timer) {
        this.timer = timer;
    }

    /** NEW
     * Getter del timer del gioco
     * @return  timer del gioco
     */
    public TimerListener getTimer() {
        return timer;
    }

    /**
     * Metodo astratto che inizializza il gioco
     * @throws Exception Eccezione lanciata in caso di errore
     */
    public abstract void init() throws Exception;

    /**
     * Metodo astratto che restituisce la mossa successiva
     * @param p Stream di output
     * @return 
     */
    public abstract String nextMove(ParserOutput p);

    /** NEW
     * Metodo astratto che restituisce la descrizione del gioco
     * @return Descrizione del gioco
     */
    public void setEngine(Engine engine) {}

}