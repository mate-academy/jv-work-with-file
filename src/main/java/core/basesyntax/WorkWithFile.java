package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File informationFromFile = new File(fromFileName);
        File resultFile = new File(toFileName);
        int supplySummary = 0;
        int buySummary = 0;
        int operationResult = 0;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(informationFromFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()) {
                stringBuilder.append(bufferedReader.readLine()).append(",");
            }
        } catch (IOException e) {
            throw new RuntimeException("Some went wrong", e);
        }
        String[] datesFromFile = stringBuilder.toString().split(",");
        for (int i = 0; i < datesFromFile.length; i++) {
            if (datesFromFile[i].equals("supply")) {
                supplySummary += Integer.parseInt(datesFromFile[i + 1]);
            } else if (datesFromFile[i].equals("buy")) {
                buySummary += Integer.parseInt(datesFromFile[i + 1]);
            }
        }
        operationResult = supplySummary - buySummary;
        try {
            FileWriter fileWriter = new FileWriter(resultFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("supply," + supplySummary);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buySummary);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + operationResult);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Some went wrong", e);
        }
    }
}
