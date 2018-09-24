import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Merger {
    static String fileIdInv = "c:/1.csv";
    static String fileToMerge = "c:/2.csv";
    static String mergeEntries = "C:\\Users\\las\\mergeentries.csv";
    static String newEnries = "C:\\Users\\las\\newentries.csv";

    static Map<String, String> mapIdInv = new HashMap<String, String>();
    static Map<String, String> mapToMerge = new HashMap<String, String>();



    public static void main (String[] args) throws IOException {
        //*************** Генерируем пары айди - инв.номер и пихаем в мапу  **************************//

        FileInputStream strIdInv = new FileInputStream(fileIdInv);
        int length = strIdInv.available();
        byte[] data1 = new byte[length];
        strIdInv.read(data1);
        String text = new String(data1);
        String[] lines = text.split("\n");
        for (String s : lines) {
            mapIdInv.put(s.substring(0,s.indexOf(';')), s.substring(s.indexOf(";")+1));
        }
        //******************* затащим вторую цсв в хэштейбл***********************//
        FileInputStream strMergeFile = new FileInputStream(fileToMerge);
        length = strMergeFile.available();
        byte[] data2 = new byte[length];
        strMergeFile.read(data2);
        text = new String(data2);
        lines = text.split("\n");
        for (String s : lines) {
            mapToMerge.put(s.substring(0,s.indexOf(';')), s.substring(s.indexOf(";")+1));
        }

        /*
        * Перебираем вторую мапу в поисках совпадений
        * с инвентарниками(ключами) в первой.
        * В случае совпадения строка мержится со значением из второй мапы и пишется в итоговый файл для дополнения.
        * Если такого же ключа нет, то строка пишется в другой итоговый файл, который для добавления.
        *
        */
        FileWriter strOutMerge = new FileWriter(mergeEntries, true);
        FileWriter strOutNew = new FileWriter(newEnries,true);

        for (Map.Entry<String, String> entry : mapToMerge.entrySet()) {
            if (mapIdInv.containsKey(entry.getKey())) {
                strOutMerge.write(mapIdInv.get(entry.getKey()) + entry.getValue() + "\n");
            } else {
                strOutNew.write(entry.getValue() + "\n");
            }
        }









    }







}




