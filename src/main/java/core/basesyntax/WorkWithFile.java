package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private String fileName;
    private String toFileName;
    private int supply;
    private int buy;
    private int result;

    public void getStatistic(String fromFileName, String toFileName) {
        this.fileName = fromFileName;
        this.toFileName = toFileName;
        readFile(fromFileName);
        writer();
    }

    public String[] readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] valueFile;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String elementsInFile = reader.readLine();
            while (elementsInFile != null) {
                stringBuilder.append(elementsInFile).append(System.lineSeparator());
                elementsInFile = reader.readLine();
            }
            valueFile = stringBuilder.toString().split("\\W+");
            return valueFile;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    public int count() {
        String[] strings = readFile(fileName);
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals("supply")) {
                supply += Integer.parseInt(strings[i + 1]);
            } else if (strings[i].equals("buy")) {
                buy += Integer.parseInt(strings[i + 1]);
            }
        }
        return result = supply - buy;
    }

    public void writer() {
        count();
        File file = new File(toFileName);
        StringBuilder stringBuilder = new StringBuilder("supply,")
                .append(this.supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(result);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
