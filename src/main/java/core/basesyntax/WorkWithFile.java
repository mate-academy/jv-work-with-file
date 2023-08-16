package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private int supply;
    private int buy;
    private boolean isParsed = false;

    private int getResult() {
        return supply - buy;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        if (!isParsed) {
            parse(fromFileName);
            isParsed = true;
        }
        makeReport(toFileName);
    }

    public void parse(String fileName) {
        File fromFile = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String value = reader.readLine();
            String[] values;
            while (value != null) {
                values = value.split(",");
                if (values[0].equals("supply")) {
                    supply += Integer.parseInt(values[1]);
                }
                if (values[0].equals("buy")) {
                    buy += Integer.parseInt(values[1]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
    }

    public void makeReport(String fileName) {
        File fromFile = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fromFile))) {
            bufferedWriter.write(makeLine("supply", supply));
            bufferedWriter.write(makeLine("buy", buy));
            bufferedWriter.write(makeLine("result", getResult()));
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }

    public String makeLine(String name, int value) {
        return name + "," + value + System.lineSeparator();
    }
}

