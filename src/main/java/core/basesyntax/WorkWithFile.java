package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private final List<String> allLines = new ArrayList<>();

    public void getStatistic(String fromFileName, String toFileName) {
        File file1 = new File(fromFileName);
        File file2 = new File(toFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file1));
            String line;
            while ((line = reader.readLine()) != null) {
                allLines.add(line);
            }
            reader.close();
            String[] reportString = {"supply", "buy", "result"};
            for (int j = 0; j < reportString.length; j++) {
                int[] reportInt = new int[reportString.length];
                for (String allLine : allLines) {
                    String[] array = allLine.split(",");
                    if (array[0].equals(reportString[0])) {
                        reportInt[0] += Integer.parseInt(array[1]);
                    } else if (array[0].equals(reportString[1])) {
                        reportInt[1] += Integer.parseInt(array[1]);
                    }
                    reportInt[2] = reportInt[0] - reportInt[1];
                }
                stringBuilder.append(reportString[j]).append(",").append(reportInt[j])
                .append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data to file", e);
        }
        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file2));
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}


