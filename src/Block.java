
import java.util.Arrays;

public class Block {

    static final int BLOCK_SIZE = 4096;
    static final int TUPLE_SIZE = 100;
    static final int TUPLES_PER_BLOCK = 40;

    byte[] blockdata;
    int index = 0;

    public Block() {
        blockdata = new byte[BLOCK_SIZE];
        
        // pad the block with proper ascii and a newline for readability
        Arrays.fill(blockdata, 4000, 4095, (byte)61);
        blockdata[4095] = 10;
    }

    public void reset() {
        index = 0;
    }

    public boolean hasNext() {
        return index + TUPLE_SIZE < BLOCK_SIZE;
    }

    public byte[] getTupleData() {
        byte[] tupledata = Arrays.copyOfRange(blockdata, index, index + TUPLE_SIZE);
        index += TUPLE_SIZE;
        return tupledata;
    }

    public void putTupleData(byte[] tupledata) {
        System.arraycopy(tupledata, 0, blockdata, index, TUPLE_SIZE);
        index += TUPLE_SIZE;
    }

    public Tuple currentTuple() {
        return new Tuple(Arrays.copyOfRange(blockdata, index, index + TUPLE_SIZE));
    }

    public void sort() {
        Tuple[] tuples = new Tuple[TUPLES_PER_BLOCK];
        reset();
        int i = 0;
        while (hasNext() && i < tuples.length) {
            tuples[i++] = new Tuple(getTupleData());
        }
        Arrays.sort(tuples);
        reset();
        i = 0;
        while (hasNext() && i < tuples.length) {
            putTupleData(tuples[i++].toByteArray());
        }
    }

    byte[] getBlockData() {
        return blockdata;
    }
}
