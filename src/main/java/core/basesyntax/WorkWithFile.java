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
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                builder.append(resultArr[i]).append(KOMA)
                        .append(values[i]).append(System.lineSeparator());
            }
            Files.write(file.toPath(), builder.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while creating or writing to the file");
        }
    }

    private int[] reportValues(String[] dataSplited) {
        int result;
        int supply = 0;
        int buy = 0;
        for (String string : dataSplited) {
            String[] parts = string.split(KOMA);
            if (parts[0].equals(SUPPLY)) {
                supply += Integer.parseInt(parts[1]);
            } else {
                buy += Integer.parseInt(parts[1]);
            }
        }
        result = supply - buy;
        return new int[] {supply, buy, result};
    }

}
