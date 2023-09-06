package di.uniba.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pierpaolo
 */
public class AdvObjectContainer extends AdvObject {

    private List<AdvObject> list = new ArrayList<>();

    public AdvObjectContainer(int id, String name, String description) {
        super(id, name, description);
    }

    public List<AdvObject> getList() {
        return list;
    }

    public void setList(List<AdvObject> AdvObjectlist) {
        this.list = AdvObjectlist;
    }

    public void add(AdvObject o) {
        list.add(o);
    }

    public void remove(AdvObject o) {
        list.remove(o);
    }

}
