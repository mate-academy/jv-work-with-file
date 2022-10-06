package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String REGEX = "\\W+";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMA = ",";
    private static final String SPACE = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        String string = readFromFile(fromFileName);
        String result = resultString(string);
        writeDataToFile(result,toFileName);
    }

    private void writeDataToFile(String data, String toFileName) {
        String[] result = resultString(data).split(SPACE);
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(result[i] + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file" + toFileName, e);
        }
    }

    private String readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read file" + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fileName, e);
        }
        return stringBuilder.toString();
    }

    private String resultString(String data) {
        int supply = 0;
        int buy = 0;
        String[] split = data.split(REGEX);
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals(SUPPLY)) {
                supply += Integer.parseInt(split[i + 1]);
            }
            if (split[i].equals(BUY)) {
                buy += Integer.parseInt(split[i + 1]);
            }
        }
        int result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(COMA).append(supply).append(SPACE).append(BUY)
                .append(COMA).append(buy).append(SPACE).append(RESULT).append(COMA).append(result);
        return stringBuilder.toString();
    }
}
