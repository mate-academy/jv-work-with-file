package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {
    static final int INDEX_OF_WORD = 0;
    static final int INDEX_OF_NUMBER = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(fromFileName, toFileName);
    }

    public ArrayList<String[]> getListFromFile(File file) {
        String line;
        ArrayList<String[]> lines = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
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
                supply += Integer.parseInt(list[INDEX_OF_NUMBER].trim());
            }
            if (list[INDEX_OF_WORD].equals("buy")) {
                buy += Integer.parseInt(list[INDEX_OF_NUMBER].trim());
            }
        }
        stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        return stringBuilder.toString();
    }

    public void writeToFile(String from, String to) {
        File file = new File(from);
        ArrayList<String[]> lines = getListFromFile(file);
        String stringList = getCalculatedResult(lines);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(to));
            bufferedWriter.write(stringList);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Exception 2", e);
        }
    }
}
