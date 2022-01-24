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
        String data = readFile(fromFileName);
        String report = countData(data);
        writeDataToFile(toFileName, report);
    }

    public String readFile(String fileName) {
        File file = new File(fileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    public String countData(String data) {
        String[] splitArr = data.split(",");
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < splitArr.length; i += 2) {
            if (splitArr[i].contains(SUPPLY)) {
                supply += Integer.parseInt(splitArr[i + 1]);
            } else if (splitArr[i].contains(BUY)) {
                buy += Integer.parseInt(splitArr[i + 1]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(",").append(supply)
                .append(System.lineSeparator()).append(BUY).append(",")
                .append(buy).append(System.lineSeparator()).append(RESULT)
                .append(",").append(supply - buy);
        return stringBuilder.toString();
    }

    public void writeDataToFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
