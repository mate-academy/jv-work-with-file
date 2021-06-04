package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    private String readFromFile(String fromFileName) {

        StringBuilder stringBuilder = new StringBuilder();
        File fileToRead = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead))) {
            String line = bufferedReader.readLine();
            stringBuilder.append(line);
            while (line != null) {
                line = bufferedReader.readLine();
                stringBuilder.append(" ").append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading the file");
        }
        String[] resultingString = stringBuilder.toString().split(" ");
        int buySum = 0;
        int supplySum = 0;
        String[] stringSplit;
        for (String line : resultingString) {
            stringSplit = line.split(",");
            if (stringSplit[OPERATION_INDEX].equals(BUY)) {
                buySum += Integer.parseInt(stringSplit[AMOUNT_INDEX]);
            } else if (stringSplit[OPERATION_INDEX].equals(SUPPLY)) {
                supplySum += Integer.parseInt(stringSplit[AMOUNT_INDEX]);
            }
        }
        int resultSum = supplySum - buySum;
        return "supply," + supplySum + System.lineSeparator()
                + "buy," + buySum + System.lineSeparator()
                + "result, " + resultSum;
    }

    private void writeToFile(String toFileName, String report) {
        File fileToWrite = new File(toFileName);

        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(fileToWrite, true))) {
            bufferedWriter.write(report.replaceAll(" ",""));
        } catch (IOException e) {
            throw new RuntimeException("Error while writing into the file", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String report = readFromFile(fromFileName);
        writeToFile(toFileName, report);
    }
}
