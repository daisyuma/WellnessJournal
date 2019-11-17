package model;


import java.io.IOException;

public interface Saveable {

    //REQUIRES:
    //EFFECTS: 1.reads each item from the file
    //         2. save fields of each item as strings in the file
    public void saveEntry() throws IOException;
}

//

