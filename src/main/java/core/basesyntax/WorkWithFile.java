package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    public static final String DATA_SEPARATOR = ",";
    public static final String ARGUMENT_ONE = "supply";
    public static final String ARGUMENT_TWO = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = readFromFile(fromFileName);
        writeInFile(toFileName, stringBuilder.toString());
    }

    private StringBuilder readFromFile(String readFile) {
        File incomingFile = new File(readFile);
        int argumentOneAmount = 0;
        int argumentTwoAmount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(incomingFile))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                if (value.contains(ARGUMENT_ONE)) {
                    argumentOneAmount += getArgumentAmount(value);
                } else {
                    argumentTwoAmount += getArgumentAmount(value);
                }
                value = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + incomingFile, e);
        }
        return new StringBuilder()
                .append(ARGUMENT_ONE)
                .append(DATA_SEPARATOR)
                .append(argumentOneAmount)
                .append(System.lineSeparator())
                .append(ARGUMENT_TWO)
                .append(DATA_SEPARATOR)
                .append(argumentTwoAmount)
                .append(System.lineSeparator())
                .append("result")
                .append(DATA_SEPARATOR)
                .append(argumentOneAmount - argumentTwoAmount);
    }

    private int getArgumentAmount(String value) {
        return Integer.parseInt(value.substring(value
                .indexOf(DATA_SEPARATOR) + 1,value.length()));
    }

    private void writeInFile(String incomingFile, String incomingValue) {
        File exitFile = new File(incomingFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(exitFile))) {
            bufferedWriter.write(incomingValue.toString());
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to the file " + exitFile, e);
        }
    }
}
