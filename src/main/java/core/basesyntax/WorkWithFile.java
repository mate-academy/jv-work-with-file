package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = readFromFileName(fromFileName);
        String result = createResult(stringBuilder.toString().split("\n"));
        System.out.println(result);
        writeToFileName(toFileName, result);
    }

    private StringBuilder readFromFileName(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int value = bufferedReader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = bufferedReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        return stringBuilder;
    }

    private String createResult(String[] stringArr) {
        int supply = 0;
        int buy = 0;

        for (int i = 0; i < stringArr.length; i++) {
            if (stringArr[i].contains("supply")) {
                supply += Integer.parseInt(stringArr[i].substring(7));
            } else {
                buy += Integer.parseInt(stringArr[i].substring(4));
                ;
            }
        }

        String result = "supply," + supply
                + "\nbuy," + buy
                + "\nresult," + (supply - buy);
        return result;
    }

    private void writeToFileName(String toFileName, String result) {
        File file2 = new File(toFileName);
        try (BufferedWriter bufferedWriter1 = new BufferedWriter(new FileWriter(file2, true))) {
            bufferedWriter1.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}