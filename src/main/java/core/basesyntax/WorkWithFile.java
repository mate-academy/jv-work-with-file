package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private BufferedReader bufferedReader;
    private String[] arrayToWriteIntoFile = new String[3];

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        try {
            bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] counter = value.split(",");
                if (counter[0].equals("supply")) {
                    supply = supply + Integer.parseInt(counter[1]);
                } else {
                    buy = buy + Integer.parseInt(counter[1]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant read from file" + fromFileName, e);
        }
        arrayToWriteIntoFile[0] = "supply," + supply;
        arrayToWriteIntoFile[1] = "buy," + buy;
        arrayToWriteIntoFile[2] = "result," + (supply - buy);
        writeToFile(arrayToWriteIntoFile, toFileName);
    }

    private void writeToFile(String[] data, String toFileName) {
        int count = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            for (String s : data) {
                writer.write(s);
                if (count < data.length - 1) {
                    writer.newLine();
                    count++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant write to file" + toFileName, e);
        }
    }
}
