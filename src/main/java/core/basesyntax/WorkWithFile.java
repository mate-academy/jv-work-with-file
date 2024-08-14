package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY_STRING = "supply";
    public static final String SEPARATION_PLACE = ",";
    public static final String BUY_STRING = "buy";
    public static final String RESULT_STRING = "result";
    private static final int VALUE_INDEX = 1;
    private static final int NAME_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFileContent(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String readFileContent(String fromFileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Cannot find the file: " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file: " + fromFileName, e);
        }
        return content.toString();
    }

    private String generateReport(String string) {
        int totalSupply = 0;
        int totalBuy = 0;
        String[] lines = string.split(System.lineSeparator());
        for (String item : lines) {
            String[] array = item.split(SEPARATION_PLACE);
            if (array[NAME_INDEX].equals(SUPPLY_STRING)) {
                totalSupply += Integer.parseInt(array[VALUE_INDEX]);
            } else if (array[NAME_INDEX].equals(BUY_STRING)) {
                totalBuy += Integer.parseInt(array[VALUE_INDEX]);
            }
        }

        StringBuilder reportBuilder = new StringBuilder()
                .append(SUPPLY_STRING).append(SEPARATION_PLACE).append(totalSupply)
                .append(System.lineSeparator()).append(BUY_STRING).append(SEPARATION_PLACE)
                .append(totalBuy).append(System.lineSeparator()).append(RESULT_STRING)
                .append(SEPARATION_PLACE).append(totalSupply - totalBuy)
                .append(System.lineSeparator());

        return reportBuilder.toString();
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file: " + fileName, e);
        }
    }
}
