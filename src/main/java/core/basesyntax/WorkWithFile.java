package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static String fileLine;
    private static String line;
    private static String report;
    private StringBuilder readerBuilder = new StringBuilder();
    private StringBuilder reportBuilder = new StringBuilder();
    private int totalAmount;
    private int amountNumber;
    private int totalSupply;
    private int totalBuy;
    private String operation;
    private String[] separatedItems;

    public void getStatistic(String fromFileName, String toFileName) {
        line = readFromFile(fromFileName);
        report = createReport(line);
        writeToFile(toFileName, report);
    }

    public String readFromFile(String fromFileName) {
        File incomingFile = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(incomingFile))) {
            fileLine = reader.readLine();
            while (fileLine != null) {
                readerBuilder.append(fileLine).append(",");
                fileLine = reader.readLine();
            }
            return readerBuilder.toString();
        } catch (IOException b) {
            throw new RuntimeException("Cannot read from file" + b.getMessage());
        }
    }

    public String createReport(String line) {
        totalSupply = 0;
        totalBuy = 0;
        separatedItems = line.split(",");
        for (int i = 0; i < separatedItems.length; i += 2) {
            operation = separatedItems[i].trim();
            amountNumber = Integer.parseInt(separatedItems[i + 1].trim());
            if (operation.equals("supply")) {
                totalSupply += amountNumber;
            } else if (operation.equals("buy")) {
                totalBuy += amountNumber;
            }
        }
        totalAmount = totalSupply - totalBuy;
        return reportBuilder.append("supply,")
                .append(totalSupply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(totalBuy)
                .append(System.lineSeparator())
                .append("result,")
                .append(totalAmount)
                .toString();
    }

    public void writeToFile(String toFileName, String result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(result);
            reportBuilder.setLength(0);
            readerBuilder.setLength(0);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file" + e.getMessage());
        }
    }

}
