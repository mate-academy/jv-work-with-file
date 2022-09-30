package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BuffWriter {
    private String fileToRead;
    private String fileToWrite;

    public void writeInFile() {
        CreateReportMessage message = new CreateReportMessage();
        try (BufferedWriter buffWrite = new BufferedWriter(new FileWriter(fileToWrite))) {
            String[] reportArray = message.getReportArray();
            for (String arr : reportArray) {
                buffWrite.write(arr);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not write in file", e);
        }
    }

    public String readFromFile() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                sb.append(value).append(WorkWithFile.SPACE);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read from file", e);
        }
        return sb.toString().trim();
    }

    public void setFileToRead(String fileToRead) {
        this.fileToRead = fileToRead;
    }

    public void setFileToWrite(String fileToWrite) {
        this.fileToWrite = fileToWrite;
    }
}
