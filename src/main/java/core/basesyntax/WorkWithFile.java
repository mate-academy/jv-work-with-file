package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = getSortedData(getSumItems(fromFileName));
        StringBuilder resultData = new StringBuilder();
        for (String line : data) {
            resultData.append(line).append(System.lineSeparator());
        }
        resultData.append(getOddOfValues(data));
        writeToFile(resultData.toString().split(System.lineSeparator()), toFileName);
    }

    private String[] getSortedData(String[] resultData) {
        Arrays.sort(resultData, (o1, o2) -> {
            int number1 = Integer.parseInt(o1.split(",")[1]);
            int number2 = Integer.parseInt(o2.split(",")[1]);
            return Integer.compare(number2, number1);
        });
        return resultData;
    }

    private String[] getSumItems(String fromFileName) {
        String[] data = readFromFile(fromFileName);
        StringBuilder resultData = new StringBuilder();
        String[] keyWords = getUniqueWords(data);
        int counter = 0;
        for (String datum : keyWords) {
            for (String word : data) {
                String[] compareWords = word.split(",");
                if (datum.equals(compareWords[0])) {
                    counter += Integer.parseInt(compareWords[1]);
                }
            }
            resultData.append(datum).append(",").append(counter).append(System.lineSeparator());
            counter = 0;
        }
        return resultData.toString().split(System.lineSeparator());
    }

    private String getOddOfValues(String[] resultData) {
        StringBuilder result = new StringBuilder();
        int odd = 0;
        for (String number : resultData) {
            int digit = Integer.parseInt(number.split(",")[1]);
            if (digit > odd) {
                odd = digit - odd;
            } else {
                odd -= digit;
            }
        }
        result.append("result").append(",").append(odd);
        return result.toString();
    }

    private String[] getUniqueWords(String[] dataFromFile) {
        StringBuilder uniqueArray = new StringBuilder();
        String allStrings = Arrays.toString(dataFromFile).replaceAll("[^a-zA-Z ]+", "");
        String[] strings = allStrings.split(" ");
        for (String temp : strings) {
            if (!uniqueArray.toString().contains(temp)) {
                uniqueArray.append(temp).append(" ");
            }
        }
        return String.valueOf(uniqueArray).split(" ");
    }

    private String[] readFromFile(String fromFile) {
        try {
            return Files.readString(Paths.get(fromFile)).split("\r?\n");
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    private void writeToFile(String[] text, String toFile) {
        StringBuilder data = new StringBuilder();
        for (String line : text) {
            data.append(line).append(System.lineSeparator());
        }
        try (FileWriter file = new FileWriter(toFile);
                BufferedWriter writer = new BufferedWriter(file)) {
            writer.write(String.valueOf(data));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
