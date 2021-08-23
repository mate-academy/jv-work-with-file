package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        File inputData = new File(fromFileName);
        FileReader fileReader;
        try {
            fileReader = new FileReader(inputData);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Problem", e);
        }
        int[] out = createReport(fileReader, supply, buy);
        File outputData = new File(toFileName);
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(outputData);
        } catch (IOException e) {
            throw new RuntimeException("Problem", e);
        }
        writeToFile(fileWriter, out[0], out[1]);
    }

    private int[] createReport(FileReader fileReader, int supply, int buy) {
        String[] record;
        String recordString = "";
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            recordString = bufferedReader.readLine();
            while (recordString != null) {
                record = recordString.split(",");
                if (record[0].equals("supply")) {
                    supply += Integer.parseInt(record[1]);
                } else {
                    buy += Integer.parseInt(record[1]);
                }
                recordString = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Problem", e);
        }
        return new int[] {supply, buy};
    }

    private void writeToFile(FileWriter fileWriter, int supply, int buy) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(new StringBuilder("supply,")
                    .append(String.valueOf(supply))
                    .append(System.lineSeparator())
                    .append("buy,")
                    .append(String.valueOf(buy))
                    .append(System.lineSeparator())
                    .append("result,")
                    .append(String.valueOf(supply - buy))
                    .toString());
        } catch (IOException e) {
            throw new RuntimeException("Problem", e);
        }
    }
}
