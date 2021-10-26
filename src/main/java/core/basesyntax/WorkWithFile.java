package core.basesyntax;

import java.io.*;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String readedFile = readFromFile(fromFileName);
        String statistic = statistic(readedFile);
        writeToFile(statistic, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }

        return builder.toString();
    }

    private String statistic(String someStatistic) {
        String[] splitArray = someStatistic.split(" ");
        int supply = 0;
        int buy = 0;
        int result = 0;
        for (int i = 0; i < splitArray.length; i++) {
            int j = splitArray[i].indexOf(',');
            if (splitArray[i].substring(0, j).equals("supply")) {
                supply += Integer.valueOf(splitArray[i].substring(j + 1));
            }
            if (splitArray[i].substring(0, j).equals("buy")) {
                buy += Integer.valueOf(splitArray[i].substring(j + 1));
            }
        }
        result = supply - buy;
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("supply,").append(supply).append("\n").append("buy,").append(buy)
                .append("\n").append("result,").append(result);
        return resultBuilder.toString();
    }

    private void writeToFile(String statistic, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Cant't write to file", e);
        }
    }

}
