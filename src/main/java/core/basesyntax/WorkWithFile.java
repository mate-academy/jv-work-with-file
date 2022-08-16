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

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder dataToWrite = new StringBuilder();
        List<String> dataFromFile = readDataFromFile(fromFileName);
        String[] finalArrayOfData = generateArrayWithCorrectData(dataFromFile, new String[2]);
        int[] finalSumOfEachElementOfArrayData =
                generateSumForEachElementOfCorrectDataArray(
                        dataFromFile,
                        finalArrayOfData,
                        0,
                        0);
        dataToWrite.append(finalArrayOfData[1]).append(",")
                .append(finalSumOfEachElementOfArrayData[1])
                .append(System.lineSeparator())
                .append(finalArrayOfData[0]).append(",")
                .append(finalSumOfEachElementOfArrayData[0])
                .append(System.lineSeparator())
                .append("result").append(",")
                .append(finalSumOfEachElementOfArrayData[1] - finalSumOfEachElementOfArrayData[0]);
        writeDataToFile(toFileName, dataToWrite);
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

    public void writeDataToFile(String fileName, StringBuilder dataToWrite) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(dataToWrite.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't find a file: " + fileName, e);
        }
    }

    public String[] generateArrayWithCorrectData(List<String> data, String[] result) {
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
        return result;
    }

    public int[] generateSumForEachElementOfCorrectDataArray(
            List<String> listOfFilesData,
            String[] finalArrayOfData,
            int firstSum,
            int secondSum) {
        for (int i = 0; i < listOfFilesData.size(); i++) {
            if (listOfFilesData.get(i).equals(finalArrayOfData[0])) {
                firstSum += Integer.parseInt(listOfFilesData.get(i + 1));
            }
            if (listOfFilesData.get(i).equals(finalArrayOfData[1])) {
                secondSum += Integer.parseInt(listOfFilesData.get(i + 1));
            }
        }
        int[] res = new int[]{firstSum, secondSum};
        return res;
    }
}
