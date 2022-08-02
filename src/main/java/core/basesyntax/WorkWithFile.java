package core.basesyntax;

import core.basesyntax.services.ReportService;
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
            ReportService reportService = new ReportService();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                reportService.process(line);
            }
            bufferedWriter.write(reportService.getSumSupply());
            bufferedWriter.write(reportService.getSumBuy());
            bufferedWriter.write(reportService.getResult());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can not read file " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Fatal error i/o", e);
        }
    }
}
