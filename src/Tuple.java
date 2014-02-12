
import java.util.Arrays;

public class Tuple implements Comparable<Tuple> {

    byte[] data;

    public Tuple(byte[] tupledata) {
        data = tupledata;
    }

    public byte[] toByteArray() {
        return data;
    }

    public byte[] getKey() {
        return Arrays.copyOfRange(data, 0, 7);
    }

    @Override
    public int compareTo(Tuple t) {
        int thiskey = Integer.MAX_VALUE;
        int thatkey = Integer.MAX_VALUE;

        try {
            thiskey = Integer.parseInt(new String(getKey()).trim());
        } catch (NumberFormatException e) {
        }
        try {
            thatkey = Integer.parseInt(new String(t.getKey()).trim());
        } catch (NumberFormatException e) {
        }
        return thiskey - thatkey;
        //return thiskey.compareTo(thatkey);
    }
}
