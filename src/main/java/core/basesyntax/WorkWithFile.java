package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final String LINE_SEPARATOR = System.lineSeparator();

    private String readFromFile(String fromFileName) {
        File fileFrom = new File(fromFileName);
        StringBuilder dataFromFile = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileFrom))) {
            String line = reader.readLine();
            while (line != null) {
                dataFromFile.append(line).append(",");
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return dataFromFile.toString();
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String statistic = readFromFile(fromFileName);
        String[] statisticToArray = statistic.split(",");
        int resultSupply = resultSupply(statisticToArray);
        int resultBuy = resultBuy(statisticToArray);
        StringBuilder statisticFromFile = new StringBuilder();
        statisticFromFile.append(SUPPLY).append(",").append(resultSupply)
                .append(System.lineSeparator()).append(BUY)
                .append(",").append(resultBuy)
                .append(LINE_SEPARATOR)
                .append(RESULT).append(",").append(resultSupply - resultBuy);
        writeFile(statisticFromFile.toString(), toFileName);
    }

    private void writeFile(String statisticFromFile, String toFileName) {
        File toFile = new File(toFileName);
        try {
            toFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
        BufferedWriter bufferedwriter = null;
        try {
            bufferedwriter = new BufferedWriter(new FileWriter(toFileName, true));
            {
                bufferedwriter.write(statisticFromFile);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        } finally {
            if (bufferedwriter != null) {
                try {
                    bufferedwriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close BufferedWriter", e);
                }
            }

        }
    }

    private int resultBuy(String [] fileToArray) {

        int resultBuy = 0;
        for (int i = 0; i < fileToArray.length; i++) {
            if (fileToArray[i].equals(BUY)) {
                resultBuy += Integer.parseInt(fileToArray[i + 1]);
            }
        }
        return resultBuy;
    }

    private int resultSupply(String [] fileToArray) {

        int resultSupply = 0;
        for (int i = 0; i < fileToArray.length; i++) {
            if (fileToArray[i].equals(SUPPLY)) {
                resultSupply += Integer.parseInt(fileToArray[i + 1]);
            }
        }
        return resultSupply;
    }
}
