package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String VALUE_DELIMITER = ",";
    private static final String SUPPLY_DATA = "supply";
    private static final String BUY_DATA = "buy";
    private static final String RESULT_DATA = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = null;
        String[] splitWords = this.readFile(fromFileName).toString().split(" ");
        int supply = 0;
        int buy = 0;
        int result = 0;
        for (String temp : splitWords) {
            String[] splitValues = temp.split(VALUE_DELIMITER);
            if (splitValues[0].equals(SUPPLY_DATA)) {
                supply += Integer.parseInt(splitValues[1]);
            } else if (splitValues[0].equals(BUY_DATA)) {
                buy += Integer.parseInt(splitValues[1]);
            }
        }
        result = supply - buy;
        this.writeFile(result, supply, buy, toFileName);
    }

    private StringBuilder readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String readLine = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            readLine = bufferedReader.readLine();
            if (readLine == null) {
                System.out.println("File is empty");
            }
            while (readLine != null) {
                stringBuilder.append(readLine).append(" ");
                readLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }

    private void writeFile(int result, int supply, int buy, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(SUPPLY_DATA + VALUE_DELIMITER + supply + System.lineSeparator()
                    + BUY_DATA + VALUE_DELIMITER + buy + System.lineSeparator()
                    + RESULT_DATA + VALUE_DELIMITER + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
