package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] inputArray = readFromFile(fromFileName).split(",");
        int buy = 0;
        int supply = 0;
        int result;

        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i].equals("buy")) {
                buy += Integer.parseInt(inputArray[i + 1]);
            } else if (inputArray[i].equals("supply")) {
                supply += Integer.parseInt(inputArray[i + 1]);
            }
        }
        result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder().append("supply,").append(supply)
                .append(System.lineSeparator()).append("buy,").append(buy)
                .append(System.lineSeparator()).append("result,").append(result);
        String calculateResult = stringBuilder.toString();

        writeToNewFile(toFileName, calculateResult);
    }

    private String readFromFile(String fromFileName) {
        File fileFrom = new File(fromFileName);
        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileFrom));
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return builder.toString().replace(System.lineSeparator(), ",");
    }

    private void writeToNewFile(String toFileName, String calculateResult) {
        File file = new File(toFileName);

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create a file", e);
        }

        try {
            Files.write(file.toPath(),calculateResult.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
