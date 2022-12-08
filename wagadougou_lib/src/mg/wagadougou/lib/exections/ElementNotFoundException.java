package mg.wagadougou.lib.exections;

import mg.wagadougou.lib.classes.misc.RequestCollection;
import mg.wagadougou.lib.classes.misc.RequestFolder;

public class ElementNotFoundException extends Exception {

    public ElementNotFoundException(String id, RequestFolder folder) {
        super("Can't find the element " + id + " in " + folder.getId());
    }
    public ElementNotFoundException(String id, RequestCollection collection) {
        super("Can't find the element " + id + " in " + collection.getName());
    }
}
