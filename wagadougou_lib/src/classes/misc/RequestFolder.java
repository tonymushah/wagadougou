package classes.misc;

import java.util.ArrayList;

import classes.requestTypes.base.HTTPRequestElement;
import exections.ElementNotFoundException;

public class RequestFolder {
    private String name;
    private String id;
    private String doc;
    private ArrayList<HTTPRequestElement> childrenElements;
    private ArrayList<RequestFolder> childrenFolders;
    public String getName() {
        return name;
    }
    public RequestFolder setName(String name) {
        this.name = name;
        return this;
    }
    public String getId() {
        return id;
    }
    public RequestFolder setId(String id) {
        this.id = id;
        return this;
    }
    public String getDoc() {
        return doc;
    }
    public RequestFolder setDoc(String doc) {
        this.doc = doc;
        return this;
    }
    public ArrayList<HTTPRequestElement> getChildrenElements() {
        return childrenElements;
    }
    public RequestFolder setChildrenElements(ArrayList<HTTPRequestElement> childrenElements) {
        this.childrenElements = childrenElements;
        return this;
    }
    public ArrayList<RequestFolder> getChildrenFolders() {
        return childrenFolders;
    }
    private RequestFolder setChildrenFolders(ArrayList<RequestFolder> childrenFolders) {
        this.childrenFolders = childrenFolders;
        return this;
    }
    public RequestFolder(String id) {
        this.setId(id);
        this.setChildrenElements(new ArrayList<HTTPRequestElement>());
        this.setChildrenFolders(new ArrayList<RequestFolder>());
    }
    public RequestFolder addRequest(HTTPRequestElement request){
        this.childrenElements.add(request);
        return this;
    }
    public RequestFolder addFolder(RequestFolder requestFolder){
        if(requestFolder.equals(this)){
            throw new IllegalArgumentException("You can't add the root folder to his children");
        }else{
            this.childrenFolders.add(requestFolder);
            return this;
        }
        
    }
    public HTTPRequestElement getElementByID(String id) throws ElementNotFoundException{
        for (HTTPRequestElement httpRequestElement : childrenElements) {
            if(httpRequestElement.getId().compareTo(id) == 0){
                return httpRequestElement;
            }
        }
        for (RequestFolder httpRequestElement : childrenFolders) {
            try {
                return httpRequestElement.getElementByID(id);
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
        throw new ElementNotFoundException(id, this);
    }
    
    public RequestFolder getFolderByID(String id) throws ElementNotFoundException{
        for (RequestFolder requestFolder : this.getChildrenFolders()) {
            if(requestFolder.getId().compareTo(id) == 0){
                return requestFolder;
            }
        }
        for (RequestFolder requestFolder : this.getChildrenFolders()) {
            try {
                return requestFolder.getFolderByID(id);
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
        throw new ElementNotFoundException(id, this);
    }
    public ArrayList<HTTPRequestElement> getElementByName(String name){
        ArrayList<HTTPRequestElement> arrayList = new ArrayList<HTTPRequestElement>();
        for (HTTPRequestElement httpRequestElement : childrenElements) {
            if(httpRequestElement.getId().compareTo(id) == 0){
                arrayList.add(httpRequestElement);
            }
        }
        for (RequestFolder httpRequestElement : childrenFolders) {
            try {
                arrayList.addAll(httpRequestElement.getElementByName(name));
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
        return arrayList;
    }
    public ArrayList<RequestFolder> getFolderByName(String name){
        ArrayList<RequestFolder> arrayList = new ArrayList<RequestFolder>();
        for (RequestFolder requestFolder : this.getChildrenFolders()) {
            if(requestFolder.getId().compareTo(id) == 0){
                arrayList.add(requestFolder);
            }
        }
        for (RequestFolder requestFolder : this.getChildrenFolders()) {
            try {
                arrayList.addAll(requestFolder.getFolderByName(name));
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
        return arrayList;
    }
}
