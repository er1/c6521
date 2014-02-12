
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BlockAccess {

    public static int readcount = 0;
    public static int writecount = 0;
    RandomAccessFile fc;

    public BlockAccess(File file) throws IOException {
        fc = new RandomAccessFile(file, "rw");
    }

    public void read(int n, Block b) throws IOException {
        byte[] data = b.getMutableBlockData();
        fc.seek(n * Block.BLOCK_SIZE);
        fc.read(data);
        readcount++;
    }

    public void write(int n, Block b) throws IOException {
        byte[] data = b.getMutableBlockData();
        fc.seek(n * Block.BLOCK_SIZE);
        fc.write(data);
        writecount++;
    }

    int getBlockCount() {
        int blockcount = 0;
        try {
            blockcount = (int) (fc.length() / Block.BLOCK_SIZE);
        } catch (IOException ex) {
        }
        return blockcount;
    }
}
