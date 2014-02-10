
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

    public Block read(int n) throws IOException {
        byte[] data = new byte[Block.BLOCK_SIZE];
        ByteBuffer buf = ByteBuffer.wrap(data);
        fc.read(buf, n * Block.BLOCK_SIZE);
        return new Block(data);
    }

    public void write(int n, Block b) throws IOException {
        byte[] data = b.getBlockData();
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
