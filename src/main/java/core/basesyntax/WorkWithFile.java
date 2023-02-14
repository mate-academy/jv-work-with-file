package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, reportFromData(readFromFile(fromFileName)));
    }

    public String readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File has not found" + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fileName, e);
        }
        return stringBuilder.toString();
    }

    public void writeToFile(String fileName, String report) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + fileName, e);
        }
    }

    private String reportFromData(String dataFrom) {
        String[] dataStrings = dataFrom.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String string : dataStrings) {
            String[] value = string.split(",");
            if (value[OPERATION_TYPE_INDEX].equals("supply")) {
                supply += Integer.parseInt(value[AMOUNT_INDEX]);
            } else {
                buy += Integer.parseInt(value[AMOUNT_INDEX]);
            }
        }
        int result = supply - buy;
        return "supply,"
                + supply
                + System.lineSeparator()
                + "buy,"
                + buy
                + System.lineSeparator()
                + "result,"
                + (result);
    }
}
