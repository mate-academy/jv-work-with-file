package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int TYPE_OF_OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String COMMA = ",";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String readAndCalculationDataFromFile = getCalculation(readFile(fromFileName));
        writeResultToFile(readAndCalculationDataFromFile, toFileName);
    }

    private String readFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder resultOfReading = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                resultOfReading.append(value).append(" ");
                value = bufferedReader.readLine();
            }
            return resultOfReading.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file" + fromFileName, e);
        }
    }

    private String getCalculation(String infoFromFile) {
        StringBuilder resultOfCalculation = new StringBuilder();
        String[] result = infoFromFile.split(" ");
        int totalSupply = 0;
        int totalBuy = 0;
        for (String item : result) {
            String[] infoItem = item.split(",");
            if (infoItem[TYPE_OF_OPERATION_INDEX].equals(SUPPLY)) {
                totalSupply += Integer.parseInt(infoItem[AMOUNT_INDEX]);
            }
            if (infoItem[TYPE_OF_OPERATION_INDEX].equals(BUY)) {
                totalBuy += Integer.parseInt(infoItem[AMOUNT_INDEX]);
            }
        }
        resultOfCalculation.append(SUPPLY + COMMA)
                .append(totalSupply)
                .append(System.lineSeparator())
                .append(BUY + COMMA)
                .append(totalBuy)
                .append(System.lineSeparator())
                .append(RESULT + COMMA)
                .append(totalSupply - totalBuy)
                .append(System.lineSeparator());
        return resultOfCalculation.toString();
    }

    private void writeResultToFile(String readFile, String toFileName) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(readFile);
        } catch (IOException e) {
            throw new RuntimeException("Can't find file " + toFileName, e);
        }
    }
}
