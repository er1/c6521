
import java.util.AbstractList;
import java.util.Arrays;

public class Block extends AbstractList<Tuple> {

    static final int BLOCK_SIZE = 4096;
    static final int TUPLE_SIZE = 100;
    static final int TUPLES_PER_BLOCK = 40;

    byte[] blockdata;
    int index = 0;

    public Block() {
        blockdata = new byte[BLOCK_SIZE];

        // pad the block with proper ascii and a newline for readability
        Arrays.fill(blockdata, TUPLE_SIZE * TUPLES_PER_BLOCK, BLOCK_SIZE - 1, (byte) 61);
        blockdata[BLOCK_SIZE - 1] = 10;
    }

    public void reset() {
        Arrays.fill(blockdata, 0, BLOCK_SIZE - 1, (byte) 61);
        blockdata[BLOCK_SIZE - 1] = 10;
        index = 0;
    }

    public void unset() {
        index = BLOCK_SIZE;
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

    byte[] getMutableBlockData() {
        return blockdata;
    }

    @Override
    public Tuple set(int index, Tuple t) {
        Tuple r = new Tuple(Arrays.copyOfRange(blockdata, index * TUPLE_SIZE, index * TUPLE_SIZE + TUPLE_SIZE));
        System.arraycopy(t.toByteArray(), 0, blockdata, index * TUPLE_SIZE, TUPLE_SIZE);
        return r;
    }

    @Override
    public Tuple get(int index) {
        return new Tuple(Arrays.copyOfRange(blockdata, index * TUPLE_SIZE, index * TUPLE_SIZE + TUPLE_SIZE));
    }

    @Override
    public int size() {
        return TUPLES_PER_BLOCK;
    }
}
