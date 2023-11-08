package di.uniba.map.b.adventure.db;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta lo stato di gioco di un utente.
 * Contiene le informazioni necessarie per ripristinare lo stato di gioco di un utente.
 * Implementa l'interfaccia Serializable per poter essere salvata su file.
 * @author fra
 */
public class GameStatus implements Serializable {

    private String username;
    private Integer lastRoomId;
    private List<Integer> inventory;
    private LocalDateTime time;
    private Integer progress;
    private Integer delay;

    /**
     * Costruttore della classe GameStatus.
     * @param username nome utente.
     * @param lastRoomId ultima stanza in cui si trovava l'utente.
     * @param inventoryIds lista di oggetti in inventario.
     * @param time tempo di salvataggio.
     */
    public GameStatus(String username, Integer lastRoomId, List<Integer> inventoryIds, LocalDateTime time, Integer progress, Integer delay) {
        this.username = username;
        this.lastRoomId = lastRoomId;
        this.inventory = inventoryIds;
        this.time = time;
        this.progress = progress;
        this.delay = delay;
    }

    /**
     * getter per username.
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * getter per ultima stanza in cui si trovava l'utente.
     * @return lastRoomId.
     */
    public Integer getLastRoomId() {
        return lastRoomId;
    }

    /**
     * getter per il progresso della progress bar.
     * @return progress.
     */
    public Integer getProgress() {
        return progress;
    }

    /**
     * setter per il progresso della progress bar.
     * @param progress progresso della progress bar.
     */
    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    /**
     * getter per il tempo di attesa tra un incremento e l'altro della progress bar.
     * @return delay
     */
    public Integer getDelay() {
        return delay;
    }

    /**
     * setter per il tempo di attesa tra un incremento e l'altro della progress bar.
     * @param delay tempo di attesa tra un incremento e l'altro della progress bar
     */
    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    /**
     * getter per la lista di oggetti in inventario.
     * @return lista degli id degli oggetti in inventario.
     */
    public List<Integer> getInventoryIds() {
        return inventory;
    }

    /**
     * getter per la lista di oggetti in inventario sotto forma di stringa.
     * @return stringa degli id degli oggetti in inventario.
     */
    public String getInventoryIdsAsString() {
        String string = "";
        for(Integer id: inventory )
        {
            string = string + id.toString() + ",";
        }
        return string;
    }

    /**
     * setter per username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * setter per ultima stanza in cui si trovava l'utente.
     */
    public void setLastRoomId(Integer lastRoom) {
        this.lastRoomId = lastRoom;
    }

    /**
     * setter per la lista di oggetti in inventario.
     */
    public void setInventoryIds(ArrayList<Integer> inventory) {
        this.inventory = inventory;
    }

    /**
     * getter del tempo di salvataggio.
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * setter del tempo.
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
