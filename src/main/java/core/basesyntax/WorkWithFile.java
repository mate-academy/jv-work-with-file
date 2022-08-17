package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkWithFile {
    private StringBuilder dataToWrite = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readDataFromFile(fromFileName);
        String finalArrayOfData = createReport(dataFromFile, new String[2],0,0);

        writeDataToFile(toFileName, finalArrayOfData);
    }

    public List<String> readDataFromFile(String fromFileName) {
        List<String> res = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = reader.readLine()) != null) {
                res.addAll(Arrays.asList(line.split(",")));
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }
        return res;
    }

    public void writeDataToFile(String fileName, String dataToWrite) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(dataToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't find a file: " + fileName, e);
        }
    }

    public String createReport(List<String> data, String[] result,
                               int sumOfTheValuesOfTheFirstElements,
                               int sumOfTheValuesOfTheSecondElements) {
        for (int i = 0; i < data.size(); i += 2) {
            for (int j = i + 2; j < data.size(); j += 2) {
                if (data.get(i).equals(data.get(j))) {
                    result[0] = data.get(j);
                }
            }
        }
        for (int i = 0; i < data.size(); i += 2) {
            if (!result[0].equals(data.get(i))) {
                result[1] = data.get(i);
            }
        }
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(result[0])) {
                sumOfTheValuesOfTheFirstElements += Integer.parseInt(data.get(i + 1));
            }
            if (data.get(i).equals(result[1])) {
                sumOfTheValuesOfTheSecondElements += Integer.parseInt(data.get(i + 1));
            }
        }
        return dataToWrite.append(result[1]).append(",")
                .append(sumOfTheValuesOfTheSecondElements)
                .append(System.lineSeparator())
                .append(result[0])
                .append(",")
                .append(sumOfTheValuesOfTheFirstElements)
                .append(System.lineSeparator())
                .append("result")
                .append(",")
                .append(sumOfTheValuesOfTheSecondElements - sumOfTheValuesOfTheFirstElements)
                .toString();
    }
}
