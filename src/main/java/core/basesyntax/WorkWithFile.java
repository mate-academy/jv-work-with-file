package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] strings = readFromFile(fromFileName);
        String report = createReport(strings);
        writeToFile(report, toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int value = bufferedReader.read();
            while (value != -1) {
                builder.append((char) value);
                value = bufferedReader.read();
            }
            String wordWithoutEnter = builder.toString().replaceAll("\n", ",");
            return wordWithoutEnter.split(",");
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    private String createReport(String[] splitArray) {
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < splitArray.length; i = i + 2) {
            if (splitArray[i].equals("supply")) {
                supply += Integer.parseInt(splitArray[i + 1]);
            } else if (splitArray[i].equals("buy")) {
                buy += Integer.parseInt(splitArray[i + 1]);
            }
        }
        return "supply," + supply + "\n" + "buy," + buy
                + "\n" + "result," + (supply - buy);
    }

    public void writeToFile(String resultReport, String toFileName) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(resultReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
