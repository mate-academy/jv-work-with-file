package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    public static final String FIRST_KEYWORD = "supply";
    public static final String SECOND_KEYWORD = "buy";
    public static final String RESULT_KEYWORD = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeOutputFile(toFileName, getOutputString(fromFileName));
    }

    private String getOutputString(String fromFileName) {
        StringBuilder outputString = new StringBuilder();
        try (BufferedReader inputFileBuffRdr = new BufferedReader(new FileReader(fromFileName))) {
            String inputLine;
            int[] countedBalances = {0, 0};
            while ((inputLine = inputFileBuffRdr.readLine()) != null) {
                String[] splittedInputLine = inputLine.split(",");
                if (splittedInputLine[0].equals(FIRST_KEYWORD)) {
                    countedBalances[0] += Integer.parseInt(splittedInputLine[1]);
                } else if (splittedInputLine[0].equals(SECOND_KEYWORD)) {
                    countedBalances[1] += Integer.parseInt(splittedInputLine[1]);
                } else {
                    throw new Exception("The keywords are not recognized. "
                            + "Please correct the inout data file to proceed");
                }
            }
            outputString.append(FIRST_KEYWORD).append(",").append(countedBalances[0])
                    .append(System.lineSeparator())
                    .append(SECOND_KEYWORD).append(",").append(countedBalances[1])
                    .append(System.lineSeparator())
                    .append(RESULT_KEYWORD).append(",")
                    .append(countedBalances[0] - countedBalances[1])
                    .append(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Cannot read from file", e);
        } catch (Exception e) {
            throw new RuntimeException("Unrecognized keywords.", e);
        }
        return outputString.toString();
    }

    private void writeOutputFile(String toFileName, String outputString) {
        try {
            Files.write(Paths.get(toFileName), outputString.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write results to file.", e);
        }
    }
}
