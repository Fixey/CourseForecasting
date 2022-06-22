package ru.liga;

import ru.liga.exception.FailFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class FileReader {
    /**
     * Читает файл и возвращает данные в виде Листа
     *
     * @param path путь к файлу
     * @return данные файла в виде листа
     */

    public LinkedList<String> getListLinesFromFile(String path) {
        try (InputStream in = getClass().getResourceAsStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in));) {
            LinkedList<String> listLines = new LinkedList<>();
            String line= reader.readLine();
            while (line!=null){
                listLines.add(line);
                line= reader.readLine();
            }
            return listLines;
        } catch (IOException e) {
            throw new FailFileException();
        }
    }
}
