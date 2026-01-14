package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String givenText = readTheFile(fromFileName);
        String result = calculations(givenText);
        writeIntoFile(result, toFileName);
    }

    private String readTheFile(String fromFileName) {
        String fileName = new File(fromFileName).getName();
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return stringBuilder.toString();
    }

    private String calculations(String givenText) {
        String ls = System.lineSeparator();
        int supply = 0;
        int buy = 0;
        StringBuilder stringBuilder = new StringBuilder();

        String[] lines = givenText.split("\\R");
        for (String line : lines) {
            String[] oneLine = line.split(",");
            int number = Integer.parseInt(oneLine[1]);
            if (oneLine[0].equals("supply")) {
                supply += number;
            } else if (oneLine[0].equals("buy")) {
                buy += number;
            }
        }

        int result = supply - buy;

        stringBuilder.append("supply,").append(supply).append(ls)
                .append("buy,").append(buy).append(ls)
                .append("result,").append(result);

        return stringBuilder.toString();
    }

    private void writeIntoFile(String result, String toFileName) {
        String fileName = new File(toFileName).getName();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));) {
            bufferedWriter.write(result);
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}
