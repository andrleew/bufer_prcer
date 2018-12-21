import sources.Fam;
import sources.Indi;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.TreeMap;


public class Main {

    private static String output;
    private static TreeMap<String, String> mapindi;

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        File file = new File("file");
        mapindi = new TreeMap<>();
        output = "";
        BufferedOutputStream outputStream = new BufferedOutputStream(
                new FileOutputStream(
                        "res.pl"));
        BufferedInputStream input = new BufferedInputStream(
                new FileInputStream(
                        file));
        byte [] textFile = new byte[(int) file.length()];
        input.read(textFile);
        String[] text = null;
        String forSplit = new String(textFile, StandardCharsets.UTF_8);
        text = forSplit.split("\n0");
        textFile = null;
        long k = 0;
        for (String string : text){
            String[] pfrases = string.split("\\s");
            if (pfrases.length > 2 && pfrases[2].equals("INDI"))readIndi(pfrases);
            else if (pfrases.length > 2 && pfrases[2].equals("FAM")) readFam(pfrases);
            if (output.length() > 1024){
                outputStream.write(output.getBytes());
                output = "";
            }
        }
        outputStream.write(output.getBytes());
        outputStream.flush();
        outputStream.close();
        input.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }

    private static void readIndi(String[] word){
        Indi indi = new Indi();
        for (int i = 0; i < word.length; ++i) {
            if ("INDI".equals(word[i])) {
                indi.setInd(word[i - 1]);
            } else if ("SEX".equals(word[i])) {
                indi.setSex(word[i + 1].toCharArray()[0]);
            } else if ("GIVN".equals(word[i])) {
                indi.setGivn(word[i + 1]);
            }
        }
        mapindi.put(indi.getInd(), indi.getGivn());
        if (indi.getSex() == 'M') output += "male(\"" + indi.getGivn() + "\").\n";
        else output += "female(\"" + indi.getGivn() + "\").\n";
    }

    private static void readFam(String [] word){
        Fam fam = new Fam();
        for (int i = 0; i < word.length; ++i){
            if ("FAM".equals(word[i])) {
                fam.setFam(word[i - 1]);
            } else if ("HUSB".equals(word[i])) {
                fam.setHusbandIndi(word[i + 1]);
            } else if ("WIFE".equals(word[i])) {
                fam.setWifeIndi(word[i + 1]);
            } else if ("CHIL".equals(word[i])) {
                fam.getChildIndi().add(word[i + 1]);
            }
        }
        for (String s : fam.getChildIndi()){
            String name = mapindi.get(s);
            if(fam.getHusbandIndi() != null)
                output +=
                        "child(\""
                                + name
                                + "\", \""
                                + mapindi.get(fam.getHusbandIndi())
                                + "\").\n";
            if (fam.getWifeIndi() != null)
                output +=
                        "child(\""
                                + name
                                + "\", \""
                                + mapindi.get(fam.getWifeIndi())
                                + "\").\n";
        }
    }
}
