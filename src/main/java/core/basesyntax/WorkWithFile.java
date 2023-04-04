package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {

    private List<String> readFromFile(String fromFileName) {
        List<String> data;
        File file = new File(fromFileName);
        try {
            data = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return data;
    }

    private String balanceCalculate(List<String> data) {
        int supplySum = 0;
        int buySum = 0;
        final String separator = System.lineSeparator();
        for (String element : data) {
            String[] elements = element.split(",");
            if (elements[0].equals("supply")) {
                supplySum += Integer.parseInt(elements[1]);
            } else if (elements[0].equals("buy")) {
                buySum += Integer.parseInt(elements[1]);
            }
        }
        int result = supplySum - buySum;
        return ("supply," + supplySum + separator
                + "buy," + buySum + separator
                + "result," + result);
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String result = "";
        List<String> data = readFromFile(fromFileName);
        if (data != null) {
            result = balanceCalculate(data);
        }
        writeToFile(toFileName, result);
    }

    public void writeToFile(String toFileName, String statistic) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(statistic);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }
}

