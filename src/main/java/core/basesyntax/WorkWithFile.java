
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

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            String fileContent = readFile(fromFileName);
            String[] results = processFileContent(fileContent);
            writeToFile(toFileName, results);
        } catch (IOException e) {
            throw new RuntimeException("Error processing files", e);
        }
    }

    private String readFile(String fileName) throws IOException {
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            return stringBuilder.toString();
        }
    }

    private String[] processFileContent(String fileContent) {
        int resultSupply = 0;
        int resultBuy = 0;

        String[] strings = fileContent.split("\\s+");
        for (String string : strings) {
            String[] splits = string.split(SEPARATOR);
            if (splits[OPERATION_TYPE].equals("supply")) {
                resultSupply += Integer.parseInt(splits[AMOUNT]);
            } else if (splits[OPERATION_TYPE].equals("buy")) {
                resultBuy += Integer.parseInt(splits[AMOUNT]);
            }
        }

        return new String[]{
                "supply" + SEPARATOR + resultSupply + System.lineSeparator(),
                "buy" + SEPARATOR + resultBuy + System.lineSeparator(),
                "result" + SEPARATOR + (resultSupply - resultBuy) + System.lineSeparator()
        };
    }

    private void writeToFile(String fileName, String[] results) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            for (String result : results) {
                bufferedWriter.write(result);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }
}
