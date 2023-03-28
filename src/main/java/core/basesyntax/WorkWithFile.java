package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = fileToString(fromFileName);
        String[] separatedItems = fileContent.split("\n");
        int buy = 0;
        int supply = 0;
        for (String item : separatedItems) {
            String[] splited = item.split(",");
            splited[1] = splited[1].substring(0, splited[1].indexOf("\r"));
            if (splited[0].equals("buy")) {
                buy += Integer.parseInt(splited[1]);
            } else {
                supply += Integer.parseInt(splited[1]);
            }
        }
        final int result = supply - buy;
        createReport(buy, supply, result, toFileName);
    }

    private String fileToString(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(fromFileName);
        try (FileReader fileReader = new FileReader(file)) {
            int content;
            while ((content = fileReader.read()) != -1) {
                stringBuilder.append((char) content);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString();
    }

    private void createReport(int buy, int supply, int result, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator());
            bufferedWriter.write("buy," + buy + System.lineSeparator());
            bufferedWriter.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't make report", e);
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file");
        }
    }
}
