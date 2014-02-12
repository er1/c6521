
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TPMMS {

    public static void TPMMS(Path file, int initialRun) throws IOException {
        String tempfilename = file.toString() + ".sortswap";

        System.out.println("Opening file " + file.toString());
        Path tempfile = Paths.get(tempfilename);
        tempfile.toFile().createNewFile();

        System.out.println(file.toAbsolutePath());
        BlockAccess ba = new BlockAccess(file);
        BlockAccess batmp = new BlockAccess(tempfile);

        int count = ba.getBlockCount();

        System.out.println("File opened with " + Integer.toString(count) + " blocks");

        System.out.println("Creating Runs");

        int acc = (count + initialRun - 1) / initialRun;
        int levels = 0;

        while (acc > 0) {
            acc /= 2;
            levels++;
        }

        BlockAccess ba1;
        BlockAccess ba2;

        if (levels % 2 == 0) {
            ba1 = ba;
            ba2 = batmp;
        } else {
            ba1 = batmp;
            ba2 = ba;
        }

        int currentRun = initialRun;

        RunSorter rs = new RunSorter(ba, ba1, currentRun);
        rs.run();

        for (int i = 0; i < levels; i++) {
            RunMerge.merge(currentRun, ba1, ba2);

            BlockAccess bat = ba1;
            ba1 = ba2;
            ba2 = bat;
            currentRun *= 2;

        }

        System.out.println("Done!");
        System.out.println("Total IO:");
        System.out.println("  Read:  " + Integer.toString(BlockAccess.readcount));
        System.out.println("  Write: " + Integer.toString(BlockAccess.writecount));

        System.out.println("Generating Range Table...");

        String rangefilename = file.toString() + ".ranges.txt";
        Path rangefile = Paths.get(rangefilename);

        Block b = new Block();
        for (int i = 0; i < count; i++) {
            b.reset();
            ba.read(i, b);
            Tuple t = new Tuple(b.getTupleData());
            String key = new String(t.getKey()).trim();

            System.out.println(Integer.toString(i + 1) + "\t" + key);
        }

        System.out.println("Done!");
    }
}
