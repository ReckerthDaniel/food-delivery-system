package data;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriter {

  public static void writeToFile(String fileName, String content) {
    try (BufferedWriter br = new BufferedWriter(new java.io.FileWriter(fileName, true))) {
      br.write(content);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
