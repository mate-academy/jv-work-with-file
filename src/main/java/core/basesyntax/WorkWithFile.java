package core.basesyntax;

import java.io.*;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) throws IOException {
        File fromFile = new File(fromFileName);
        int supplyCounter = 0;
        int buyCounter = 0;
        BufferedReader newBufferedReader = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int value = bufferedReader.read();
            while (value != -1) {
                String[] dataFromFile = (bufferedReader.readLine()).split(",");
                if (dataFromFile[0].equals("supply")) {
                    supplyCounter += Integer.getInteger(dataFromFile[1]);
                } else {
                    buyCounter += Integer.getInteger(dataFromFile[1]);
                }
                value = bufferedReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Read error for file " + fromFileName + "!", e);
        }
        String result = getResult(supplyCounter,buyCounter);
        fileWriter(toFileName, result);
    }
    private void fileWriter(String fileToWrite, String infoToWrite) {
        BufferedWriter startBufferedWriter = null;
        File toFile = new File(fileToWrite);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
            bufferedWriter.write(infoToWrite);
        } catch(IOException e){
            throw new RuntimeException("Write error for file " + fileToWrite + "!", e);
        }
    }
    private String getResult(int supply, int buy) {
        return ("supply, " + supply + System.lineSeparator() + "buy, " + buy
                + System.lineSeparator() + "result, " + (supply - buy));
    }
}

