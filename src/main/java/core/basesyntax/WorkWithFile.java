package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public static final String SPLIT_DELIMITER_SCV = ",";
    public static final String DATA_FIRST = "supply";
    public static final String DATA_SECOND = "buy";
    private static final String DATA_THIRD = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int resultSupply = 0;
        int resultBuy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String readOneLine = bufferedReader.readLine();
            while (readOneLine != null) {
                String [] readFile = readOneLine.split(SPLIT_DELIMITER_SCV);
                readOneLine = bufferedReader.readLine();
                int dataValue = Integer.parseInt(readFile[1]);
                if (readFile[0].equals(DATA_FIRST)) {
                    resultSupply += dataValue;
                }
                if (readFile[0].equals(DATA_SECOND)) {
                    resultBuy += dataValue;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read data from .csv file", e);
        }

        int resultWithSupplyAndBuy = resultSupply - resultBuy;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(DATA_FIRST
                    + SPLIT_DELIMITER_SCV
                    + resultSupply
                    + System.lineSeparator());
            bufferedWriter.write(DATA_SECOND
                    + SPLIT_DELIMITER_SCV
                    + resultBuy
                    + System.lineSeparator());
            bufferedWriter.write(DATA_THIRD
                    + SPLIT_DELIMITER_SCV
                    + resultWithSupplyAndBuy);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data from .csv file", e);
        }
    }
}
