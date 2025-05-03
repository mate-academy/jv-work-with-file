package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String stringBuilder = readFromFileName(fromFileName);
        String result = createResult(stringBuilder.toString());
        System.out.println(result);
        writeToFileName(toFileName, result);
    }

    private String readFromFileName(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String createResult(String data) {
        String[] stringArr = data.split("\\W+");
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < stringArr.length; i += 2) {
            if (stringArr[i].contains("supply")) {
                supply += Integer.parseInt(stringArr[i + 1]);
            } else {
                buy += Integer.parseInt(stringArr[i + 1]);
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("supply,").append(supply).append("\nbuy,").append(buy)
                .append("\nresult,").append(supply - buy);
        return result.toString();
    }

    private void writeToFileName(String toFileName, String result) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
