package core.basesyntax;


import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int buySum = 0;
        int supplySum = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if ("supply".equals(type)) {
                    supplySum += amount;
                } else if ("buy".equals(type)) {
                    buySum += amount;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file ", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file ", e);
        }

        int result = supplySum - buySum;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            StringBuilder builder = new StringBuilder();

            writer.write(String.valueOf(builder.append("supply, ").append(supplySum)));
            writer.write(String.valueOf(builder.append("buy, ").append(buySum)));
            writer.write(String.valueOf(builder.append("result, ").append(result)));
        } catch (IOException e) {
            throw new RuntimeException("Can't write file ", e);
        }
    }
}
