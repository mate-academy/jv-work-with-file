package core.basesyntax;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    private String inputData1 = "buy";
    private String inputData2 = "supply";
    private final int dataIndex = 0;
    private final int countIndex = 1;
    private int inputData1Count = 0;
    private int inputData2Count = 0;

    private String [] readFile(String fileName) {
        String[] inputArray;
        try {
            inputArray = Files.readString(Paths.get(fileName)).split("\n");
        } catch (Exception e) {
            throw new RuntimeException("Can't read file", e);
        }
        return inputArray;
    }

    private String countStatistic(String [] strings) {
        for (int i = 0; i < strings.length; i++) {
            String [] temp = strings[i].split(",");
            int tempInt = Integer.parseInt(temp[countIndex].replaceAll("[^\\d]", ""));
            if (temp[dataIndex].contains(inputData1)) {
                inputData1Count += tempInt;
            } else {
                inputData2Count += tempInt;
            }
        }
        return (inputData2 + "," + inputData2Count + System.lineSeparator()
                + inputData1 + "," + inputData1Count + System.lineSeparator() + "result,"
                + (inputData2Count - inputData1Count));
    }

    private void writeToFile(String inputData, String toFileName) {
        try {
            FileWriter fileWriter = new FileWriter(toFileName);
            fileWriter.write(inputData);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't open file", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile workWithFile = new WorkWithFile();
        String [] inputArray = workWithFile.readFile(fromFileName);
        String resultString = workWithFile.countStatistic(inputArray);
        writeToFile((resultString), toFileName);
    }
}
