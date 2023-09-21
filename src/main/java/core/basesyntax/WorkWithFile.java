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
        File fromFile = new File(fromFileName);
        try (BufferedReader br = new BufferedReader(new FileReader(fromFile))) {
            String[] namesOfArticles = {"supply", "buy", "result"};
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
            writeStatistic(toFileName, namesOfArticles, values);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("FileNotFoundException has happened");
        } catch (IOException ex) {
            throw new RuntimeException("IOException has happened");
        }
    }

    public void writeStatistic(String toFileName, String[] namesOfArticles, int[] values) {
        File toFile = new File(toFileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFile, true))) {
            for (int i = 0; i < values.length; ++i) {
                StringBuilder answer = new StringBuilder();
                answer.append(namesOfArticles[i] + "," + values[i]);
                bw.write(answer.toString());
                bw.newLine();
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("FileNotFoundException has happened");
        } catch (IOException ex) {
            throw new RuntimeException("IOException has happened");
        }
    }
}
