
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
        int thiskey = Integer.MAX_VALUE;
        int thatkey = Integer.MAX_VALUE;

        try {
            thiskey = Integer.parseInt(new String(data, 0, 7).trim());
        } catch (NumberFormatException e) {
        }
        try {
            thatkey = Integer.parseInt(new String(t.data, 0, 7).trim());
        } catch (NumberFormatException e) {
        }
        return thiskey - thatkey;
        //return thiskey.compareTo(thatkey);
    }
}
