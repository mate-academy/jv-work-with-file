package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String WHITE_GAP = " ";
    private static final String COMMA = ",";
    private static final String RESULT = "result";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int PARSED_INDEX_0 = 0;
    private static final int PARSED_INDEX_1 = 1;
    private static final int COUNTER_INIT_VALUE = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] arrFromFile = getArrFromFile(fromFileName);
        String[] arrWithResult = getArrWithResult(arrFromFile);
        putResultToFile(toFileName, arrWithResult);
    }

    private void putResultToFile(String toFileName, String[] arrWithResult) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (String s : arrWithResult) {
                bufferedWriter.write(s + LINE_SEPARATOR);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName);
        }
    }

    private String[] getArrWithResult(String[] arrFromFile) {
        int sumOfSupply = COUNTER_INIT_VALUE;
        int sumOfBy = COUNTER_INIT_VALUE;
        for (String s : arrFromFile) {
            String[] parsedS = s.split(COMMA);
            if (parsedS[PARSED_INDEX_0].equals(SUPPLY)) {
                sumOfSupply += Integer.parseInt(parsedS[PARSED_INDEX_1]);
            } else {
                sumOfBy += Integer.parseInt(parsedS[PARSED_INDEX_1]);
            }
        }
        return new String[]{SUPPLY + COMMA + sumOfSupply,
                BUY + COMMA + sumOfBy,
                RESULT + COMMA + (sumOfSupply - sumOfBy)};
    }

    private String[] getArrFromFile(String fromFileName) {
        try (FileReader fileReader = new FileReader(fromFileName)) {
            StringBuilder enteredData = new StringBuilder();
            BufferedReader reader = new BufferedReader(fileReader);
            String lineFromFile;
            while ((lineFromFile = reader.readLine()) != null) {
                enteredData.append(lineFromFile).append(WHITE_GAP);
            }
            String resData = enteredData.toString().trim();
            return resData.split(WHITE_GAP);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file " + fromFileName);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName);
        }
    }
}
