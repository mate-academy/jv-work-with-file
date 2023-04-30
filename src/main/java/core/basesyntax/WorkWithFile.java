package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String KOMA = ",";
    private static final String[] resultArr = new String[]{SUPPLY, BUY, RESULT};

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataInArray = readData(fromFileName);
        int[] finalValues = reportValues(dataInArray);
        writeData(finalValues, toFileName);
    }

    private String[] readData(String fileName) {
        File file = new File(fileName);
        StringBuilder builder = new StringBuilder();
        if (file.length() == 0) {
            return new String[0];
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int value = bufferedReader.read();
            while (value != -1) {
                builder.append((char) value);
                value = bufferedReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return builder.toString().split("\\s+");
    }

    private void writeData(int[] values, String fileName) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create the file");
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            builder.append(resultArr[i]).append(KOMA)
                    .append(values[i]).append(System.lineSeparator());
        }
        try {
            Files.write(file.toPath(), builder.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't add data to the file");
        }
    }

    private int[] reportValues(String[] dataSplited) {
        int result;
        int supply = 0;
        int buy = 0;
        for (String string : dataSplited) {
            if (string.substring(0,
                    string.indexOf(KOMA)).equals(SUPPLY)) {
                supply += Integer.parseInt(string
                        .substring(string.indexOf(KOMA) + 1));
            } else {
                buy += Integer.parseInt(string
                        .substring(string.indexOf(KOMA) + 1));
            }
        }
        result = supply - buy;
        return new int[]{supply, buy, result};
    }
}
