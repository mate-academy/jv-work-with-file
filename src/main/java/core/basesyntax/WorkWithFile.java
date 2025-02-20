package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String infoFromFile = readFromFile(fromFileName);
        String report = createReport(infoFromFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        String resultString;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            resultString = stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("File not found: " + fromFileName
                    + ", " + e.getMessage());
        }
        return resultString;
    }

    private void writeToFile(String report, String toFileName) {

        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            bufferedWriter.write(report);
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName
                    + ", " + e.getMessage());
        }
    }

    private String createReport(String resultString) {
        String[] values = resultString.split("\\R|,");
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i].startsWith("supply")) {
                supply += Integer.parseInt(values[i + 1]);
            }
            if (values[i].startsWith("buy")) {
                buy += Integer.parseInt(values[i + 1]);
            }
        }
        int result = supply - buy;
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result + System.lineSeparator();
    }
}
