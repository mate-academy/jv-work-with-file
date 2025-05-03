package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String [] arrayFromFile = readFromFile(fromFileName);
        String report = createReport(arrayFromFile);
        writeToFile(report, toFileName);
    }

    private String createReport(String [] arrayResultsFromFile) {
        StringBuilder resultBuilder = new StringBuilder();
        int sumSupply = 0;
        int sumBuy = 0;

        for (String arrayResult: arrayResultsFromFile) {
            String [] arrays = arrayResult.split(",");
            if (arrays[0].equals("supply")) {
                sumSupply += Integer.parseInt(arrays[1]);
            } else if (arrays[0].equals("buy")) {
                sumBuy += Integer.parseInt(arrays[1]);
            }
        }
        int getResult = sumSupply - sumBuy;
        resultBuilder.append("supply").append(",").append(sumSupply).append(System.lineSeparator());
        resultBuilder.append("buy").append(",").append(sumBuy).append(System.lineSeparator());
        resultBuilder.append("result").append(",").append(getResult).append(System.lineSeparator());
        return resultBuilder.toString();
    }

    private String [] readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(";");
                value = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        String getFromFile = stringBuilder.toString();
        return getFromFile.split(";");
    }

    private void writeToFile(String result, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }
}

