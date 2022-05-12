package core.basesyntax;

import java.io.*;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int[] arr = new int[2];
        try (BufferedReader buffer = new BufferedReader(new FileReader(fromFileName))) {
            String line = buffer.readLine();
            while (line != null) {
                if (line.substring(0, line.indexOf(',')).equals("supply")) {
                    arr[0] += Integer.parseInt(line.substring(line.indexOf(',') + 1));
                } else {
                    arr[1] += Integer.parseInt(line.substring(line.indexOf(',') + 1));
                }
                line = buffer.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + arr[0] + System.lineSeparator());
            bufferedWriter.write("buy," + arr[1] + System.lineSeparator());
            bufferedWriter.write("result," + (arr[0] - arr[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
