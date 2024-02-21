package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int[] amount = createReport(fromFileName);
        writeToFile(toFileName, amount);
    }

    public int[] createReport(String fromFileName) {
        String operation = "";
        File incomingFile = new File(fromFileName);
        int[] amount = new int[3];
        try (BufferedReader reader = new BufferedReader(new FileReader(incomingFile))) {
            String i = reader.readLine();
            while (i != null) {
                String[] separatelines = i.split(",");
                operation = separatelines[0].trim();
                if (operation.equals("supply")) {
                    amount[0] += Integer.parseInt(separatelines[1].trim());
                } else if (operation.equals("buy")) {
                    amount[1] += Integer.parseInt(separatelines[1].trim());
                }
                i = reader.readLine();
            }
            amount[2] = amount[0] - amount[1];
        } catch (IOException b) {
            throw new RuntimeException("Cannot read from file" + b.getMessage());
        }
        return amount;
    }

    public void writeToFile(String toFileName, int[] amount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + amount[0] + System.lineSeparator());
            writer.write("buy," + amount[1] + System.lineSeparator());
            writer.write("result," + amount[2]);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file" + e.getMessage());
        }
    }

}
