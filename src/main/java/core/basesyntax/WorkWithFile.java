package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeResult(toFileName, getTotalData(fromFileName));
    }

    private int[] getTotalData(String fileName) {
        File fileIn = new File(fileName);
        int [] totalData = {0, 0};
        try (BufferedReader reader = new BufferedReader(new FileReader(fileIn))) {
            String value = reader.readLine();

            while (value != null) {
                String [] parsedString = value.split(",");
                if (parsedString[0].equals("buy")) {
                    totalData[0] += Integer.parseInt(parsedString[1]);
                } else {
                    totalData[1] += Integer.parseInt(parsedString[1]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
        return totalData;
    }

    private void writeResult(String toFileName, int [] totalData) {
        File fileOut = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut, true))) {

            writer.write("supply," + totalData[1] + System.lineSeparator());
            writer.write("buy," + totalData[0] + System.lineSeparator());
            writer.write("result," + (totalData[1] - totalData[0]));

        } catch (IOException e) {
            throw new RuntimeException("Can't wright data to file " + toFileName, e);
        }
    }
}
