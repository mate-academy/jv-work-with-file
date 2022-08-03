package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int FIRST_ELEMENT_IN_LINE = 0;
    private static final int SECOND_ELEMENT_IN_LINE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File inputFile = new File(fromFileName);
        File outputFile = new File(toFileName);
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            String[] lineElements;
            while ((line = bufferedReader.readLine()) != null) {
                lineElements = line.split(",");
                switch (lineElements[FIRST_ELEMENT_IN_LINE]) {
                    case "supply":
                        supply += Integer.parseInt(lineElements[SECOND_ELEMENT_IN_LINE]);
                        break;
                    case "buy":
                        buy += Integer.parseInt(lineElements[SECOND_ELEMENT_IN_LINE]);
                        break;
                    default:
                        throw new RuntimeException("Input file not valid");
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileReader has problem to find file " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("BufferedReader has problem to read line in file " + fromFileName, e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            bufferedWriter.write("supply," + supply);
            bufferedWriter.write(System.lineSeparator() + "buy," + buy);
            bufferedWriter.write(System.lineSeparator() + "result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("We can not write file " + toFileName, e);
        }
    }
}
