package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int DATA_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] values = line.split(SEPARATOR);
                switch (values[DATA_INDEX]) {
                    case SUPPLY: {
                        supply += Integer.parseInt(values[VALUE_INDEX]);
                        break;
                    }
                    case BUY: {
                        buy += Integer.parseInt(values[VALUE_INDEX]);
                        break;
                    }
                    default: {
                        break;
                    }
                }
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found!", e);
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(SUPPLY + SEPARATOR + supply + System.lineSeparator());
            bufferedWriter.flush();
            bufferedWriter.write(BUY + SEPARATOR + buy + System.lineSeparator());
            bufferedWriter.flush();
            bufferedWriter.write(RESULT + SEPARATOR + (supply - buy));
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Error writing file", e);
        }

    }
}
