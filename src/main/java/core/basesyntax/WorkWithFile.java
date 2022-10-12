package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int buy = 0;
    private int supply = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile workWithFile = new WorkWithFile();
        String firstResultString = workWithFile.read(fromFileName);
        String resultString = workWithFile.createReport(firstResultString);
        workWithFile.write(resultString, toFileName);
    }

    public String read(String fromFileName) {
        File fromFile = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFile));
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    public String createReport(String dataFromFile) {
        String[] allArray = dataFromFile.split(",");
        for (int i = 0; i < allArray.length; i = i + 2) {
            if (allArray[i].equals("buy")) {
                buy = buy + Integer.parseInt(allArray[i + 1]);
            }
            if (allArray[i].equals("supply")) {
                supply = supply + Integer.parseInt(allArray[i + 1]);
            }
        }
        int result = supply - buy;
        return "supply," + supply + System.lineSeparator() + "buy," + buy
                + System.lineSeparator() + "result," + result;
    }

    public void write(String resultString, String toFileName) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(resultString);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file" + toFileName, e);
        }
    }
}
