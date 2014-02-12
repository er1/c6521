
import java.io.IOException;

public class RunMerge {

    public static void merge(int runLength, BlockAccess source, BlockAccess dest) throws IOException {
        int blockCount = source.getBlockCount();
        int runs = (blockCount + runLength * 2 - 1) / (runLength * 2);

        for (int i = 0; i < runs; i++) {

            int start = i * runLength * 2;
            int end = Math.min(start + runLength * 2, blockCount);

            int leftIndex = start;
            int rightIndex = Math.min(start + runLength, blockCount);
            int leftEnd = rightIndex;
            int rightEnd = end;

            int outIndex = start;

            Block a = new Block();
            Block b = new Block();
            Block out = new Block();

            a.unset();
            b.unset();

            while (a.hasNext() || (leftIndex < leftEnd) || b.hasNext() || (rightIndex < rightEnd)) {
                if ((!a.hasNext()) && (leftIndex < leftEnd)) {
                    a.reset();
                    source.read(leftIndex, a);
                    leftIndex++;
                }

                if ((!b.hasNext()) && (rightIndex < rightEnd)) {
                    b.reset();
                    source.read(rightIndex, b);
                    rightIndex++;
                }

                if (a.hasNext() && b.hasNext()) {
                    if (a.currentTuple().compareTo(b.currentTuple()) < 0) {
                        out.putTupleData(a.getTupleData());
                    } else {
                        out.putTupleData(b.getTupleData());
                    }
                } else {
                    if (a.hasNext()) {
                        out.putTupleData(a.getTupleData());
                    }
                    if (b.hasNext()) {
                        out.putTupleData(b.getTupleData());
                    }
                }

                if (!out.hasNext()) {
                    dest.write(outIndex, out);
                    out.reset();
                    outIndex++;
                }
            }
        }
    }
}
