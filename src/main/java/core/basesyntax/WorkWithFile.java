package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String amount = createReport(fromFileName);
        writeToFile(toFileName, amount);
    }

    public String createReport(String fromFileName) {
        String operation = "";
        String fileLine = "";
        File incomingFile = new File(fromFileName);
        int totalSupply = 0;
        int totalBuy = 0;
        int totalResult = 0;
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(incomingFile))) {
            fileLine = reader.readLine();
            while (fileLine != null) {
                String[] separatedItems = fileLine.split(",");
                operation = separatedItems[0].trim();
                if (operation.equals("supply")) {
                    totalSupply += Integer.parseInt(separatedItems[1]);
                } else if (operation.equals("buy")) {
                    totalBuy += Integer.parseInt(separatedItems[1]);
                }
                fileLine = reader.readLine();
            }
            totalResult = totalSupply - totalBuy;
        } catch (IOException b) {
            throw new RuntimeException("Cannot read from file" + b.getMessage());
        }
        return builder.append(totalSupply)
                .append(",")
                .append(totalBuy)
                .append(",")
                .append(totalResult)
                .toString();
    }

    public void writeToFile(String toFileName, String amount) {
        String[] count = amount.split(",");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + count[0] + System.lineSeparator());
            writer.write("buy," + count[1] + System.lineSeparator());
            writer.write("result," + count[2]);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file" + e.getMessage());
        }
    }

}
