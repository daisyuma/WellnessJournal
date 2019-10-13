package model;

import exceptions.InvalidInputException;

import java.io.IOException;
import java.util.ArrayList;

public interface Loadable {
    public Object loadEntry() throws IOException, InvalidInputException;
}
