package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        if (fromFileName == null || fromFileName.isEmpty()
                || toFileName == null || toFileName.isEmpty()) {
            throw new RuntimeException("Invalid file");
        }
        String fileContent = readFile(fromFileName);
        String report = createReport(fileContent);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
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

    private String createReport(String content) {
        int buy = 0;
        int supply = 0;
        String[] separatedItems = content.split(System.lineSeparator());
        for (String item : separatedItems) {
            String[] splited = item.split(",");
            if (splited[0].equals("buy")) {
                buy += Integer.parseInt(splited[1]);
            } else {
                supply += Integer.parseInt(splited[1]);
            }
        }
        final int result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply," + supply + System.lineSeparator());
        stringBuilder.append("buy," + buy + System.lineSeparator());
        stringBuilder.append("result," + result + System.lineSeparator());
        return stringBuilder.toString();
    }

    private void writeToFile(String report, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: ", e);
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file: ", e);
        }
    }
}
