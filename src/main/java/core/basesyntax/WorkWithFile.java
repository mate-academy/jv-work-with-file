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

    public void getStatistic(String fromFileName, String toFileName) {
        File fileFrom = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileFrom));
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(",");
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        File toFile = new File(toFileName);
        try {
            toFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }

        String[] fileToArray = stringBuilder.toString().split(",");
        StringBuilder resultBuilder = new StringBuilder();
        int resultSupply = 0;
        int resultBuy = 0;
        for (int i = 0; i < fileToArray.length; i++) {
            if (fileToArray[i].equals(SUPPLY)) {
                resultSupply += Integer.parseInt(fileToArray[i + 1]);
            }
            if (fileToArray[i].equals(BUY)) {
                resultBuy += Integer.parseInt(fileToArray[i + 1]);
            }
        }
        resultBuilder.append(SUPPLY).append(",").append(resultSupply)
                .append(System.lineSeparator()).append(BUY)
                .append(",").append(resultBuy)
                .append(System.lineSeparator())
                .append(RESULT).append(",").append(resultSupply - resultBuy);
        BufferedWriter bufferedwriter = null;
        try {
            bufferedwriter = new BufferedWriter(new FileWriter(toFileName, true));
            {
                bufferedwriter.write(resultBuilder.toString());
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
}

