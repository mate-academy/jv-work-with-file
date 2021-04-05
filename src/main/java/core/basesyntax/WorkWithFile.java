package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

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
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(",");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't find the file", e);
        }
        String[] datesFromFile = stringBuilder.toString().split(",");
        for (int i = 0; i < datesFromFile.length; i++) {
            if (datesFromFile[i].equals(SUPPLY)) {
                supplySummary += Integer.parseInt(datesFromFile[i + 1]);
            } else if (datesFromFile[i].equals(BUY)) {
                buySummary += Integer.parseInt(datesFromFile[i + 1]);
            }
        }
        operationResult = supplySummary - buySummary;
        try {
            FileWriter fileWriter = new FileWriter(resultFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(SUPPLY + "," + supplySummary);
            bufferedWriter.newLine();
            bufferedWriter.write(BUY + "," + buySummary);
            bufferedWriter.newLine();
            bufferedWriter.write(RESULT + "," + operationResult);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
