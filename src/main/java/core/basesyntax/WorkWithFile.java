package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                 BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            int supply = 0;
            int buy = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] files = line.split(",");
                if (files[OPERATION].equals("supply")) {
                    supply += Integer.parseInt(files[AMOUNT]);
                } else if (files[OPERATION].equals("buy")) {
                    buy += Integer.parseInt(files[AMOUNT]);
                }
            }
            int result = supply - buy;

            bufferedWriter.write("supply," + supply);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buy);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + result);

        } catch (IOException e) {
            throw new RuntimeException("Can`t read/write files", e);
        }
    }
}
