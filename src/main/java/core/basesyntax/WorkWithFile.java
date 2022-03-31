package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        writer(toFileName, calculator(reader(fromFileName).split(" ")));
    }

    public String reader(String fromFileName) {
        File file1 = new File(fromFileName);
        StringBuilder data = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file1));) {
            String string = bufferedReader.readLine();
            while (string != null) {
                data.append(string).append(" ");
                string = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Some problems", e);
        }
        return data.toString();
    }

    public int[] calculator(String[] str) {
        int[] answer = new int[3];
        // answer[0] - supply
        // answer[1] - buy
        // answer[2] - result
        for (String data : str) {
            if (data.contains("supply")) {
                answer[0] += Integer.parseInt(data.trim().substring(7));
            } else if (data.contains("buy")) {
                answer[1] += Integer.parseInt(data.trim().substring(4));
            }
        }
        answer[2] = answer[0] - answer[1];
        return answer;
    }

    public void writer(String toFileName, int[] answer) {
        // answer[0] - supply
        // answer[1] - buy
        // answer[2] - result
        File file2 = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2, true))) {
            file2.createNewFile();
            bufferedWriter.write("supply," + answer[0] + System.lineSeparator()
                    + "buy," + answer[1] + System.lineSeparator() + "result," + answer[2]);
        } catch (IOException e) {
            throw new RuntimeException("Some problems", e);
        }
    }
}
