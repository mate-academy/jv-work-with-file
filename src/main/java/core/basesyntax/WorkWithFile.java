package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final String SPACE = " ";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readData(fromFileName);
        String report = createReport(data);
        writeData(report, toFileName);
    }

    private String readData(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder data = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null && !line.isEmpty()) {
                data.append(line).append(SPACE);
                line = bufferedReader.readLine();
            }
            return data.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File doesn't exist " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file" + fromFileName, e);
        }
    }

    private void writeData(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File doesn't exist" + toFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }

    private String createReport(String data) {
        int countSupply = 0;
        int countBuy = 0;
        StringBuilder result = new StringBuilder();
        String[] lines = data.split(SPACE);
        for (String line : lines) {
            String[] spliter = line.split(DELIMITER);
            if (spliter[OPERATION_INDEX].equals("supply")) {
                countSupply += Integer.parseInt(spliter[AMOUNT_INDEX]);
            } else if (spliter[OPERATION_INDEX].equals("buy")) {
                countBuy += Integer.parseInt(spliter[AMOUNT_INDEX]);
            }
        }
        int countedResult = countSupply - countBuy;
        result.append(SUPPLY).append(DELIMITER).append(countSupply).append(System.lineSeparator());
        result.append(BUY).append(DELIMITER).append(countBuy).append(System.lineSeparator());
        result.append(RESULT).append(DELIMITER).append(countedResult);
        return result.toString();
    }
}
