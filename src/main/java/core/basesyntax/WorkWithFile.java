package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = getSumUpFromFile(fromFileName);

        try {
            Files.write(Path.of(toFileName), data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file, ", e);
        }
    }

    private String getSumUpFromFile(String fileName) {
        int buy = 0;
        int supply = 0;
        File file = new File(fileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            String[] values;

            while (value != null) {
                values = value.split(",");

                switch (values[0]) {
                    case SUPPLY:
                        supply += Integer.parseInt(values[1]);
                        break;
                    case BUY:
                        buy += Integer.parseInt(values[1]);
                        break;
                    default:
                        throw new RuntimeException("Incorrect file content");
                }

                value = reader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read file, ", e);
        }

        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }

}
