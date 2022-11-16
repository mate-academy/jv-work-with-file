package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private String[] splitInformationFromFile;

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            splitInformationFromFile = builder.toString().split(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file " + fromFileName, e);
        }
        String report = createReport(splitInformationFromFile);
        File newFile = new File(toFileName);
        WorkWithFile file = new WorkWithFile();
        file.writeToFile(newFile, report);
    }

    private String createReport(String[] array) {
        int supply = 0;
        int buy = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (String arrayInfo : array) {
            String[] arraySplit = arrayInfo.split(",");
            if (arraySplit[OPERATION_TYPE_INDEX].equals("supply")) {
                supply += Integer.parseInt(arraySplit[AMOUNT_INDEX]);
            } else if (arraySplit[OPERATION_TYPE_INDEX].equals("buy")) {
                buy += Integer.parseInt(arraySplit[AMOUNT_INDEX]);
            }
        }

        return stringBuilder
                .append("supply,")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(supply - buy).toString();
    }

    private void writeToFile(File file, String string) {
        try {
            Files.write(file.toPath(), string.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + file, e);
        }
    }
}
