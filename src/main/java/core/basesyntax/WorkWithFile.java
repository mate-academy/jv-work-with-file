
package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFile(fromFileName);
        String[] results = processFileContent(fileContent);
        writeToFile(toFileName, results);
    }

    private String readFile(String fileName) {
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder readFromFileName = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                readFromFileName.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            return readFromFileName.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file: " + fileName, e);
        }
    }

    private String[] processFileContent(String fileContent) {
        int operationTypeSupply = 0;
        int operationTypeBuy = 0;

        String[] strings = fileContent.split("\\s+");
        for (String string : strings) {
            String[] splits = string.split(SEPARATOR);
            if (splits[OPERATION_TYPE].equals(SUPPLY)) {
                operationTypeSupply += Integer.parseInt(splits[AMOUNT]);
            } else if (splits[OPERATION_TYPE].equals(BUY)) {
                operationTypeBuy += Integer.parseInt(splits[AMOUNT]);
            }
        }

        return new String[]{
                SUPPLY + SEPARATOR + operationTypeSupply + System.lineSeparator(),
                BUY + SEPARATOR + operationTypeBuy + System.lineSeparator(),
                RESULT + SEPARATOR + (operationTypeSupply - operationTypeBuy)
                        + System.lineSeparator()
        };
    }

    private void writeToFile(String fileName, String[] results) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            for (String result : results) {
                bufferedWriter.write(result);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file: " + fileName, e);
        }
    }
}
