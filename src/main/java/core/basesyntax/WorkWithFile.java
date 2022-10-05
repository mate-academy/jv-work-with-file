package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMMOUNT_INDEX = 1;
    private int supply = 0;
    private int buy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);

        String builder = "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);

        writeToFile(toFileName, builder);
    }

    private void readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();

            while (value != null) {
                String[] lineValue = value.split(",");
                switch (lineValue[OPERATION_TYPE_INDEX]) {
                    case "buy":
                        buy += Integer.parseInt(lineValue[AMMOUNT_INDEX]);
                        break;
                    case "supply":
                        supply += Integer.parseInt(lineValue[AMMOUNT_INDEX]);
                        break;
                    default:
                        break;
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't create a report for file: " + fromFileName, e);
        }
    }

    private void writeToFile(String toFileName, String message) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(message);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }
}
