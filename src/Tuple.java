
public class Tuple implements Comparable<Tuple> {

    byte[] data;

    public Tuple(byte[] tupledata) {
        data = tupledata;
    }

    public byte[] toByteArray() {
        return data;
    }

    @Override
    public int compareTo(Tuple t) {
        String thiskey = new String(data, 0, 7);
        String thatkey = new String(t.data, 0, 7);
        return thiskey.compareTo(thatkey);
    }
}
