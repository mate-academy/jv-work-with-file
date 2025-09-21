package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final String OPERATION_TYPE_SUPPLY = "supply";
    private static final String OPERATION_TYPE_BUY = "buy";
    private static final String OPERATION_TYPE_RESULT = "result";
    private static final String COMMA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                switch (parts[0]) {
                    case OPERATION_TYPE_BUY:
                        buy += Integer.parseInt(parts[1]);
                        break;
                    case OPERATION_TYPE_SUPPLY:
                        supply += Integer.parseInt(parts[1]);
                        break;
                    default:
                        break;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Can't read file", e);
        }

        String resultToFile = OPERATION_TYPE_SUPPLY + COMMA_SEPARATOR 
                + supply + System.lineSeparator()
                + OPERATION_TYPE_BUY + COMMA_SEPARATOR + buy + System.lineSeparator()
                + OPERATION_TYPE_RESULT + COMMA_SEPARATOR + (supply - buy);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(resultToFile);
        } catch (Exception e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
