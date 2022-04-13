package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final int CONDITION_FOR_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(toFileName, readFile(fromFileName));
    }

    private List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();
        String line;
        try (FileReader fileFrom = new FileReader(fileName)) {
            BufferedReader bufferedReader = new BufferedReader(fileFrom);
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return lines;
    }

    private void writeFile(String fileName, List<String> lines) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(getReport(lines));
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }

    public String getReport(List<String> lines) {
        int supplyOperation = 0;
        int buyOperation = 0;
        for (String line : lines) {
            String[] splittedLine = line.split(",");
            if (splittedLine [CONDITION_FOR_INDEX].equals("buy")) {
                buyOperation += Integer.parseInt(splittedLine [QUANTITY_INDEX]);
            }
            if (splittedLine [CONDITION_FOR_INDEX].equals("supply")) {
                supplyOperation += Integer.parseInt(splittedLine [QUANTITY_INDEX]);
            }
        }
        StringBuilder reportBuilder = new StringBuilder();
        return reportBuilder.append("supply,").append(supplyOperation)
                .append(System.lineSeparator())
                .append("buy,").append(buyOperation).append(System.lineSeparator())
                .append("result,").append(supplyOperation - buyOperation).toString();
    }
}
