package core.basesyntax;

import java.io.*;
import java.nio.file.Files;


public class WorkWithBytes {
  public void writeBytesToFile(String fileName, byte[] data) {

    File file = new File(fileName);
    try {
      Files.write(file.toPath(), data);
    } catch (IOException e) {
      throw new RuntimeException("Can't create file", e);
    }
  }
}