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
        File toFile = new File(toFileName);

        try {
            toFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            String inputLine = bufferedReader.readLine();

            int bought = 0;
            int supplied = 0;

            while (inputLine != null) {
                String[] splitLine = inputLine.split(",");

                if (splitLine[0].equals("supply")) {
                    supplied = Integer.parseInt(splitLine[1]) + supplied;
                } else {
                    bought = Integer.parseInt(splitLine[1]) + bought;
                }

                inputLine = bufferedReader.readLine();
            }

            int resultNumber = supplied - bought;

            bufferedWriter.write("supply" + "," + supplied);
            bufferedWriter.newLine();

            bufferedWriter.write("buy" + "," + bought);
            bufferedWriter.newLine();

            bufferedWriter.write("result" + "," + resultNumber);
            bufferedWriter.newLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
