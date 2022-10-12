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
        String dataFromFile = readFromFile(fromFileName);
        String dataToFile = createReport(dataFromFile);
        writeToFile(toFileName, dataToFile);
    }

    public String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        File fromFile = new File(fromFileName);
        String stringFromFile;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            stringFromFile = reader.readLine();
            while (stringFromFile != null) {
                builder.append(stringFromFile);
                builder.append(",");
                stringFromFile = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return builder.toString();
    }

    public void writeToFile(String toFileName, String inputData) {
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(inputData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    public String createReport(String dataFromFile) {
        StringBuilder builder = new StringBuilder();
        int supply = 0;
        int buy = 0;
        int result;
        String[] arrayOfData;
        arrayOfData = dataFromFile.split(",");
        for (int i = 0; i < arrayOfData.length; i++) {
            if (arrayOfData[i].equals(SUPPLY)) {
                supply += Integer.parseInt(arrayOfData[i + 1]);
            }
            if (arrayOfData[i].equals(BUY)) {
                buy += Integer.parseInt(arrayOfData[i + 1]);
            }
        }
        result = supply - buy;
        builder.append(SUPPLY);
        builder.append(",");
        builder.append(supply);
        builder.append(System.lineSeparator());
        builder.append(BUY);
        builder.append(",");
        builder.append(buy);
        builder.append(System.lineSeparator());
        builder.append(RESULT);
        builder.append(",");
        builder.append(result);
        return builder.toString();
    }
}
