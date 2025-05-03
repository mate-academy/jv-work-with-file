package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String report = readFrom(fromFileName);
        writeData(report, toFileName);
    }

    private String readFrom(String fileName) {
        String data;
        File file = new File(fileName);
        String line = "";
        int sum = 0;
        int sum2 = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] column = line.split(",");
                int numbers = Integer.parseInt(column[1]);
                if (column[0].equals("supply")) {
                    sum += numbers;
                } else if (column[0].equals("buy")) {
                    sum2 += numbers;
                }
            }
            data = "supply," + sum + System.lineSeparator()
                    + "buy," + sum2 + System.lineSeparator()
                    + "result," + (sum - sum2) + System.lineSeparator();
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Can not read file: " + fileName + ", " + e);
        }
    }

    private void writeData(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        String dataToWrite = fromFileName;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            bufferedWriter.write(dataToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can not read file: " + toFileName + ", " + e);
        }
    }
}
