package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(fromFileName,toFileName);
    }

    private String createReport(String fromFileName) {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            int supply = 0;
            int buy = 0;

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
            builder.append("supply,").append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy).append(System.lineSeparator())
                    .append("result,").append(supply - buy);
        } catch (IOException e) {
            throw new RuntimeException("Can't create a report for file: " + fromFileName, e);
        }
        return builder.toString();
    }

    private void writeToFile(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(createReport(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }
}
