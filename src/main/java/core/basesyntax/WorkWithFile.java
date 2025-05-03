package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        int supplyCounter = 0;
        int buyCounter = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String lineFromFile = bufferedReader.readLine();
            while (lineFromFile != null) {
                String[] dataFromFile = lineFromFile.split(",");
                if (dataFromFile[0].equals("supply")) {
                    supplyCounter += Integer.parseInt(dataFromFile[1]);
                } else {
                    buyCounter += Integer.parseInt(dataFromFile[1]);
                }
                lineFromFile = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Read error from file " + fromFileName + "!", e);
        }
        String result = getResult(supplyCounter,buyCounter);
        fileWriter(toFileName, result);
    }

    private void fileWriter(String fileToWrite, String infoToWrite) {
        BufferedWriter startBufferedWriter = null;
        File toFile = new File(fileToWrite);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
            bufferedWriter.write(infoToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Write error to file " + fileToWrite + "!", e);
        }
    }
    
    private String getResult(int supply, int buy) {
        return ("supply," + supply + System.lineSeparator() + "buy," + buy
                + System.lineSeparator() + "result," + (supply - buy));
    }
}

