package snowpost.rngshop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class ReadFile {
    File file;
    static public LinkedList<String> Iteamlist;
    public int[] Fileread(String StoreChoice) { //Reads the file and imports all items from Item type in to a linked list
        Iteamlist = new LinkedList<String>();
        int[] minMAX = new int[2];
        try {
            if(StoreChoice.equals("Gerenal Goods")){
                file = new File("src\\main\\resources\\snowpost\\rngshop\\Files\\Protected\\Ggoods.csv"); //Make files filled will all good, and have this set min and max vaules      
            }
            else if(StoreChoice.equals("Arcane Shop")){
                file = new File("src\\main\\resources\\snowpost\\rngshop\\Files\\Protected\\Magic.csv");
            }
            else if(StoreChoice.equals("BlackSmith")){
                file = new File("src\\main\\resources\\snowpost\\rngshop\\Files\\Protected\\BlackSmith.csv");
            }
            else if(StoreChoice.equals("Apothecary")){
                file = new File("src\\main\\resources\\snowpost\\rngshop\\Files\\Protected\\Apothecary.csv");
            }
            BufferedReader BuffReader = new BufferedReader(new FileReader(file));
            String line = " ";
            char let;
            String getminmax =  BuffReader.readLine();
            minMAX = new int [getminmax.length()];
            int add = 0;
            String addto ="";
            for(int i=0; i <getminmax.length();i++){
                let = getminmax.charAt(i);
                if(let != ' ' && i != getminmax.length()-2){
                    addto += String.valueOf(let);
                }
                else{
                    minMAX[add] = Integer.parseInt(addto);
                    addto ="";
                    add++;
                }
            }
            int num=0;
            while ((line = BuffReader.readLine()) != null){ //while loop to get items from csv
                Iteamlist.add(line);
                //System.out.println(Parse[0] +"    "+Parse[1]+"      "+Parse[2]);
                num++;
            }
            BuffReader.close();
        }
        catch (IOException e) {
                  System.out.println("An error occurred.");
                  e.printStackTrace();
        }
        //System.out.println(minMAX +"minMAX");
        return minMAX;
    }
    static String[] InveList;
    public String[] getInve(int min, int max) {//creates an array of items from linked list
        String[] Item =  new String [3];//what is returned
        Random rand = new Random();
        int Newitem; //The gened number
        Newitem = rand.nextInt((max - min) + 1) + min;//start of Random Gen
        //System.out.println("GET INVE"+"       "+ min +"       " + max +"       "+ Iteamlist.size()+ "     "+Newitem+"    "+Iteamlist.get(0)+"    "+ Iteamlist.get(max));
        Item = Iteamlist.get(Newitem).split(",");
         return Item;
    }
    public String getInve4change(int min, int max) {//creates an array of items from linked list
        String Item =  "";//what is returned
        Random rand = new Random();
        int Newitem; //The gened number
        Newitem = rand.nextInt((max - min) + 1) + min;//start of Random Gen//System.out.println("GET INVE"+"       "+ min +"       " + max +"       "+ Iteamlist.size()+ "     "+Newitem+"    "+Iteamlist.get(0)+"    "+ Iteamlist.get(max));
        Item = Iteamlist.get(Newitem);
         return Item;
    }
    
    LinkedList<String> FillerlinkedList;;
    public void openfiller(){
        FillerlinkedList = new LinkedList<String>();
        try {
            
            BufferedReader BuffReader = new BufferedReader(new FileReader("src\\main\\resources\\snowpost\\rngshop\\Files\\Protected\\Filler.csv"));
            String line = " ";
            while ((line = BuffReader.readLine()) != null){ //while loop to get items from csv
                FillerlinkedList.add(line);
            }
            BuffReader.close();
        }
        catch (IOException e) {
                  System.out.println("An error occurred.");
                  e.printStackTrace();
        }
    }
    public String Addfiller(){
        int min = 1; // Min
        int Newitem; //The gened number
        int max = 1199; //Max of Random gen
        String Item =  "";//what is returned
        Random rand = new Random();       

        Newitem = rand.nextInt((max - min) + 1) + min;//start of Random Gen
        Item = FillerlinkedList.get(Newitem);
        return Item;
    }
    static LinkedList<String> Storelist;
    public String[][] Storeread() { //For the combobox
          Storelist = new LinkedList<String>();
          String [][] OldStores = new String[0][0];
        try {
            File File1 = new File("src\\main\\resources\\snowpost\\rngshop\\Files\\Protected\\Stores.csv");
            BufferedReader BuffReader = new BufferedReader(new FileReader(File1));
            String line = "";
            while ((line = BuffReader.readLine()) != null){ //while loop to get items from csv
                String PUSHIN [] = line.split(",");
                Collections.addAll(Storelist, PUSHIN);
            }           
            Object[] objectAarray = Storelist.toArray();
            int length = objectAarray.length;
            OldStores = new String[length/4][4];
            int inter = 0;
            for(int i =0; i < length/4; i++) {
                String temp1 = (String) objectAarray[inter];
                String temp2 = (String) objectAarray[inter+1];
                String temp3 = (String) objectAarray[inter+2];
                String temp4 = (String) objectAarray[inter+3];
                OldStores[i][0] = temp1;
                OldStores[i][1] = temp2;
                OldStores[i][2] = temp3;
                OldStores[i][3] = temp4;
                inter = inter +4;
             }
            BuffReader.close();
            Storelist.clear();
            return OldStores;
        }
        catch (IOException e) {
                  System.out.println("An error occurred.");
                  e.printStackTrace();
        }
        return OldStores ;
    }
    static public LinkedList<String> Storeinventory;
    public void Readstore(String StoreName) {//Reads the file and imports all items from Item type in to a linked list
        Storeinventory = new LinkedList<String>();
        try {
            File files = new File("src\\main\\resources\\snowpost\\rngshop\\Files\\Created\\"+ StoreName + ".csv");
            BufferedReader BuffReader = new BufferedReader(new FileReader(files));
            String line = BuffReader.readLine();
            while ((line = BuffReader.readLine()) != null){ //while loop to get items from csv
                Storeinventory.add(line);
            }
            BuffReader.close();
        }
        catch (IOException e) {
                  System.out.println("An error occurred.");
                  e.printStackTrace();
        }
    }
    
    
}
