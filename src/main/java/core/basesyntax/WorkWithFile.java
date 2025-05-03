package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeInfoToFile(createReport(readFromFile(fromFileName)), toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] allInputDataOnArray;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            allInputDataOnArray = stringBuilder.toString().split(System.lineSeparator());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return allInputDataOnArray;
    }

    private String createReport(String[] allInputDataOnArray) {
        int supply = 0;
        int buy = 0;

        for (String i : allInputDataOnArray) {
            if (i.startsWith("supply")) {
                supply += Integer.parseInt(i.replace("supply,", ""));
            } else {
                buy += Integer.parseInt(i.replace("buy,", ""));
            }
        }

        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }

    private void writeInfoToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }
}
