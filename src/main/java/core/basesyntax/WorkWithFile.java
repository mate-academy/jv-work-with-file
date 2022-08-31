package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = this.readFromFile(fromFileName);
        String report = this.createReport(dataFromFile);
        this.writeToFile(report, toFileName);
    }

    private String readFromFile(final String fromFileName) {
        File file = new File("./" + fromFileName);
        StringJoiner result = new StringJoiner("|");

        try (
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader)

        ) {
            String nextString = bufferedReader.readLine();
            while (nextString != null) {
                result.add(nextString);
                nextString = bufferedReader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read file: File not Found", e);
        }
        return result.toString();
    }

    private String createReport(final String dataFromFile) {
        StringBuilder result = new StringBuilder();
        int supply = 0;
        int buy = 0;
        String[] dataFromFileArray = dataFromFile.split("\\|");

        for (final String datum : dataFromFileArray) {
            String[] datumArray = datum.split(",");
            String operation = datumArray[0];
            int amount = Integer.parseInt(datumArray[1]);

            if (operation.equals("supply")) {
                supply += amount;
            } else {
                buy += amount;
            }
        }
        result.append("supply")
                .append(",")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy")
                .append(",")
                .append(buy)
                .append(System.lineSeparator())
                .append("result")
                .append(",")
                .append(supply - buy);
        return result.toString();
    }

    private void writeToFile(final String report, final String toFileName) {
        try (
                FileWriter fileWriter = new FileWriter(toFileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: File not Found", e);
        }
    }
}
