package barnestr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ForEachArrayStrategy implements AutoCompleter{

    ArrayList<String> entries = new ArrayList<>();
    @Override
    public void initialize(String filename) {
        //TODO
        try(Scanner in = new Scanner(new File(filename))) {
            while(in.hasNext()) {
                if (filename.endsWith(".csv")) {
                    String[] line = in.nextLine().split(",");
                    entries.add()
                } else if (filename.endsWith(".txt")) {
                    entries.add(in.next());
                }
            }
        } catch (FileNotFoundException e) {

        }
    }

    @Override
    public List allThatBeginsWith(String prefix) {

        return null;
    }

    @Override
    public long getLastOperationTime() {
        return 0;
    }
}
