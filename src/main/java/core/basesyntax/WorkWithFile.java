package core.basesyntax;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fileFrom = new File(fromFileName);
        int buy = 0;
        int supply = 0;
        int result;
        StringBuilder builder = new StringBuilder();

        try {BufferedReader reader = new BufferedReader(new FileReader(fileFrom));
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String inputString = builder.toString().replace(System.lineSeparator(), ",");
        String[] inputArray = inputString.split(",");

        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i].equals("buy")) {
                buy += Integer.parseInt(inputArray[i + 1]);
            } else if (inputArray[i].equals("supply")) {
                supply += Integer.parseInt(inputArray[i + 1]);
            }
        }
        result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create a file", e);
        }

        try {
            Files.write(file.toPath(),stringBuilder.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
