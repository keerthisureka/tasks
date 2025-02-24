package assignment;

import java.io.IOException;

public interface MyFileHandler {
    void read(String filePath, MyCollection collection) throws IOException;
    void write(String filePath, MyCollection collection) throws IOException;
}
