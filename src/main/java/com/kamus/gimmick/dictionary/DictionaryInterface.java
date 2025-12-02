package com.kamus.gimmick.dictionary;

import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DictionaryInterface {
    int loadFromCSV(String csv) throws IOException, CsvValidationException;
}