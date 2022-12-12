package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {
    static final int INDEX_OF_WORD = 0;
    static final int INDEX_OF_NUMBER = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        ArrayList<String[]> lines = getListFromFile(fromFileName);
        String report = getCalculatedResult(lines);
        writeToFile(report, toFileName);
    }

    public ArrayList<String[]> getListFromFile(String froFileName) {
        String line;
        ArrayList<String[]> lines = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(froFileName));
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line.split(","));
            }
            return lines;
        } catch (IOException e) {
            throw new RuntimeException("Exception 1", e);
        }
    }

    public String getCalculatedResult(ArrayList<String[]> lines) {
        int supply = 0;
        int buy = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (String[] list: lines) {
            if (list[INDEX_OF_WORD].equals("supply")) {
                supply += Integer.parseInt(list[INDEX_OF_NUMBER]);
            }
            if (list[INDEX_OF_WORD].equals("buy")) {
                buy += Integer.parseInt(list[INDEX_OF_NUMBER]);
            }
        }
        stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        return stringBuilder.toString();
    }

    public void writeToFile(String report, String toFileName) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(report);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Exception 2", e);
        }
    }
}
