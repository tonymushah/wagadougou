package mg.wagadougou.lib.classes.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import mg.wagadougou.lib.classes.clients.AbstractClient;
import mg.wagadougou.lib.classes.requestTypes.base.HTTPRequestElement;
import mg.wagadougou.lib.classes.responseTypes.HTTPResponse;
import mg.wagadougou.lib.exections.ElementNotFoundException;

public class RequestCollection implements Serializable {
    private AbstractClient client;
    private ArrayList<RequestFolder> folders;
    private ArrayList<HTTPRequestElement> requests;
    private String name;

    public AbstractClient getClient() {
        return client;
    }

    public RequestCollection setClient(AbstractClient client) {
        this.client = client;
        return this;
    }

    public ArrayList<RequestFolder> getFolders() {
        return folders;
    }

    public RequestCollection setFolders(ArrayList<RequestFolder> folders) {
        this.folders = folders;
        return this;
    }

    public ArrayList<HTTPRequestElement> getRequests() {
        return requests;
    }

    public RequestCollection setRequests(ArrayList<HTTPRequestElement> requests) {
        this.requests = requests;
        return this;
    }

    public RequestCollection addFolder(RequestFolder requestFolder) {
        this.folders.add(requestFolder);
        return this;
    }

    public RequestCollection addRequest(HTTPRequestElement request) {
        this.requests.add(request);
        return this;
    }

    public RequestCollection addFolders(ArrayList<RequestFolder> folders) {
        this.folders.addAll(folders);
        return this;
    }

    public RequestCollection addRequests(ArrayList<HTTPRequestElement> requests) {
        this.requests.addAll(requests);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RequestFolder getFolderByIDLevel0(String id) throws ElementNotFoundException {
        for (RequestFolder folder : this.getFolders()) {
            if (folder.getId().compareTo(id) == 0) {
                return folder;
            }
        }
        throw new ElementNotFoundException(id, this);
    }

    public HTTPRequestElement getRequestByIDLevel0(String id) throws ElementNotFoundException {
        for (HTTPRequestElement requestElement : this.getRequests()) {
            if (requestElement.getId().compareTo(id) == 0) {
                return requestElement;
            }
        }
        throw new ElementNotFoundException(id, this);
    }

    public RequestFolder getFolderByID(String id) throws ElementNotFoundException {
        try {
            this.getFolderByIDLevel0(id);
        } catch (Exception e) {
            // TODO: handle exception
        }
        for (RequestFolder folder : this.getFolders()) {
            try {
                return folder.getFolderByID(id);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        throw new ElementNotFoundException(id, this);
    }

    public HTTPRequestElement getRequestByID(String id) throws ElementNotFoundException {
        for (HTTPRequestElement requestElement : this.getRequests()) {
            if (requestElement.getId().compareTo(id) == 0) {
                return requestElement;
            }
        }
        for (RequestFolder folder : this.getFolders()) {
            try {
                return folder.getElementByID(id);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        throw new ElementNotFoundException(id, this);
    }

    public ArrayList<HTTPRequestElement> getRequestByName(String name) {
        ArrayList<HTTPRequestElement> requestElements = new ArrayList<HTTPRequestElement>();
        for (HTTPRequestElement httpRequestElement : this.getRequests()) {
            if (httpRequestElement.getName().compareTo(name) == 0) {
                requestElements.add(httpRequestElement);
            }
        }
        for (RequestFolder requestFolder : folders) {
            requestElements.addAll(requestFolder.getElementByName(name));
        }
        return requestElements;
    }

    public RequestCollection removeFolderByIDLevel0(String id) throws ElementNotFoundException {
        this.folders.remove(this.getFolderByIDLevel0(id));
        return this;
    }

    public RequestCollection removeRequestByIDLevel0(String id) throws ElementNotFoundException {
        this.folders.remove(this.getRequestByIDLevel0(id));
        return this;
    }

    public RequestCollection removeFolderByID(String id) throws ElementNotFoundException {
        try {
            return this.removeFolderByIDLevel0(id);
        } catch (Exception e) {
            // TODO: handle exception
        }
        for (RequestFolder requestFolder : folders) {
            try {
                requestFolder.remove_childrenFolderByID(id);
                return this;
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        throw new ElementNotFoundException(id, this);
    }

    public RequestCollection removeRequestByID(String id) throws ElementNotFoundException {
        try {
            return this.removeRequestByIDLevel0(id);
        } catch (Exception e) {
            // TODO: handle exception
        }
        for (RequestFolder requestFolder : folders) {
            try {
                requestFolder.remove_childrenElementsByID(id);
                return this;
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        throw new ElementNotFoundException(id, this);
    }

    public HTTPResponse send(HTTPRequestElement requestElement) throws Exception {
        return this.client.send(requestElement);
    }

    public HTTPResponse sendByID(String id) throws Exception {
        return this.client.send(this.getRequestByID(id));
    }

    public RequestCollection() {
        this.setFolders(new ArrayList<RequestFolder>());
        this.setRequests(new ArrayList<HTTPRequestElement>());
    }

    public RequestCollection(String name) {
        this.setFolders(new ArrayList<RequestFolder>());
        this.setRequests(new ArrayList<HTTPRequestElement>());
        this.setName(name);
    }

    public RequestCollection(AbstractClient client) {
        this.setFolders(new ArrayList<RequestFolder>());
        this.setRequests(new ArrayList<HTTPRequestElement>());
        this.setClient(client);
    }

    public RequestCollection(String name, AbstractClient client) {
        this.setFolders(new ArrayList<RequestFolder>());
        this.setRequests(new ArrayList<HTTPRequestElement>());
        this.setName(name);
        this.setClient(client);
    }

    public void export_to(File file) throws FileNotFoundException, IOException {
        ObjectOutputStream stream = null;
        try {
            stream = new ObjectOutputStream(new FileOutputStream(file));
            stream.writeObject(this);
            stream.flush();
        } catch (IOException e) {
            //TODO: handle exception
            throw e;
        }finally{
            if(stream != null){
                stream.close();
            }
        }
        

    }

    public static RequestCollection from(File data) throws IOException, ClassNotFoundException {
        ObjectInputStream stream = null;
        try {
            stream = new ObjectInputStream(new FileInputStream(data));
            RequestCollection collection = (RequestCollection) stream.readObject();
            return collection;
        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            throw e;
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }
}
