
import java.io.IOException;

public class BlockMerge {

    BlockAccess ba;
    Block[] leftBuffers;
    Block[] rightBuffers;
    Block outBuffer;

    int totalBlocks;

    public BlockMerge(BlockAccess b, int inbuffers) {
        ba = b;
        outBuffer = new Block();
        totalBlocks = ba.getBlockCount();
    }

    // EPIC FIXME NEEDED
    // this just merges two ajacent blocks
    
    public void sort() throws IOException {
        Block a = new Block();
        Block b = new Block();

        for (int i = 0; i < totalBlocks - 1; i += 2) {
            ba.read(i, a);
            ba.read(i + 1, b);

            while (a.hasNext() || b.hasNext()) {
                if (!outBuffer.hasNext()) {
                    ba.write(i, outBuffer);
                    outBuffer = new Block();
                }
                if (a.hasNext() && b.hasNext()) {
                    if (a.currentTuple().compareTo(b.currentTuple()) < 0) {
                        outBuffer.putTupleData(a.getTupleData());
                    } else {
                        outBuffer.putTupleData(b.getTupleData());
                    }
                } else {
                    if (a.hasNext()) {
                        outBuffer.putTupleData(a.getTupleData());
                    }
                    if (b.hasNext()) {
                        outBuffer.putTupleData(b.getTupleData());
                    }
                }
            }

            ba.write(i + 1, outBuffer);
        }
    }

    private void merge(int start, int runLength) {

    }
}
