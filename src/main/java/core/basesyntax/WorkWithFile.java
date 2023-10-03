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
        String[] namesOfArticles = {"supply", "buy", "result"};
        File fromFile = new File(fromFileName);
        try (BufferedReader br = new BufferedReader(new FileReader(fromFile))) {
            int indexOfResult = namesOfArticles.length - 1;
            int[] values = new int[namesOfArticles.length];
            String text = br.readLine();
            while (text != null) {
                String[] tempArray = text.split(",");
                for (int i = 0; i < indexOfResult; ++i) {
                    if (tempArray[0].equals(namesOfArticles[i])) {
                        values[i] += Integer.valueOf(tempArray[1]);
                    }
                }
                text = br.readLine();
            }
            values[indexOfResult] = values[0] - values[1];
            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < namesOfArticles.length; ++i) {
                answer.append(namesOfArticles[i] + " ");
            }
            answer.append("\n");
            for (int i = 0; i < values.length; ++i) {
                answer.append(values[i] + " ");
            }
            return answer.toString();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("FileNotFoundException has happened");
        } catch (IOException ex) {
            throw new RuntimeException("IOException has happened");
        }
    }

    public String makeReport(String information) {
        StringBuilder report = new StringBuilder();
        String[] articles = information.split("\n")[0].split(" ");
        String[] values = information.split("\n")[1].split(" ");
        for (int i = 0; i < articles.length; i++) {
            report.append(articles[i] + "," + values[i] + "\n");
        }
        return report.toString();
    }

    public void writeToFile(String toFileName, String report) {
        File toFile = new File(toFileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFile, false))) {
            String[] info = report.split("\n");
            for (int i = 0; i < info.length; ++i) {
                bw.write(info[i]);
                bw.newLine();
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("FileNotFoundException has happened");
        } catch (IOException ex) {
            throw new RuntimeException("IOException has happened");
        }
    }
}
