import java.io.FileNotFoundException;
import java.io.PrintWriter;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class MakeInputFile {

    public static void main(String[] args) {
        int count = Integer.parseInt(args[0]);
        int size = Integer.parseInt(args[1]);

        String filename = new String( "input" + count + ".txt");
        StdOut.println(filename);

        try {
            PrintWriter write = new PrintWriter(filename);

            StdOut.println(count);
            write.println(count);
            for (int i = 0; i < count; i++) {
                StdOut.println(StdRandom.uniform(size) + "  " + StdRandom.uniform(size));
                write.println(StdRandom.uniform(size) + "  " + StdRandom.uniform(size));
            }
            write.close();
        } catch (FileNotFoundException err) {
            err.printStackTrace();
        }
    }
}
