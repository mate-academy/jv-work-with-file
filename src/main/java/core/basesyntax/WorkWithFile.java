package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            String[] array = reader(fromFileName);
            String[] reportArray = reportGenerator(array);
            for (String reportLine : reportArray) {
                bufferedWriter.write(reportLine);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can`t find a file", e);
        } catch (IOException e) {
            throw new RuntimeException("Line can`t be written", e);
        }
    }

    private String[] reader(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String value;
            value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString().split(" ");
        } catch (IOException e) {
            throw new RuntimeException("Line can`t be read", e);
        }
    }

    private String[] reportGenerator(String[] readFile) {
        int supply = 0;
        int buy = 0;
        for (String position: readFile) {
            int amount = Integer.parseInt(position.substring(position.indexOf(',') + 1));
            if (position.charAt(0) == 's') {
                supply += amount;
            } else {
                buy += amount;
            }
        }
        int res = supply - buy;
        String supplyString = "supply," + supply + System.lineSeparator();
        String buyString = "buy," + buy + System.lineSeparator();
        String result = "result," + res;
        return new String[]{supplyString, buyString, result};
    }
}
