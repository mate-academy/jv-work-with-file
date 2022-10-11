package core.basesyntax;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMMOUNT = 1;
    private int supply;
    private int buy;
    private int result;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        writeToFile(toFileName, stringBuilder(supply, buy, result));
    }

    public void readFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        String str;
        String[] strArray;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            str = reader.readLine();
            while (str != null) {
                strArray = str.split(",");
                if (strArray[OPERATION_TYPE].equals("supply")) {
                    supply += Integer.parseInt(strArray[AMMOUNT]);
                }
                if (strArray[OPERATION_TYPE].equals("buy")) {
                    buy += Integer.parseInt(strArray[AMMOUNT]);
                }
                str = reader.readLine();
            }
            result = supply - buy;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    public void writeToFile(String toFileName, String inputData) {
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(inputData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    public String stringBuilder(int suplly, int buy, int result) {
        StringBuilder builder = new StringBuilder();
        builder.append("supply");
        builder.append(",");
        builder.append(supply);
        builder.append(System.lineSeparator());
        builder.append("buy");
        builder.append(",");
        builder.append(buy);
        builder.append(System.lineSeparator());
        builder.append("result");
        builder.append(",");
        builder.append(result);
        return builder.toString();
    }
}
