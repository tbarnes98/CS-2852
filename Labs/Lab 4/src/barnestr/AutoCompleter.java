package barnestr;

import java.util.List;

public interface AutoCompleter {

    void initialize(String filename);

    List allThatBeginsWith(String prefix);

    long getLastOperationTime();

}
