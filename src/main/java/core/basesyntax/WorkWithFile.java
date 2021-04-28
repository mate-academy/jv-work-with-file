package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_DIVIDER = ",";
    private static final String COMPARISON_WORD = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] resultOfReadFile = readFile(new File(fromFileName));
        writeFile(countData(resultOfReadFile), toFileName);
    }

    private String[] readFile(File oldFile) {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(oldFile))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line).append(" ");
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't found the file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }

        return builder.toString().trim().split(" ");
    }

    private String countData(String[] arrayOfData) {
        int supply = 0;
        int buy = 0;

        for (String arrayOfDatum : arrayOfData) {
            String[] data = arrayOfDatum.split(LINE_DIVIDER);
            if (data[0].equals(COMPARISON_WORD)) {
                buy += Integer.parseInt(data[1]);
            } else {
                supply += Integer.parseInt(data[1]);
            }
        }
        int result = supply - buy;

        return new StringBuilder("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result).toString();
    }

    private void writeFile(String newData, String nameFile) {
        File newFile = new File(nameFile);
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(newFile, true))) {
            bufferedWriter.write(newData);
        } catch (IOException e) {
            throw new RuntimeException("Can't found the file", e);
        }
    }
}
