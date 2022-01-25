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
    private static final String COMA = ",";
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        int[] arrayValues = countData(data);
        String report = writeReport(arrayValues);
        writeDataToFile(toFileName, report);
    }

    public String readFile(String fileName) {
        File file = new File(fileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(COMA);
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    public int[] countData(String data) {
        String[] splitArr = data.split(COMA);
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < splitArr.length; i += 2) {
            if (splitArr[i].contains(SUPPLY)) {
                supply += Integer.parseInt(splitArr[i + 1]);
            } else if (splitArr[i].contains(BUY)) {
                buy += Integer.parseInt(splitArr[i + 1]);
            }
        }
        int[] array = {supply, buy};
        return array;
    }

    public String writeReport(int[] arrayOfData) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(COMA).append(arrayOfData[SUPPLY_INDEX])
                .append(System.lineSeparator()).append(BUY).append(COMA)
                .append(arrayOfData[BUY_INDEX]).append(System.lineSeparator()).append(RESULT)
                .append(COMA).append(arrayOfData[SUPPLY_INDEX] - arrayOfData[BUY_INDEX]);
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
