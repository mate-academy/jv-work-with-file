package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String createReport(String dataFromFile) {
        String[] split = dataFromFile.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        int result;
        for (String word : split) {
            String[] arrayValues = word.split(",");
            String stringValue = arrayValues[1];
            int value = Integer.parseInt(stringValue);
            if (word.startsWith("s")) {
                supply += value;
            } else if (word.startsWith("b")) {
                buy += value;
            }
        }
        result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply").append(",").append(supply).
                append(System.lineSeparator()).append("buy").append(",")
                .append(buy).append(System.lineSeparator()).append("result")
                .append(",").append(result);
        return stringBuilder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            bufferedWriter.write(report);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close BufferedWriter", e);
                }
            }
        }
    }

    private String readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                value = value.toLowerCase();
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("No such file");
        }
        return stringBuilder.toString();
    }
}
