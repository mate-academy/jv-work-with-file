package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    public static final String dataSeparator = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = readFromFile(fromFileName);
        writeInFile(toFileName,stringBuilder);
    }

    private StringBuilder readFromFile(String readFile) {
        File fileIn = new File(readFile);
        StringBuilder argumentOne = new StringBuilder("supply");
        int argumentOneAmount = 0;
        StringBuilder argumentTwo = new StringBuilder("buy");
        int argumentTwoAmount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileIn))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                if (isValueContainsArgument(value,argumentOne)) {
                    argumentOneAmount += getArgumentAmount(value);
                }
                if (isValueContainsArgument(value,argumentTwo)) {
                    argumentTwoAmount += getArgumentAmount(value);
                }
                value = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + fileIn, e);
        }
        return new StringBuilder()
                .append(argumentOne)
                .append(dataSeparator)
                .append(argumentOneAmount)
                .append(System.lineSeparator())
                .append(argumentTwo)
                .append(dataSeparator)
                .append(argumentTwoAmount)
                .append(System.lineSeparator())
                .append("result")
                .append(dataSeparator)
                .append(argumentOneAmount - argumentTwoAmount);
    }

    private int getArgumentAmount(String value) {
        return Integer.parseInt(value
                .substring(value.indexOf(",") + 1,value.length()));
    }

    private boolean isValueContainsArgument(String value,StringBuilder argument) {
        return value.substring(0,value.indexOf(dataSeparator)).equals(argument.toString());
    }

    private void writeInFile(String fileWriteIn, StringBuilder valueToWrite) {
        File fileOut = new File(fileWriteIn);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileOut,true))) {
            bufferedWriter.write(valueToWrite.toString());
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to the file " + fileOut, e);
        }
    }
}
