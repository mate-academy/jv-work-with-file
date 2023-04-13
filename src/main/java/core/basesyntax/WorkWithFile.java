package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SEPARATE = System.lineSeparator();
    public static final String SPLITTER = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFile(fromFileName);
        String report = generateReport(fileContent);
        writeToFile(report, toFileName);
    }

    private String readFile(String fileName) {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line)
                        .append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file " + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
        return fileContent.toString();
    }

    private String generateReport(String fileContent) {
        int supply = 0;
        int buy = 0;
        String[] lines = fileContent.split(SEPARATE);
        for (String line : lines) {
            String[] split = line.split(SPLITTER);
            if (split[0].equals(SUPPLY)) {
                supply += Integer.parseInt(split[1]);
            }
            if (split[0].equals(BUY)) {
                buy += Integer.parseInt(split[1]);
            }
        }
        int result = supply - buy;
        return "supply," + supply + SEPARATE
                + "buy," + buy + SEPARATE
                + "result," + result;
    }

    private void writeToFile(String report, String fileName) {
        File file = new File(fileName);
        BufferedWriter writer;
        try (FileWriter fileWriter = new FileWriter(file)) {
            writer = new BufferedWriter(fileWriter);
            writer.write(report);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + fileName, e);
        }
    }
}
