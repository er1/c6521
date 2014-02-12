
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class C6521test {

    public static void main(String[] args) {
        String filename = "sample1";
        String tempfilename = filename + ".sortswap";
        try {
            System.out.println("Opening file " + filename);

            Path file = Paths.get(filename);
            Path tempfile = Paths.get(tempfilename);

            tempfile.toFile().createNewFile();

            System.out.println(file.toAbsolutePath());
            BlockAccess ba = new BlockAccess(file);
            BlockAccess batmp = new BlockAccess(tempfile);

            int count = ba.getBlockCount();

            System.out.println("File opened with " + Integer.toString(count) + " blocks");

            System.out.println("Creating Runs");

            int initialRun = 10;

            int acc = (count + initialRun - 1) / initialRun;
            int levels = 0;

            while (acc > 0) {
                acc /= 2;
                levels++;
            }

            System.out.println("Merge Levels: " + Integer.toString(levels));

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

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
