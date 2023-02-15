package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    
    public void getStatistic(String fromFileName, String toFileName) {
        String data = read(fromFileName);
        String report = getReport(data);
        writeData(toFileName, report);
    }

    private String read(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant read from file", e);
        }
        return stringBuilder.toString();
    }

    private String getReport(String data) {
        String[] array = data.split(System.lineSeparator());
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String item : array) {
            String[] itemInfo = item.split(SEPARATOR);
            if (itemInfo[0].equals(SUPPLY)) {
                supplyAmount += Integer.parseInt(itemInfo[1]);
            }
            if (itemInfo[0].equals(BUY)) {
                buyAmount += Integer.parseInt(itemInfo[1]);
            }
        }
        int resultAmount = supplyAmount - buyAmount;
        StringBuilder result = new StringBuilder();
        result.append(SUPPLY).append(SEPARATOR).append(supplyAmount).append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buyAmount).append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(resultAmount);
        return result.toString();
    }

    private void writeData(String toFileName, String text) {
        File file = new File(toFileName);
        try (FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Cant write text to file" + toFileName, e);
        }
    }
}
