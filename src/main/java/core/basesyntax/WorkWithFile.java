package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] fileDataArray = readFile(fromFileName).split("\n");
        String[] arrayToWrite = sortNums(fileDataArray);
        File file = new File(toFileName);
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Cannot create a new file");
        }
        writeData(arrayToWrite, toFileName);
    }

    public String readFile(String fromFileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file.");
        }
        return content.toString();
    }

    public String[] sortNums(String[] notGroupedArray) {
        int supplySum = 0;
        int buySum = 0;
        for (int i = 0; i < notGroupedArray.length; i++) {
            String[] splitedArray = notGroupedArray[i].split(",");
            if (splitedArray[0].equals("supply")) {
                supplySum += Integer.parseInt(splitedArray[1]);
            } else if (splitedArray[0].equals("buy")) {
                buySum += Integer.parseInt(splitedArray[1]);
            }
        }
        return new String[] {"supply," + supplySum, "buy,"
                + buySum, "result," + (supplySum - buySum)};
    }

    public void writeData(String[] arrayWithData, String fileToWrite) {
        File file = new File(fileToWrite);
        for (String data: arrayWithData) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            } catch (IOException e) {
                throw new RuntimeException("The data hasn't been written");
            }
        }
    }
}

    /*
        public void writeData(String[] arrayWithData, String fileToWrite) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String data: arrayWithData) {
            stringBuilder.append(data).append("\n");
        }
        File file = new File(fileToWrite);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                bufferedWriter.write(stringBuilder.toString());
            } catch (IOException e) {
                throw new RuntimeException("The data hasn't been written");
            }
        }
    }
     */
