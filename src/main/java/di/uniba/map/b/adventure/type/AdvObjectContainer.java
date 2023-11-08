package di.uniba.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fra
 */
public class AdvObjectContainer extends AdvObject {

    private List<AdvObject> list = new ArrayList<>();

    public AdvObjectContainer(int id, String name, String description) {
        super(id, name, description);
    }

    /**
     * getter della lista di oggetti contenuti
     * @return lista di oggetti contenuti
     */
    public List<AdvObject> getList() {
        return list;
    }

    /**
     * setter della lista di oggetti contenuti
     * @param AdvObjectlist lista di oggetti contenuti
     */
    public void setList(List<AdvObject> AdvObjectlist) {
        this.list = AdvObjectlist;
    }

    /**
     * aggiunge un oggetto alla lista di oggetti contenuti
     * @param o oggetto da aggiungere
     */
    public void add(AdvObject o) {
        list.add(o);
    }

    /**
     * rimuove un oggetto dalla lista di oggetti contenuti
     * @param o oggetto da rimuovere
     */
    public void remove(AdvObject o) {
        list.remove(o);
    }

}
