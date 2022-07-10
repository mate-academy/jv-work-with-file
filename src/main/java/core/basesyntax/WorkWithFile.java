package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        final String buyEventName = "buy";
        final String supplyEventName = "supply";
        File fileSource = new File(fromFileName);
        File fileTarget = new File(toFileName);
        StringBuilder stringBuilder = new StringBuilder();
        int supplyTotal = 0;
        int buyTotal = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileSource))) {
            String lineFromFile = reader.readLine();
            String[] splittedLine;
            while (lineFromFile != null) {
                splittedLine = null;
                splittedLine = lineFromFile.split(",");
                switch (splittedLine[0]) {
                    case (supplyEventName):
                        supplyTotal += Integer.valueOf(splittedLine[1]);
                        break;
                    case (buyEventName):
                        buyTotal += Integer.valueOf(splittedLine[1]);
                        break;
                    default:
                        break;
                }
                lineFromFile = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            throw new RuntimeException("File " + fromFileName + " not found.", ex);
        }
        stringBuilder.append(supplyEventName)
                .append(",")
                .append(supplyTotal)
                .append(System.lineSeparator());
        stringBuilder.append(buyEventName)
                .append(",")
                .append(buyTotal)
                .append(System.lineSeparator());
        stringBuilder.append("result,")
                .append(supplyTotal - buyTotal);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTarget, true))) {
            writer.write(stringBuilder.toString());
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }
}
