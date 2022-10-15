package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String information = readFromFile(fromFileName);
        String report = createReport(information);
        writeToFile(toFileName, report);
    }

    public void writeToFile(String toFileName, String report) {
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(report);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder info = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                info.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return info.toString();
    }

    private String createReport(String data) {
        int supply = 0;
        int buy = 0;
        int result;
        String[] array = data.split(" ");
        for (String operation : array) {
            if (operation.split(",")[0].equals("supply")) {
                supply += Integer.parseInt(operation.split(",")[1]);
            } else if (operation.split(",")[0].equals("buy")) {
                buy += Integer.parseInt(operation.split(",")[1]);
            }
        }
        result = supply - buy;
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result;
    }
}
