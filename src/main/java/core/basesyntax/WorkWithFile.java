package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ACTION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SPLIT = ",";
    private int buy = 0;
    private int supply = 0;

    private void writeToFile(String toFileName) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(createResultString(supply,buy));

        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file: " + toFileName, e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String information;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {

            while ((information = bufferedReader.readLine()) != null) {
                String[] str = information.split(SPLIT);
                if (str[ACTION_INDEX].equals("supply")) {
                    supply += Integer.parseInt(str[AMOUNT_INDEX]);
                } else {
                    buy += Integer.parseInt(str[AMOUNT_INDEX]);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file: " + fromFileName, e);
        }
        writeToFile(toFileName);
    }

    public String createResultString(int supply, int buy) {
        int result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply," + supply + System.lineSeparator())
                .append("buy," + buy + System.lineSeparator())
                .append("result," + result + System.lineSeparator());
        return stringBuilder.toString();
    }
}







