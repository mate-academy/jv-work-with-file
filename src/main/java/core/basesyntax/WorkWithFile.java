package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {

    public String resultString(List<String> list) {
        StringBuilder builder = new StringBuilder();
        if (list.size() > 0) {
            Integer buySum = 0;
            Integer supplySum = 0;
            for (String item : list) {
                String[] datumArr = item.split(",");
                String operation = datumArr[0];
                String value = datumArr[1];
                if (operation.equals("buy")) {
                    buySum += Integer.valueOf(value);
                } else {
                    supplySum += Integer.valueOf(value);
                }
            }

            return builder.append("supply").append(",").append(supplySum).append("\n")
                    .append("buy").append(",").append(buySum).append("\n")
                    .append("result").append(",").append(supplySum - buySum)
                    .toString();
        }

        return "";
    }

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> inputData = new ArrayList<>();

        try {
            List<String> data = Files.readAllLines(Path.of(fromFileName));
            inputData.addAll(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file!");
        }

        try {
            Files.write(Path.of(toFileName), resultString(inputData).getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file!");
        }
    }

}
