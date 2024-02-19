package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SPACE = " ";
    private static final String REGEX = "\\W+";

    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.writeToFile(toFileName, workWithFile.getResultString(fromFileName));

    }

    private String getResultString(String fileName) {
        WorkWithFile workWithFile = new WorkWithFile();
        String[] dataArray = workWithFile.readFile(fileName);
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < dataArray.length; i += 2) {
            if (dataArray[i].equals("supply")) {
                supply += Integer.parseInt(dataArray[i + 1]);
            } else if (dataArray[i].equals("buy")) {
                buy += Integer.parseInt(dataArray[i + 1]);
            }
        }
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }

    private void writeToFile(String fileName, String reportString) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, false))) {
            bufferedWriter.write(reportString);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + fileName, e);
        }
    }

    private String[] readFile(String fileName) {
        StringBuilder fileDataString = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String value = reader.readLine();
            while (value != null) {
                fileDataString.append(value).append(SPACE);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fileName, e);
        }
        return fileDataString.toString().split(REGEX);
    }
}
