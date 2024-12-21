package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] entries = getTableEntries(fromFileName);
        if(entries.length == 0 || entries[0].isEmpty()){
            return;
        }
        String[] reportData = getReportData(entries);
        clearFile(toFileName);
        for(String entry : reportData){
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName,
                    true))){
                bufferedWriter.write(entry + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can't write to this file: " + toFileName, e);
            }
        }
    }

    public void clearFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("");
        } catch (IOException e) {
            throw new RuntimeException("Error clearing file: " + fileName, e);
        }
    }

    public String[] getTableEntries(String fromFileName) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fromFileName))){
            StringBuilder data = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                data.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            return data.toString().split(System.lineSeparator());

        } catch (FileNotFoundException e) {
            throw new RuntimeException("There is no file with such name: " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file: " + fromFileName, e);
        }
    }

    public String[] getReportData(String[] tableData) {
        StringBuilder report = new StringBuilder();
        int buyAmount = 0;
        int supplyAmount = 0;
        for(String entry : tableData){
            String[] data = entry.split(",");
            if(data[0].equals("buy")){
                buyAmount += Integer.parseInt(data[1]);
            } else if(data[0].equals("supply")){
                supplyAmount += Integer.parseInt(data[1]);
            }
        }
        report.append("supply,").append(supplyAmount).append(System.lineSeparator())
                .append("buy,").append(buyAmount).append(System.lineSeparator())
                .append("result,").append(supplyAmount - buyAmount);
        return report.toString().split(System.lineSeparator());
    }
}
