package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int supply;
    private int buy;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFIle(fromFileName);
        writeToFIle(toFileName);
    }

    private void readFromFIle(String fromFile) {
        try (BufferedReader readFile = new BufferedReader(new FileReader(fromFile + ""))) {
            String value = readFile.readLine();
            while (value != null) {
                String[] line = value.split(",");
                if (line[0].equals("supply")) {
                    supply += Integer.parseInt(line[1]);
                } else {
                    buy += Integer.parseInt(line[1]);
                }
                value = readFile.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found" + fromFile, e);
        }
    }

    private void writeToFIle(String toFile) {
        try (BufferedWriter writeFile = new BufferedWriter(new FileWriter(toFile + ""))) {
            writeFile.write(new StringBuilder().append("supply,").append(supply)
                    .append(System.lineSeparator()).append("buy,").append(buy)
                    .append(System.lineSeparator()).append("result,")
                    .append(supply - buy).toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't create a file!" + toFile, e);
        }
    }
}
