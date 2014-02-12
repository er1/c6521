
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class BlockAccess {

    public static int readcount = 0;
    public static int writecount = 0;

    FileChannel fc;

    public BlockAccess(Path file) throws IOException {
        fc = FileChannel.open(file, StandardOpenOption.READ, StandardOpenOption.WRITE);
    }

    public void read(int n, Block b) throws IOException {
        byte[] data = b.getMutableBlockData();
        ByteBuffer buf = ByteBuffer.wrap(data);
        fc.read(buf, n * Block.BLOCK_SIZE);

        readcount++;
    }

    public void write(int n, Block b) throws IOException {
        byte[] data = b.getMutableBlockData();
        ByteBuffer buf = ByteBuffer.wrap(data);
        fc.write(buf, n * Block.BLOCK_SIZE);

        writecount++;
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
