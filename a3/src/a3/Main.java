package a3;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {
    /**
     * Replace "-1" by the time you spent on A3 in hours.
     * Example: for 3 hours 15 minutes, use 3.25
     * Example: for 4 hours 30 minutes, use 4.50
     * Example: for 5 hours, use 5 or 5.0
     */
    public static double timeSpent = 16;

    /* TODO 6
     * Write a function that takes in a csv file name as a string and returns a Linked List
     * representation of the CSV table.
     */
    public static LList<LList<String>> csvToList (String file) {
        LList<LList<String>> newlist = new SLinkedList<>();
        try( Reader r = new FileReader(file);
                BufferedReader br = new BufferedReader(r)) {

            String str = "";
            while((str = br.readLine()) != null) {
                String[] content = str.split(",");
                LList<String> temp = new SLinkedList<>();
                for( int i = 0; i < content.length; i++) {
                    temp.append(content[i] );
                }
                newlist.append(temp);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e)  {
            throw new RuntimeException(e);
        }
        return newlist;
    }



    /* TODO 7
     * Write a function that takes in two lists representing tables and
     * computes their left join.
     */
    public static LList<LList<String>> join(LList<LList<String>> table1,
            LList<LList<String>> table2) {
        LList<LList<String>> list = new SLinkedList<>();
        for (int i = 0; i < table1.size(); i++) {
            boolean found = false;
            for (int j = 0; j < table2.size(); j++) {
                LList<String> temp = new SLinkedList<>();
                for (int k = 0; k < table1.get(i).size(); k++) {
                    temp.append(table1.get(i).get(k));
                }
                if (table1.get(i).get(0).equals(table2.get(j).get(0))) {
                    for (int l = 1; l < table2.get(j).size(); l++) {
                        temp.append(table2.get(j).get(l));
                    }
                    list.append(temp);
                    found = true;
                }
            }
        if (!found) {
            list.append(table1.get(i));
        }
    }


//        for (int i = 0; i < table1.size(); i++) {
//            for (int j = 0; j < table2.size(); j++) {
//
//                if (table1.get(i).get(0).equals(table2.get(j).get(0))) {
//
//                    for (int k = 1; k < table2.get(j).size(); k++) {
//                        list.get(i).append(table2.get(j).get(k));
//                    }
//                }
//            }
//        }


        //TODO
        return list;
    }

    /**
     * Takes in a table represented by nested linked lists and converts the table into a string in
     * CSV format
     * @param table
     * @return returns a string containing the elements of the table separated by commas in CSV
     * format
     */
    public static String listToCSV(LList<LList<String>> table) {
        String s = "";
        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).size(); j++) {
                s += table.get(i).get(j) + ",";
            }
            s = s.substring(0, s.length() - 1);
            if(i != table.size() - 1) s+= "\n";
        }

        return s;
    }
    /** Effect: Print a usage message to standard error. */
    public static void usage() {
        System.err.println("Usage: a3.Main <file1.csv> <file2.csv>");
    }

    /* TODO 8
     * Write the main method, which parses the command line arguments, reads CSV files
     * into tables, and prints out the resulting table resulting from a left join of the
     * input tables. Hint: use helper methods.
     */
    public static void main(String[] args) throws IOException {
        try {
            System.out.print(listToCSV(join(csvToList(args[0]), csvToList(args[1]))));
        }
        catch(ArrayIndexOutOfBoundsException e) {
            usage();
        }
    }
}
