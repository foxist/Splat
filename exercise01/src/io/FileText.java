package io;

import java.io.*;

public class FileText {

    public String isFindFile(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String str;
        StringBuilder string = new StringBuilder();

        while ((str = br.readLine()) != null) {
            string.append(str);
            string.append("\n");
        }
        return string.toString();
    }
}
