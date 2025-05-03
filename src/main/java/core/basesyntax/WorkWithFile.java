package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(fromFileName);
        writeToFile(report, toFileName);
    }
    
    private String createReport(String fromFileName) {
        int totalSupply = 0;
        int totalBuy = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String readLine = reader.readLine();
            do {
                String[] currentLine = readLine.split(",");
                if (currentLine[0].equals("supply")) {
                    totalSupply += Integer.valueOf(currentLine[1]);
                } else {
                    totalBuy += Integer.valueOf(currentLine[1]);
                }
                readLine = reader.readLine();
            } while (readLine != null);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        int difference = totalSupply - totalBuy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(totalSupply).append(System.lineSeparator())
                .append("buy,").append(totalBuy).append(System.lineSeparator())
                .append("result,").append(difference);
        return new String(stringBuilder);
    }
    
    private void writeToFile(String report, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            file.createNewFile();
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
