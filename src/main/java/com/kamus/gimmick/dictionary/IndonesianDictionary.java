package com.kamus.gimmick.dictionary;

import com.kamus.gimmick.hashmap.GimmickHashMap;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class IndonesianDictionary {

    private GimmickHashMap map = new GimmickHashMap();

    public boolean loadAllData(String csv, GimmickHashMap gimmickMap) {
        try (CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(csv))) {
            Map<String, String> values;
            while ((values = reader.readMap()) != null) {
                String indo = values.get("indonesian");
                String eng = values.get("english");
                String gimmick = values.get("gimmick");
                if (gimmick == null) {
                    gimmick = "";
                }

                if (indo != null && eng != null) {
                    map.put(indo.toLowerCase(), eng.toLowerCase());
                    map.put(eng.toLowerCase(), indo.toLowerCase());

                    if (gimmick != null && !gimmick.isBlank()) {
                        gimmickMap.put(indo.toLowerCase(), gimmick);
                        gimmickMap.put(eng.toLowerCase(), gimmick);
                    }
                }
            }
            return true;
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String find(String word) {
        return map.getValue(word.toLowerCase());
    }

    public int getSize() {
        return map.size();
    }
}
