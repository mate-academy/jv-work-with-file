package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int TYPE_OF_OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

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
            String line = bufferedReader.readLine();
            while (line != null) {
                reportBuilder.append(line).append(" ");
                line = bufferedReader.readLine();
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
            if (lines[TYPE_OF_OPERATION_INDEX].equals("buy")) {
                resultBuy += Integer.parseInt(lines[AMOUNT_INDEX]);
            } else {
                resultSupply += Integer.parseInt(lines[AMOUNT_INDEX]);
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
