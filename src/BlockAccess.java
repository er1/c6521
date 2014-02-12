
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class BlockAccess {

    FileChannel fc;

    public BlockAccess(Path file) throws IOException {
        fc = FileChannel.open(file, StandardOpenOption.READ, StandardOpenOption.WRITE);
    }

    public void read(int n, Block b) throws IOException {
        byte[] data = b.getMutableBlockData();
        ByteBuffer buf = ByteBuffer.wrap(data);
        fc.read(buf, n * Block.BLOCK_SIZE);
    }

    public void write(int n, Block b) throws IOException {
        byte[] data = b.getMutableBlockData();
        
        // debug code
        
        String s = " ^^^ " + Integer.toString(n) + " ^^^ ";
        System.arraycopy(s.getBytes(), 0, data, 4000, s.length());
        
        ByteBuffer buf = ByteBuffer.wrap(data);
        fc.write(buf, n * Block.BLOCK_SIZE);
    }

    int getBlockCount() {
        int blockcount = 0;
        try {
            blockcount = (int) (fc.size() / Block.BLOCK_SIZE);
        } catch (IOException ex) {
        }
        return blockcount;
    }
}
