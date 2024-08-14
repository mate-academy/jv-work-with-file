package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int VALUE_INDEX = 1;
    private static final int NAME_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFileContent(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String readFileContent(String fromFileName) {
        File fileCopy = new File(fromFileName);
        StringBuilder content = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileCopy))) {
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
            String[] array = item.split(",");
            if (array[NAME_INDEX].equals("supply")) {
                totalSupply += Integer.parseInt(array[VALUE_INDEX]);
            } else {
                totalBuy += Integer.parseInt(array[VALUE_INDEX]);
            }
        }

        StringBuilder reportBuilder = new StringBuilder()
                .append("supply,").append(totalSupply).append(System.lineSeparator())
                .append("buy,").append(totalBuy).append(System.lineSeparator())
                .append("result,").append(totalSupply - totalBuy)
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
