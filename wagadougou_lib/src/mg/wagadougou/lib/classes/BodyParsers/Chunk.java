package mg.wagadougou.lib.classes.BodyParsers;

import java.io.ByteArrayInputStream;

public class Chunk {
    private int size;
    private ByteArrayInputStream data;
    public int getSize() {
        return size;
    }
    public ByteArrayInputStream getData() {
        return data;
    }
    public Chunk setSize(int size) {
        this.size = size;
        return this;
    }
    public Chunk setData(ByteArrayInputStream data) {
        this.data = data;
        return this;
    }
    public Chunk(){
        this.setSize(0);
        this.setData(new ByteArrayInputStream("".getBytes()));
    }
}
