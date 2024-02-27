package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_TEXT = "supply";
    private static final String BUY_TEXT = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String line = readFromFile(fromFileName);
        String report = createReport(line);
        writeToFile(toFileName, report);
    }

    public String readFromFile(String fromFileName) {
        File incomingFile = new File(fromFileName);
        String readString;
        StringBuilder readerBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(incomingFile))) {
            String fileLine = reader.readLine();
            while (fileLine != null) {
                readerBuilder.append(fileLine).append(",");
                fileLine = reader.readLine();
            }
            readString = readerBuilder.toString();
            readerBuilder.setLength(0);
            return readString;
        } catch (IOException b) {
            throw new RuntimeException("Cannot read from file" + b.getMessage());
        }
    }

    public String createReport(String line) {
        int totalSupply = 0;
        int totalBuy = 0;
        int amountNumber;
        int totalAmount;
        String operation = " ";
        String reportString;
        String[] separatedItems = line.split(",");
        StringBuilder reportBuilder = new StringBuilder();
        for (int i = 0; i < separatedItems.length; i += 2) {
            operation = separatedItems[i].trim();
            amountNumber = Integer.parseInt(separatedItems[i + 1].trim());
            if (operation.equals(SUPPLY_TEXT)) {
                totalSupply += amountNumber;
            } else if (operation.equals(BUY_TEXT)) {
                totalBuy += amountNumber;
            }
        }
        totalAmount = totalSupply - totalBuy;
        reportString = reportBuilder.append(SUPPLY_TEXT)
                .append(",")
                .append(totalSupply)
                .append(System.lineSeparator())
                .append(BUY_TEXT)
                .append(",")
                .append(totalBuy)
                .append(System.lineSeparator())
                .append("result,")
                .append(totalAmount)
                .toString();
        reportBuilder.setLength(0);
        return reportString;
    }

    public void writeToFile(String toFileName, String result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file" + e.getMessage());
        }
    }

}
