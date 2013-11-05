package org.markvarabyou.ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class for getting symbols usage statistics.
 * Author: Mark Varabyou
 * Date: 10/19/13
 * Time: 9:54 PM
 */
public class SymbolsUsageStatistics {
    private String fileName;
    private HashMap<Character, Integer> symbolsUsage;

    public SymbolsUsageStatistics(String fileName) {
        this.fileName = fileName;
        symbolsUsage = new HashMap<Character, Integer>();
    }

    public void processFile(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            int symbol = reader.read();
            while (symbol != -1){
                saveSymbol((char)symbol);
                symbol = reader.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public HashMap<Character, Integer> getSymbolsUsage(){
        return new HashMap<Character, Integer>(symbolsUsage);
    }

    private void saveSymbol(char symbol){
        if (symbolsUsage.containsKey(symbol)) {
            int count = symbolsUsage.get(symbol);
            symbolsUsage.put(symbol, count + 1);
        } else {
            symbolsUsage.put(symbol, 1);
        }
    }
}
