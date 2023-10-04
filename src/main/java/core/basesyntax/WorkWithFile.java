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
        String information = readFromFile(fromFileName);
        String report = makeReport(information);
        writeToFile(toFileName, report);
    }

    public String readFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        try (BufferedReader br = new BufferedReader(new FileReader(fromFile))) {
            StringBuilder answer = new StringBuilder();
            String text = br.readLine();
            while (text != null) {
                answer.append(text + "\n");
                text = br.readLine();
            }
            return answer.toString();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Can't read data from file" + fromFileName, ex);
        } catch (IOException ex) {
            throw new RuntimeException("IOException has happened in the file" + fromFileName, ex);
        }
    }

    public String makeReport(String information) {
        StringBuilder report = new StringBuilder();
        String[] strings = information.split("\n");
        String[] namesOfArticles = {"supply", "buy", "result"};
        int[] values = new int[namesOfArticles.length];
        int indexOfResult = namesOfArticles.length - 1;
        for (int i = 0; i < strings.length; ++i) {
            String[] tempArray = strings[i].split(",");
            for (int c = 0; c < indexOfResult; ++c) {
                if (tempArray[0].equals(namesOfArticles[c])) {
                    values[c] += Integer.valueOf(tempArray[1]);
                }
            }
        }
        values[indexOfResult] = values[0] - values[1];
        for (int i = 0; i < namesOfArticles.length; i++) {
            report.append(namesOfArticles[i] + "," + values[i]);
            if (i < namesOfArticles.length - 1) {
                report.append(System.lineSeparator());
            }
        }
        return report.toString();
    }

    public void writeToFile(String toFileName, String report) {
        File toFile = new File(toFileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFile))) {
            bw.write(report);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Can't write data to file" + toFileName, ex);
        } catch (IOException ex) {
            throw new RuntimeException("IOException has happened in the file" + toFileName, ex);
        }
    }
}
