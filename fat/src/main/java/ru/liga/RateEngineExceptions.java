package ru.liga;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;

public class RateEngineExceptions extends IOException {
    public RateEngineExceptions(){
        super("Something with file. I can't open it!");
    }
}
