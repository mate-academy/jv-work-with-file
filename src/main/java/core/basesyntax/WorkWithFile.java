package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fileTo = new File(toFileName);
        String fileFromData = getFileData(fromFileName);
        int[] value = getValues(fileFromData);
        int result = value[0] - value[1];
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(value[0]).append(System.lineSeparator())
                .append("buy,").append(value[1]).append(System.lineSeparator())
                .append("result,").append(result).append(System.lineSeparator());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTo))) {
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file " + toFileName, e);
        }

    }

    private String getFileData(String file) {
        File fileFrom = new File(file);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileFrom))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + file, e);
        }
    }

    private int[] getValues(String nameFile) {
        String[] dataFromFileToMath = nameFile.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String line: dataFromFileToMath) {
            String[] lineToMath = line.split(",");
            if (lineToMath[0].length() > 0 && lineToMath[0].equals("supply")) {
                supply += Integer.parseInt(lineToMath[1]);
            }
            if (lineToMath[0].length() > 0 && lineToMath[0].equals("buy")) {
                buy += Integer.parseInt(lineToMath[1]);
            }
        }
        return new int[]{supply,buy};
    }
}
