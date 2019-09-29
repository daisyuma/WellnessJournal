package model;


import java.io.IOException;
import java.util.ArrayList;

public interface Saveable {

    //REQUIRES:
    //EFFECTS: 1.reads each item from the file
    //         2.
    public void save() throws IOException;
}

