package exections;

import classes.misc.RequestFolder;

public class ElementNotFoundException extends Exception {

    public ElementNotFoundException(String id, RequestFolder folder) {
        super("Can't find the element " + id + " in " + folder.getId());
    }
    
}
