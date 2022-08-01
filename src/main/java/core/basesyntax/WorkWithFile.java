package core.basesyntax;

import core.basesyntax.services.ParseLine;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File inFile = new File(fromFileName);
        File outFile = new File(toFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inFile));
                 BufferedWriter bufferedWriter = new BufferedWriter(
                         new FileWriter(outFile, true))) {
            ParseLine parseLine = new ParseLine();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                parseLine.run(line);
            }
            bufferedWriter.write(parseLine.printSumSupply());
            bufferedWriter.write(parseLine.printSumBuy());
            bufferedWriter.write(parseLine.printResult());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can not read file " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Fatal error i/o", e);
        }
    }
}
