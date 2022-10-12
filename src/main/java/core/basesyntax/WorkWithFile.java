package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMMOUNT = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private int supply;
    private int buy;
    private int result;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        writeToFile(toFileName, stringBuilder(supply, buy, result));
    }

    public void readFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        String stringFromFile;
        String[] arrayOfData;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            stringFromFile = reader.readLine();
            while (stringFromFile != null) {
                arrayOfData = stringFromFile.split(",");
                if (arrayOfData[OPERATION_TYPE].equals(SUPPLY)) {
                    supply += Integer.parseInt(arrayOfData[AMMOUNT]);
                }
                if (arrayOfData[OPERATION_TYPE].equals(BUY)) {
                    buy += Integer.parseInt(arrayOfData[AMMOUNT]);
                }
                stringFromFile = reader.readLine();
            }
            result = supply - buy;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }

    public void writeToFile(String toFileName, String inputData) {
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(inputData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    public String stringBuilder(int suplly, int buy, int result) {
        StringBuilder builder = new StringBuilder();
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
