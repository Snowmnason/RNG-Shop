package snowpost.rngshop;

import java.util.LinkedList;
import java.util.Random;

public class ShopRNG {
    double Per = .85;
    //Calling the classes
    //Txt items
    String Weapon;
    String Armors;
    String Magic;
    String Filler;
    String Agear;
    String Ggoods;
    String Rgoods;
    String Tools;
    double CommonCheck = 100;		// 40,  80,  120, 160,  240,  320, 400
    double UncommonCheck = 40;		// 15,  30,  45,  60,   90,   120, 150
    double RareCheck = 10;		// 2.2, 4.4, 6.6, 8.8,  13.2, 17.6,22  
    double VRareCheck = 5;		// 1.5, 3,   4.5, 6,    9,    12,  15
    double LegendaryCheck = 2;                             // 1.1, 2.2, 3.3, 4.4,  6.6,  8.8, 11 
    double ArtifactCheck = .2;                                      // .2, .4,   .6,  .8,   1.2,  1.6, 2
    //Rarity
    int Generic = 0; 
    int Common = 0; 
    int Uncommon = 0;  
    int Rare = 0;  
    int VRare = 0; 
    int Legendary = 0; 
    int Artifact = 0;
   double multi = 1;
   static int Fillamount = 0;
   Random rand = new Random(); //instance of random class
   ReadFile read = new ReadFile(); //Opens Readfile
   
   //Get Multiplier					//min max items
   public String[][] setCitysize(String citysize, String StoreType, Boolean AddFill){
       if(citysize.equals("Hamlet")) {                      multi = 1;                }//20-30   
       else if(citysize.equals("Village")) {                       multi = 2;                }//40-60 
       else if(citysize.equals("Town")) {                      multi = 3;                }//70-100  
       else if(citysize.equals("Large town")) {                        multi = 4;                }//100-140
       else if(citysize.equals("City")) {                   multi = 6;                }//140-190     
       else if(citysize.equals("Large City")) {                     multi = 8;                }//190-250   
       else if(citysize.equals("Metropolis")) {                        multi = 10;                }//250-320					 			
        //Set what items can be found
       CommonCheck *= multi;	UncommonCheck *=  multi;     RareCheck *=  multi;	VRareCheck *=  multi;   LegendaryCheck *=  multi;   ArtifactCheck *= multi;		 
        //find how many items to print
       int max = (int) (multi * 10);
       int min = (int) (multi * 5);
       int Amount = rand.nextInt((max - min) + 1) + min;		
            //loop for all the items
            for(int i = 0; i < Amount; i++) {
                double Per = rand.nextDouble();
                Per = Math.round(Per * 1000) / 10d;
                if(ArtifactCheck >= Per) {                                Artifact++;                        }
                else if(LegendaryCheck >= Per) {                                Legendary++;                        }
                else if(VRareCheck >= Per) {                                VRare++;                        }
                else if(RareCheck >= Per) {                                Rare++;                        }
                else if(UncommonCheck >= Per) {                                Uncommon++;                        }
                else if(CommonCheck >= Per) {                                Common++;                        }
                
            }
            Fillamount = 0;
            if(AddFill){
                int Fillmax = Amount/2;
                int Fillmin = Amount/4;
                Fillamount = rand.nextInt((Fillmax - Fillmin) + 1) + Fillmin;
                Amount = Amount + Fillamount;
            }
            
            int[] MINmax = read.Fileread(StoreType);
            String[][] Inevetory = new String[Amount][3];
            String[] Toadd = new String[3];
            int minforfile = 1;
            int maxforfile = 40;
            for(int i=0;i < Amount; i++){ //Start of While Loop to creat inventory 
            if(Common != 0){
                 minforfile = MINmax[0];
                 maxforfile = MINmax[1];
                 Common--;
             }
            else if(Uncommon != 0){
                minforfile = MINmax[2];
                maxforfile = MINmax[3];

                Uncommon--;
             }
             else if(Rare != 0){
                 minforfile = MINmax[4];
                 maxforfile = MINmax[5];

                 Rare--;
             }
             else if(VRare != 0){
                 minforfile = MINmax[6];
                 maxforfile = MINmax[7];
                 VRare--;
             }
             else if(Legendary != 0){
                 minforfile = MINmax[8];
                 maxforfile = MINmax[9];
                 Legendary--;
             }
             else if(Artifact != 0){
                 minforfile = MINmax[10];
                 maxforfile = MINmax[11];
                 Artifact--;
             }
                 Toadd = read.getInve(minforfile-1, maxforfile-1);
                 Inevetory[i] = Toadd;
                 if(Inevetory[i][0].startsWith("Luck") && StoreType.equals("BlackSmith")){
                      int rolled = rand.nextInt((4 - 1) + 1) + 1;
                      Inevetory[i][0] += "  " + rolled + " charges";
                 }
                 if(Inevetory[i][0].startsWith("Nine") && StoreType.equals("BlackSmith")){
                      int rolled = rand.nextInt((8 - 1) + 1) + 1;
                      rolled++;
                      Inevetory[i][0] += "  " + rolled + " charges";
                 }
                 
                 //System.out.println(Inevetory[i] + "    INEVETORY");
            }
            if(Fillamount != 0 || AddFill){
                read.openfiller();
                int indexAt = Amount - Fillamount;
                for(int i=0; i<Fillamount;i++){
                    Inevetory[indexAt][0] = read.Addfiller().split(",")[0];
                    Inevetory[indexAt][1] = " ";
                    Inevetory[indexAt][2] = " ";
                    indexAt++;
                }
                read.FillerlinkedList.clear();
            }
            
            
             
             read.Iteamlist.clear();
            return Inevetory;

     }
        public String[][] ChaneInve(String[] Info){
            
            int[] MINmax =read.Fileread(Info[2]);//Makes sure I get the right list of item and gets items in linked list

            read.Readstore(Info[0]);//Enters the Shop Name and gets items in linked list
            String CitySize = Info[1]; //Takes city size
            int Timeamount = Integer.parseInt(Info[3]);  //Make mulitple from user input
            String Timepassed = Info[4]; //Takes tha amount of time that passed from combo box hours days weeks months
            int checkFill = Integer.parseInt(Info[5]);
            
            int Timechanged = 1;
            if(Timepassed.equals("Hours")){                Timechanged = Timeamount *2;            }
            else if(Timepassed.equals("Days")){                Timechanged = Timeamount *3;            }
            else if(Timepassed.equals("Weeks")){                Timechanged = Timeamount *5;            }
            else if(Timepassed.equals("Months")){               Timechanged = Timeamount *8;            }
            int ItemChange = 1;
            if(CitySize.equals("Hamlet")) {                      ItemChange = 2*Timechanged;                }//20-30   
            else if(CitySize.equals("Village")) {                       ItemChange = 2*Timechanged;                }//40-60 
            else if(CitySize.equals("Town")) {                      ItemChange = 3*Timechanged;                }//70-100  
            else if(CitySize.equals("Large town")) {                        ItemChange = 4*Timechanged;                }//100-140
            else if(CitySize.equals("City")) {                   ItemChange = 6*Timechanged;                }//140-190     
            else if(CitySize.equals("Large City")) {                     ItemChange = 8*Timechanged;                }//190-250   
            else if(CitySize.equals("Metropolis")) {                        ItemChange = 10*Timechanged;                }//250-320					 			
            //Set what items can be found

            int max1 = (int) (ItemChange+2);
            int min1 = (1);
            int Changed = rand.nextInt((max1 - min1) + 1) + min1;
            
            
            int chance = 0;
            if(CitySize.equals("Hamlet")) {                      multi = 1;        chance = 8;        }//20-30   
            else if(CitySize.equals("Village")) {                       multi = 2;           chance = 7;      }//40-60 
            else if(CitySize.equals("Town")) {                      multi = 3;           chance = 6;      }//70-100  
            else if(CitySize.equals("Large town")) {                        multi = 4;          chance = 4;       }//100-140
            else if(CitySize.equals("City")) {                   multi = 6;             chance = 3;    }//140-190     
            else if(CitySize.equals("Large City")) {                     multi = 8;          chance = 2;       }//190-250   
            else if(CitySize.equals("Metropolis")) {                        multi = 10;       chance = 1;         }//250-320					 			
            //Set what items can be found		
            CommonCheck *= multi;	UncommonCheck *=  multi;     RareCheck *=  multi;	VRareCheck *=  multi;   LegendaryCheck *=  multi;   ArtifactCheck *= multi;		 
            //find how many items to print
            for(int i = 0; i < Changed; i++) {
                double Per = rand.nextDouble();
                Per = Math.round(Per * 1000) / 10d;
                if(ArtifactCheck >= Per) {                                Artifact++;                        }
                else if(LegendaryCheck >= Per) {                                Legendary++;                        }
                else if(VRareCheck >= Per) {                                VRare++;                        }
                else if(RareCheck >= Per) {                                Rare++;                        }
                else if(UncommonCheck >= Per) {                                Uncommon++;                        }
                else if(CommonCheck >= Per) {                                Common++;                        } 
            }
            
            Fillamount = 0;
            if(checkFill != 0){
                Fillamount =checkFill;
                Changed = Changed + Fillamount;
            }
            
            
            
            
            LinkedList<String> ChangesLinked = new LinkedList<String>();
            String Toadd = "";
            int minforfile = 1;
            int maxforfile = 40;
            for(int i=0;i < Changed; i++){ //Start of While Loop to creat inventory 
            if(Common != 0){
                 minforfile = MINmax[0];
                 maxforfile = MINmax[11];
                 Common--;
             }
            else if(Uncommon != 0){
                minforfile = MINmax[2];
                maxforfile = MINmax[3];
                Uncommon--;
             }
             else if(Rare != 0){
                 minforfile = MINmax[4];
                 maxforfile = MINmax[5];
                 Rare--;
             }
             else if(VRare != 0){
                 minforfile = MINmax[6];
                 maxforfile = MINmax[7];
                 VRare--;
             }
             else if(Legendary != 0){
                 minforfile = MINmax[8];
                 maxforfile = MINmax[9];
                 Legendary--;
             }
             else if(Artifact != 0){
                 minforfile = MINmax[10];
                 maxforfile = MINmax[11];
                 Artifact--;
             }
                 Toadd = read.getInve4change(minforfile-1, maxforfile-1); //read.getInve(minforfile-1, maxforfile-1); //change Cahnges to Toadd
                 ChangesLinked.add(i,Toadd);
            }
            int total = 0;
            int Newitem; //The gened number
            int Olditem;
            int min = 0;
            int max = ChangesLinked.size()-1;
            
             if(Fillamount != 0 || checkFill != 0){
             read.openfiller();
             int Loop = Changed - Fillamount;
             while(Loop != total){ //Start of While Loop to creat inventory  
                        max1 = read.Storeinventory.size()-Fillamount-2;                
                        int whathappens = rand.nextInt((300 - 1) + 1) + 1;
                        Newitem = rand.nextInt((max - min) + 1) + min;//start of Random Gen
                        Olditem = rand.nextInt((max1 - min) + 1) + min;
                        if(whathappens%(chance+6) == 0 ){ //Striaght remove
                            read.Storeinventory.remove(Olditem);
                        }
                        else if(whathappens%(chance*2) == 0) { //Straight Replace
                            String replace = ChangesLinked.get(Newitem);
                            read.Storeinventory.set(Olditem, replace);
                        }
                        else if(whathappens %(14/chance) == 0){
                            //Nothing happens
                        }
                        else{ //Straight Add
                            read.Storeinventory.add(max1, ChangesLinked.get(Newitem));
                        }   
                           total++;
                   }          
             while(Changed != total){ //Start of While Loop to creat inventory  
                        max1 = read.Storeinventory.size()-1;                
                        min1 = read.Storeinventory.size()-Fillamount;
                        int whathappens = rand.nextInt((300 - 1) + 1) + 1;
                        Olditem = rand.nextInt((max1 - min1) + 1) + min1;
                        if(whathappens%(chance*8) == 0 && false){ //Striaght remove
                            read.Storeinventory.remove(Olditem);
                        }
                        else if(whathappens%(chance*2) == 0) { //Straight Replace
                            String replace = read.Addfiller();
                            System.out.println(read.Storeinventory.get(Olditem));
                            read.Storeinventory.set(Olditem, replace);
                            
                        }
                        else if(whathappens %(14/chance) == 0){
                            //Nothing happens
                        }
                        else{ //Straight Add
                            read.Storeinventory.add(max1,read.Addfiller());
                        }   
                           total++;
                   }
                   read.FillerlinkedList.clear();
             }
             else{
                while(Changed != total){ //Start of While Loop to creat inventory   without Filler
                        max1 = read.Storeinventory.size();                
                        int whathappens = rand.nextInt((300 - 1) + 1) + 1;
                        Newitem = rand.nextInt((max - min) + 1) + min;//start of Random Gen
                        Olditem = rand.nextInt((max1 - min) + 1) + min;
                        if(whathappens%(chance+6) == 0 ){ //Striaght remove
                            read.Storeinventory.remove(Olditem);
                        }
                        else if(whathappens%(chance*2) == 0) { //Straight Replace
                            String replace = ChangesLinked.get(Newitem);
                            read.Storeinventory.set(Olditem, replace);
                        }
                        else if(whathappens %(14/chance) == 0){
                            //Nothing happens
                        }
                        else{ //Straight Add
                            read.Storeinventory.add(max1, ChangesLinked.get(Newitem));
                        }   
                           total++;
                   }         
             }
            Object[] goodByelinked =  read.Storeinventory.toArray();
            int length = goodByelinked.length;
            String[] Changes = new String [length];
            String[][] changesReturn = new String[length][3];
            for(int i =0; i < length; i++) {
                Changes[i] = (String) goodByelinked[i];
             }
            read.Storeinventory.clear();
            ChangesLinked.clear();
            char let;
            String addto ="";
            int add = 0;
            for(int j=0; j <Changes.length;j++){
                String INDEXED = Changes[j];
                for(int i=0; i <INDEXED.length();i++){
                    let = INDEXED.charAt(i);
                    if(let != ',' && i != INDEXED.length()-1 && Changes[j] != null){
                        addto += String.valueOf(let);
                    }
                    else if(Changes[j] == null){
                        changesReturn[j][add] = " ";
                        add++;
                    }
                    else if( i == INDEXED.length()-1){
                        addto += String.valueOf(let);
                        changesReturn[j][add] = addto;
                        addto ="";
                        add++;
                    }
                    else{
                        changesReturn[j][add] = addto;
                        addto ="";
                        add++;
                        }
                    }
                 add = 0;
                 }   
            
            return changesReturn;
        }
        public int getFillamount(){
            return Fillamount;
        }
}
/*   Black Smith/Armory  General Store Potion Shop Arcane Shop Break up rarities */
