package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = createReport(data);
        writeDataToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder inputString = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int character;
            while ((character = bufferedReader.read()) != -1) {
                inputString.append((char) character);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inputString.toString();
    }

    private String createReport(String data) {
        int buy = 0;
        int supply = 0;
        String[] stringsFirst = data.split("\n");
        for (String s : stringsFirst) {
            String[] stringsSecond = s.split(",");
            if (stringsSecond[0].equals("buy")) {
                buy += Integer.parseInt(stringsSecond[1]);
            } else {
                supply += Integer.parseInt(stringsSecond[1]);
            }
        }
        int result = supply - buy;
        return String.format("supply,%s\nbuy,%s\nresult,%s", supply, buy, result);
    }

    private void writeDataToFile(String dataResult, String toFileName) {
        File fileName = new File(toFileName);
        try {
            fileName.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (int i = 0; i < dataResult.length(); i++) {
                bufferedWriter.write(dataResult.charAt(i));
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
