package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String REGEX_RETREAT = "\r";
    private static final String REGEX_NEW_LINE = "\n";
    private static final String CSV_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String result = readFromFile(fromFileName);
        writeToFile(result, toFileName);
    }

    private String readFromFile(String fromFileName) {
        File fileFromRead = new File(fromFileName);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFromRead))) {
            for (int i = 0; i < fileFromRead.length(); i++) {
                sb.append((char) bufferedReader.read());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }

        String informationFileReadCheck = sb.toString();
        String[] informationFileRead = informationFileReadCheck.replace(REGEX_RETREAT, "")
                .split(REGEX_NEW_LINE);

        int supply = 0;
        int buy = 0;

        for (int i = 0; i < informationFileRead.length; i++) {
            String[] splitWords = informationFileRead[i].split(CSV_SEPARATOR);
            if (splitWords[OPERATION_INDEX].equals(SUPPLY)) {
                supply += Integer.parseInt(splitWords[AMOUNT_INDEX]);
            } else if (splitWords[OPERATION_INDEX].equals(BUY)) {
                buy += Integer.parseInt(splitWords[AMOUNT_INDEX]);
            }
        }

        StringBuilder sbResult = new StringBuilder();
        sbResult.append(SUPPLY).append(CSV_SEPARATOR).append(supply)
                .append(System.lineSeparator())
                .append(BUY).append(CSV_SEPARATOR).append(buy)
                .append(System.lineSeparator())
                .append(RESULT).append(CSV_SEPARATOR).append(supply - buy);

        return sbResult.toString();
    }

    private void writeToFile(String result, String toFileName) {
        File fileToWrite = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }

}
