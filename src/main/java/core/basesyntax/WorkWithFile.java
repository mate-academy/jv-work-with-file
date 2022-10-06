package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_NAME_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY_STRING = "supply";
    private static final String BUY_STRING = "buy";
    private static final String RESULT_STRING = "result";
    private static final String COMA_STRING = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataForWrite = getWorkingData(fromFileName);
        File outputFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            bufferedWriter.write(dataForWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }

    private String[] readFile(String fileName) {
        File inputFile = new File(fileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private String getWorkingData(String fileName) {
        String[] dataFromFile = readFile(fileName);
        int countSupply = 0;
        int countBuy = 0;
        for (String data : dataFromFile) {
            String[] dataInLineOfFile = data.split(COMA_STRING);
            if (dataInLineOfFile[OPERATION_NAME_INDEX].equals(SUPPLY_STRING)) {
                countSupply += Integer.parseInt(dataInLineOfFile[AMOUNT_INDEX]);
            }
            if (dataInLineOfFile[OPERATION_NAME_INDEX].equals(BUY_STRING)) {
                countBuy += Integer.parseInt(dataInLineOfFile[AMOUNT_INDEX]);
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY_STRING).append(COMA_STRING)
                .append(Integer.toString(countSupply)).append(System.lineSeparator())
                .append(BUY_STRING).append(COMA_STRING)
                .append(Integer.toString(countBuy)).append(System.lineSeparator())
                .append(RESULT_STRING).append(COMA_STRING).append(countSupply - countBuy);
        return builder.toString();
    }
}
