package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) throws IOException {
        String[] dataToWrite = null;
        try {
            BufferedReader readFromFile = new BufferedReader(new FileReader(fromFileName));
            StringBuilder stringBuilder = new StringBuilder();
            int value = readFromFile.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = readFromFile.read();
            }
            dataToWrite = stringBuilder.toString().split("\n");
        } catch (IOException e) {
            throw new IOException("Can`t read file", e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(fromFileName);
        } catch (IOException e) {
            throw new IOException("Can`t write to file", e);
        }

    }
}
