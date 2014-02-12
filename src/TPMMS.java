
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TPMMS {

    public static void TPMMS(File file, int initialRun) throws IOException {
        String tempfilename = file.toString() + ".sortswap";

        System.out.println("Opening file " + file.getPath());
        File tempfile = new File(tempfilename);
        tempfile.createNewFile();

        System.out.println(file.getPath());
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

        tempfile.delete();

        System.out.println("Done!");
        System.out.println("Total IO:");
        System.out.println("  Read:  " + Integer.toString(BlockAccess.readcount));
        System.out.println("  Write: " + Integer.toString(BlockAccess.writecount));

        System.out.println("Generating Range Table...");

        String rangefilename = file.toString() + ".ranges.txt";
        File rangefile = new File(rangefilename);
        rangefile.delete();

        BufferedWriter ranges;

        ranges = new BufferedWriter(new FileWriter(rangefile));

        Block b = new Block();
        for (int i = 0; i < count; i++) {
            b.reset();
            ba.read(i, b);
            Tuple t;
            t = b.get(0);
            String key1 = new String(t.getKey()).trim();
            t = b.get(Block.TUPLES_PER_BLOCK - 1);
            String key2 = new String(t.getKey()).trim();

            ranges.write(Integer.toString(i + 1) + "\t" + key1 + "\t" + key2);
            ranges.newLine();
        }

        ranges.close();

        System.out.println("Done!");
    }
}
