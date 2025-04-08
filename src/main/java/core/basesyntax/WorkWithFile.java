package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) throws IOException {
        int supplySum = 0;
        int buySum = 0;
        int temporaryValue = 0;
        String temporaryValueName = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);
                if (operation.equals("supply")) {
                    supplySum += amount;
                } else if(operation.equals("buy")) {
                    buySum += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int result = supplySum - buySum;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply,"+ supplySum + System.lineSeparator());
            writer.write("buy" + buySum + System.lineSeparator());
            writer.write("result" + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
