package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFileName = dataFromFileName(fromFileName);
        writerToFile(dataFromFileName, toFileName);
    }

    public String dataFromFileName(String fromFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] resultNumbers = value.split(",");
                if (resultNumbers[0].equals("supply")) {
                    supply += Integer.parseInt(resultNumbers[1]);
                }
                if (resultNumbers[0].equals("buy")) {
                    buy += Integer.parseInt(resultNumbers[1]);
                }
                value = bufferedReader.readLine();
            }
            result = supply - buy;

        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
        return "supply," + supply
                + System.lineSeparator()
                + "buy," + buy
                + System.lineSeparator()
                + "result," + result;
    }

    public void writerToFile(String resultData, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(resultData);
        } catch (IOException e) {
            throw new RuntimeException("Can't open file");
        }
    }

}
