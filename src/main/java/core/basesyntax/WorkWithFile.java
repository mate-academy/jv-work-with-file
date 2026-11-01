package core.basesyntax;

import java.io.*;

public class WorkWithFile {

    private static final int TYPE = 0;
    private static final int VALUE = 1;
    private static final String EMPTY_SPACE = " ";

    public void getStatistic(String fromFileName, String toFileName) {

        File from = new File(fromFileName);
        File to = new File(toFileName);

        String namesBeforeSplit = "";
        String valueBeforeSplit = "";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(from))) {

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] splittedLine = line.split(",");
                namesBeforeSplit += splittedLine[TYPE] + EMPTY_SPACE;
                valueBeforeSplit += splittedLine[VALUE] + EMPTY_SPACE;
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(to))) {

            String[] names = namesBeforeSplit.split(EMPTY_SPACE);
            String[] valuesString = valueBeforeSplit.split(EMPTY_SPACE);

            for (int i = 0; i < ; i++) {

            }

        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }

    }
}
