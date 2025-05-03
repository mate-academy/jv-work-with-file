package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int POSITION_NAME = 0;
    private static final int POSITION_SUM = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String order = makeReport(data);
        writeToFile(order, toFileName);
    }

    private String readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder strBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                strBuilder.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file " + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
        return strBuilder.toString();
    }

    private String makeReport(String data) {
        StringBuilder stringBuilder = new StringBuilder();
        int supplyTotal = 0;
        int buyTotal = 0;
        int result;

        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] dataArray = line.split(",");
            if (dataArray[POSITION_NAME].equals("supply")) {
                supplyTotal += Integer.parseInt(dataArray[POSITION_SUM]);
            } else {
                buyTotal += Integer.parseInt(dataArray[POSITION_SUM]);
            }
        }
        result = supplyTotal - buyTotal;
        stringBuilder.append("supply,").append(supplyTotal).append(System.lineSeparator())
                .append("buy,").append(buyTotal).append(System.lineSeparator())
                .append("result,").append(result);
        return stringBuilder.toString();
    }

    private void writeToFile(String data, String fileName) {
        File toFile = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileName, e);
        }
    }
}
