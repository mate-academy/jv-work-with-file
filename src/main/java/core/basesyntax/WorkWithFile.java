package core.basesyntax;

import java.io.*;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        File myFile = new File(toFileName);
        StringBuilder builder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(";");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        String[] listOfStatistic = builder.toString().split(";");
        int supplyCount = 0;
        int buyCount = 0;
        for (String statistic : listOfStatistic) {
            if (statistic.contains("supply")) {
                supplyCount += Integer.parseInt(statistic
                        .substring(statistic.indexOf(',') + 1));
            } else if (statistic.contains("buy")) {
                buyCount += Integer.parseInt(statistic
                        .substring(statistic.indexOf(',') + 1));
            }
        }
        int result = supplyCount - buyCount;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(myFile))) {
            bufferedWriter.write("supply," + supplyCount + System.lineSeparator()
                    + "buy," + buyCount + System.lineSeparator() + "result," + result);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
