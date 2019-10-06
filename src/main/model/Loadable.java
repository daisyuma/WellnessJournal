package model;

import java.io.IOException;
import java.util.ArrayList;

public interface Loadable {
    public Object loadEntry() throws IOException;
}
