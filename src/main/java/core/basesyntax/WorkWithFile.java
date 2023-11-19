package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final String SUPPLIY = "supply";
    private static final String BUY = "buy";
    private int supply = 0;
    private int buy = 0;
    private static final String DATA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName);
        writeFile(toFileName);

    }

    private void readFile(String fromFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] data = line.split(DATA_SEPARATOR);
                if (data[0].equals(SUPPLIY)) {
                    supply += Integer.parseInt(data[1]);
                } else if (data[0].equals(BUY)) {
                    buy += Integer.parseInt(data[1]);
                }
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file", e);
        }
    }

    private void writeFile(String toFile) {
        File file = new File(toFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(file))) {
            bufferedWriter.write("supply,"+supply + System.lineSeparator());
            bufferedWriter.write("buy,"+buy + System.lineSeparator());
            bufferedWriter.write("result," + (supply - buy) + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        supply = 0;
        buy = 0;
    }
}
