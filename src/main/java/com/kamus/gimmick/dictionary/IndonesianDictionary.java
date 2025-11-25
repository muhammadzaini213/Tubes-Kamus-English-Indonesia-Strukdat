package com.kamus.gimmick.dictionary;

import com.kamus.gimmick.tree.RedBlackTree;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class IndonesianDictionary extends RedBlackTree implements DictionaryInterface {
    @Override
    public int loadFromCSV(String csv) throws IOException, CsvValidationException {
        CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(csv));
        Map<String, String> values;
        int count = 0;

        while ((values = reader.readMap()) != null) {
            String indonesian = values.get("indonesian");
            String english = values.get("english");

            if (indonesian != null && english != null) {
                if (insert(indonesian, english)) {
                    count++;
                }
            }
        }

        reader.close();
        return count;
    }
}
