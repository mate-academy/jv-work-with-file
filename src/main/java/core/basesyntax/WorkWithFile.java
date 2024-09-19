package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private int[] getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] split = value.split(",");
                if (split[0].equals("supply")) {
                    supply += Integer.parseInt(split[1]);
                } else if (split[0].equals("buy")) {
                    buy += Integer.parseInt(split[1]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return new int[]{supply, buy};
    }

    private StringBuilder countStatistic(String fromFileName) {
        int[] stats = getStatistic(fromFileName);
        int supply = stats[0];
        int buy = stats[1];
        int countedResult = supply - buy;

        StringBuilder result = new StringBuilder();
        result.append("supply,");
        result.append(supply);
        result.append("\n");
        result.append("buy,");
        result.append(buy);
        result.append("\n");
        result.append("result,");
        result.append(countedResult);

        return result;
    }

    public String writeStatistic(String fromFileName, String toFileName) {
        StringBuilder result = countStatistic(fromFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(result.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
        return result.toString();
    }
}
