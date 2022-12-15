package core.basesyntax;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    private String [] readFile(String fileName) {
        String[] inputArray;
        try {
            inputArray = Files.readString(Paths.get(fileName)).split("\n");
        } catch (Exception e) {
            throw new RuntimeException("Can't read file", e);
        }
        return inputArray;
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

    private String getStatistic(String [] strings, String inputData1, String inputData2) {
        final int Data_Index = 0;
        final int Count_Index = 1;
        int inputData1Count = 0;
        int inputData2Count = 0;

        for (int i = 0; i < strings.length; i++) {
            String [] temp = strings[i].split(",");
            int tempInt = Integer.parseInt(temp[Count_Index].replaceAll("[^\\d]", ""));
            if (temp[Data_Index].contains(inputData1)) {
                inputData1Count += tempInt;
            } else {
                inputData2Count += tempInt;
            }
        }
        return (inputData2 + "," + inputData2Count + System.lineSeparator()
                + inputData1 + "," + inputData1Count + System.lineSeparator() + "result,"
                + (inputData2Count - inputData1Count));
    }

    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile workWithFile = new WorkWithFile();
        writeToFile(getStatistic(workWithFile.readFile(fromFileName), "buy", "supply"), toFileName);
    }
}
