package snowpost.rngshop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteFile {
    static BufferedWriter BuffWriter;
    static FileWriter printWriter;
    static File file;
    public void makeFile(String FileName) {
        String filename = FileName + ".csv";
        file = new File("src\\main\\resources\\snowpost\\rngshop\\Files\\Created\\" + filename);
        try {
            printWriter = new FileWriter(file);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Filewrite(String Additem) {
        try {
            printWriter = new FileWriter(file,true);
            BuffWriter = new BufferedWriter(printWriter); 
            BuffWriter.write(Additem + "\n");
            BuffWriter.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void SaveStore(String Additem) {
        try {
            printWriter = new FileWriter(new File("src\\main\\resources\\snowpost\\rngshop\\Files\\Protected\\Stores.csv"),true);
            BuffWriter = new BufferedWriter(printWriter);
            BuffWriter.write(Additem + "\n");
            BuffWriter.close();
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    static private PrintWriter appendFile;
    public BufferedWriter savewriter;
    public void saveStorerevisited(String[][] StoresfromFile){
        try {
            appendFile = new PrintWriter(new File("src\\main\\resources\\snowpost\\rngshop\\Files\\Protected\\Stores.csv"));
            savewriter = new BufferedWriter(appendFile);
            savewriter.flush();
            for(int i=0; i<StoresfromFile.length; i++){  //double check if it starts with new line
                savewriter.write(StoresfromFile[i][0] + "," + StoresfromFile[i][1] + "," + StoresfromFile[i][2] + "," + StoresfromFile[i][3]+ "\n");
            }
            savewriter.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }  
    public void DeleteStore(String goodbye, String[][] StoresfromFile){
        try{
            File delete = new File("src\\main\\resources\\snowpost\\rngshop\\Files\\Created\\" + goodbye + ".csv");
            delete.delete();
            appendFile = new PrintWriter(new File("src\\main\\resources\\snowpost\\rngshop\\Files\\Protected\\Stores.csv"));
            savewriter = new BufferedWriter(appendFile);
            savewriter.flush();
            for(int i=0; i<StoresfromFile.length; i++){  //double check if it starts with new line
                if(goodbye.equals(StoresfromFile[i][0])){
                    System.out.println("goodbye" + StoresfromFile[i][0]);
                }
                else{
                    savewriter.write(StoresfromFile[i][0] + "," + StoresfromFile[i][1] + "," + StoresfromFile[i][2] + "," + StoresfromFile[i][3]+ "\n");
                }
            }
            savewriter.close();
            
            
            
        }
        catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    
    
}
