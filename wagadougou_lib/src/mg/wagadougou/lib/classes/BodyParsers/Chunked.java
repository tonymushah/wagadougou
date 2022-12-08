package mg.wagadougou.lib.classes.BodyParsers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HexFormat;

public class Chunked {
    private ArrayList<Chunk> chunks;
    public ArrayList<Chunk> getChunks() {
        return chunks;
    }
    public Chunked setChunks(ArrayList<Chunk> chunks) {
        this.chunks = chunks;
        return this;
    }
    public Chunked(InputStream to_use){
        BufferedReader reader = new BufferedReader(new InputStreamReader(to_use));
        int line = 0;
        int curr_chunk = 0;
        this.setChunks(new ArrayList<Chunk>());
        for (String string_line : reader.lines().toList()) {
            if(line % 2 == 0){
                this.chunks.add(new Chunk().setSize(HexFormat.fromHexDigits(string_line)));
            }else{
                this.chunks.get(curr_chunk).setData(new ByteArrayInputStream(string_line.getBytes()));
                curr_chunk++;
            }
            line++;
        }
    }
    public InputStream getData() throws IOException{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (Chunk chunk : chunks) {
            out.write(chunk.getData().readAllBytes());
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
