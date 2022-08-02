package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String infoFromFile = readFromFile(fromFileName);
        String report = formStatistic(infoFromFile);
        writeToFile(report, toFileName);
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName + e);
        }
    }

    private String readFromFile(String fromFileName) {
        StringBuilder reportBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String message = bufferedReader.readLine();
            while (message != null) {
                reportBuilder.append(message).append(" ");
                message = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return reportBuilder.toString();
    }

    private String formStatistic(String data) {
        StringBuilder builder = new StringBuilder();
        String [] dataLine = data.split(" ");
        int resultSupply = 0;
        int resultBuy = 0;
        for (String record : dataLine) {
            String[] lines = record.split(",");
            if (lines[INDEX_ZERO].equals("buy")) {
                resultBuy += Integer.parseInt(lines[INDEX_ONE]);
            } else {
                resultSupply += Integer.parseInt(lines[INDEX_ONE]);
            }
        }
        int resultTotal = resultSupply - resultBuy;
        builder.append("supply").append(",").append(resultSupply)
                .append(System.lineSeparator()).append("buy").append(",")
                .append(resultBuy).append(System.lineSeparator()).append("result")
                .append(",").append(resultTotal);
        return builder.toString();
    }
}
