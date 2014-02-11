
import java.util.Arrays;

public class Block {

    static final int BLOCK_SIZE = 4096;
    static final int TUPLE_SIZE = 100;
    static final int TUPLES_PER_BLOCK = 40;

    byte[] blockdata;
    int index = 0;

    public Block() {
        blockdata = new byte[BLOCK_SIZE];
    }

    public Block(byte[] data) {
        blockdata = data;
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
