package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String[] line = stringBuilder.append(value).toString().trim().split("\\W+");
                switch (line[0]) {
                    case "supply":
                        supply += Integer.parseInt(line[1]);
                        break;
                    case "buy":
                        buy += Integer.parseInt(line[1]);
                        break;
                    default:
                }
                stringBuilder.setLength(0);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file" + e);
        }
        writeToFile(toFileName,createResultReport(buy,supply));
    }

    private String createResultReport(int buy, int supply) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy)).toString();
    }

    private void writeToFile(String fileName,String text) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Wrong file " + e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true))) {
            bufferedWriter.write(text);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Wrong file" + e);
        }
    }
}
