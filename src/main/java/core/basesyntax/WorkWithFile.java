package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            Map<String, Integer> supplyData = new LinkedHashMap<>();
            Map<String, Integer> buyData = new LinkedHashMap<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String operationType = data[0];
                int amount = Integer.parseInt(data[1]);
                if ("supply".equals(operationType)) {
                    supplyData.put(operationType,
                            supplyData.getOrDefault(operationType, 0) + amount);
                } else if ("buy".equals(operationType)) {
                    buyData.put(operationType, buyData.getOrDefault(operationType, 0) + amount);
                }
            }

            int supplyResult = 0;
            for (Map.Entry<String, Integer> unit : supplyData.entrySet()) {
                supplyResult = unit.getValue() - supplyResult;
            }

            int buyResult = 0;
            for (Map.Entry<String, Integer> unit : buyData.entrySet()) {
                buyResult = unit.getValue() - buyResult;
            }

            StringBuilder resultBuilder = new StringBuilder();
            for (Map.Entry<String, Integer> entry : supplyData.entrySet()) {
                int sum = entry.getValue();
                String operationType = entry.getKey();
                resultBuilder.append(operationType);
                resultBuilder.append(",").append(sum).append(System.lineSeparator());
            }

            for (Map.Entry<String, Integer> entry : buyData.entrySet()) {
                int sum = entry.getValue();
                String operationType = entry.getKey();
                resultBuilder.append(operationType);
                resultBuilder.append(",").append(sum).append(System.lineSeparator());
            }

            resultBuilder.append("result,")
                    .append(supplyResult - buyResult).append(System.lineSeparator());
            writer.write(resultBuilder.toString());
            System.out.println(resultBuilder);

        } catch (IOException e) {
            throw new RuntimeException("Error while reading/writing the file: "
                    + e.getMessage());
        }
    }
}

