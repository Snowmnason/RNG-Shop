package snowpost.rngshop;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class MainFrameController implements Initializable{
        @FXML	
    private RadioButton hamletRB, villageRB, townRB, lTownRB, cityRB, lCityRB, metropolisRB;
        @FXML
    private ComboBox <String> shoptypeCMB, shopnameCMB, timeCMB,deletionCMB;
        @FXML
    private Button enter, deleteBTN;
        @FXML
    public TextField shoptxt, textTime;
        @FXML
    private VBox shopchoiceVB, makeshopVB;
        @FXML 
    private Pane revisitshopPN, buttonListPN, deletePN, inShopPN;
        @FXML
    public Text title;
        @FXML
    private CheckBox addFillerCB;
        @FXML
    private MenuItem aboutMI, saveMI, deleteMI;
    //Virables
    static private String timeSelected;
    static String storesfromfile [][];
    public String storesonfile;
    static public String storeType;
    private String selected = "NONE";
    static String citySize;
    static private String [][] storeinviList;
    private  ObservableList<ShopKeep> Itemobesrv; 
    int fillAmount = 0;
    LinkedList<String> stockList;
    ObservableList<String> storeNames;
    WriteFile filewrite = new WriteFile();
    ShopRNG shoping = new ShopRNG();
    ReadFile Fileread = new ReadFile();
    
    @Override
    public void initialize(URL argo0, ResourceBundle arg1) {
        shoptypeCMB.getItems().addAll("Gerenal Goods","BlackSmith","Apothecary","Arcane Shop");
        shoptypeCMB.setOnAction(this::getShop);
        
        timeCMB.getItems().addAll("Hours", "Days", "Weeks", "Months");
        timeCMB.setOnAction(this::getRevisit);
        
        storesfromfile = Fileread.Storeread();
        storeNames = FXCollections.observableArrayList();
        for(int i=0; i<storesfromfile.length; i++){
            storeNames.add(storesfromfile[i][0]);
          }
        shopnameCMB.getItems().addAll(storeNames);
        shopnameCMB.setOnAction(this::setRevisit);
    }
    
    public void getShop(Event event){//Creating store:::: City type, Store type
         storeType = shoptypeCMB.getValue();
         shoptxt.setDisable(false);
         enter.setDisable(false);
    }
    
    public void getRevisit(Event event){
        timeSelected = timeCMB.getValue();
        shopnameCMB.setDisable(false);
    }
    
    public void setRevisit(Event event){
        storesonfile = shopnameCMB.getValue();
        shoptxt.setText(storesonfile);
        enter.setDisable(false);
    }

    @FXML
    private void newShop() { //Selecting which Menu to open, New store
        makeshopVB.setVisible(true); //Vbox Radio Buttons
        shopchoiceVB.setVisible(false); //Vbox New or Old Shop button
        selected = "New";
        }
    
    @FXML
    private void oldShop () { //Selecting which Menu to open, Revisiting a store
        revisitshopPN.setVisible(true); //Pane Textfield comboboxs  
        shopchoiceVB.setVisible(false); //Vbox New or Old Shop button
        selected = "Old";
    }
    
    @FXML
    public void onEnter(){ //on Enter for Making shop       
        if(hamletRB.isSelected()){                citySize = "Hamlet";        }
        else if(villageRB.isSelected()){                citySize = "Village";        }
        else if(townRB.isSelected()){                citySize = "Town";        }
        else if(lTownRB.isSelected()){                citySize = "Large town";        }
        else if(cityRB.isSelected()){                citySize = "City";   }
        else if(lCityRB.isSelected()){                citySize = "Large City";    }
        else if(metropolisRB.isSelected()){                citySize = "Metropolis";        }
        Ininventory.setVisible(true);
        shoptxt.setDisable(true); //Make Text **hyperlink** or Clickable label
        enter.setDisable(true);
        title.setText(shoptxt.getText() + "'s Inventory List");
        if(addFillerCB.isSelected()){
            //StoresinviList = Filladder(StoresinviList);
            //Fillamount = Filladder();
        }
        if(selected.equals("New")){
            storeinviList = shoping.setCitysize(citySize, storeType,addFillerCB.isSelected());
        }
        else if(selected.equals("Old")){ 
            String [] storeOld = new String[storesfromfile.length+5];
            for(int i=0; i<storesfromfile.length; i++){//Finds the store from a file
                if(shoptxt.getText().equals(storesfromfile[i][0])){
                    storeOld[0] = storesfromfile[i][0];//Shop Name
                    storeOld[1] = storesfromfile[i][1];//Citysize
                    storeOld[2] = storesfromfile[i][2];//Type of Shop
                    storeOld[5] = storesfromfile[i][3];//If its has filler or not
                    break;
                }
            }
            String getTime = textTime.getText();
            int time = 1;
            try {
                time = Integer.parseInt(getTime);
            } catch (Exception e) {
                getTime = "2";
                }
            storeOld[3] = getTime; //Need to make error message if not int (amount of time that passed
            storeOld[4] = timeSelected; //The mulitple for time
            storeinviList = shoping.ChaneInve(storeOld); //Gets items from the file
        }        
        createTable();
        buttonListPN.setVisible(false);
        inShopPN.setVisible(true);
        saveMI.setDisable(false);
    }
        @FXML 
    private TableView <ShopKeep>Ininventory;
        @FXML 
    private TableColumn <ShopKeep, Number> stockTCM;
        @FXML 
    private TableColumn <ShopKeep, String> itemnameTCM;
        @FXML 
    private TableColumn <ShopKeep, String> priceTCM;
        @FXML 
    private void createTable() {
        this.Itemobesrv  = FXCollections.observableArrayList();
        stockList = new LinkedList<String>();
        int totalstock = 1;
        for(int i=0; i < storeinviList.length; i++ ){
            stockList.add(storeinviList[i][0] + ","+storeinviList[i][1]+storeinviList[i][2]);
            //System.out.println(StoresinviList[i][0] + " STORE AT 0");
         }
        for(int i=0; i < stockList.size(); i++ ){ 
            for(int j=0; j < stockList.size(); j++ ){
                if(stockList.get(i).equals(stockList.get(j)) && j > i ){
                    totalstock++;
                    stockList.remove(j);
                }
           }
            stockList.set(i, totalstock + ","+ stockList.get(i));
            totalstock = 1;
        }
        for(int i=0; i < stockList.size(); i++ ){
           storeinviList[i] = stockList.get(i).split(",");
           totalstock = Integer.parseInt(storeinviList[i][0]);
           String itemnameAdded = storeinviList[i][1];
           String priceAdded = storeinviList[i][2];
           ShopKeep keeper;
           keeper = new ShopKeep(totalstock, itemnameAdded, priceAdded);
           Itemobesrv.add(keeper); 
            //System.out.println(Itemobesrv);
        } 
         
        stockTCM.setCellValueFactory(features -> features.getValue().stockProperty());
        itemnameTCM.setCellValueFactory(features -> features.getValue().itemnameProperty());
        priceTCM.setCellValueFactory(features -> features.getValue().priceProperty());
        
        
        Ininventory.setItems(Itemobesrv);
        //Ininventory.getColumns().addAll(stockTCM,itemnameTCM,priceTCM);
    }
    
    private void Aboutme(Event event){
      System.out.print("WORDS");
  }
    
    @FXML
    public void PleaseSave(){
        filewrite.makeFile(shoptxt.getText());//Set Tilte to CSV
        fillAmount = shoping.getFillamount();
        String pricing;
        for(int i=0; i < stockList.size()-fillAmount; i++ ){
            storeinviList[i] = stockList.get(i).split(",");
            for(int k=0; k < Integer.parseInt(storeinviList[i][0]);k++){
                pricing = storeinviList[i][2];
                String value = String.valueOf(pricing.charAt(pricing.length()-2)) + String.valueOf(pricing.charAt(pricing.length()-1));
                String valueamount = "";
                for(int j=0; j < pricing.length()-2;j++){
                    valueamount += pricing.charAt(j);
                }
                filewrite.Filewrite(storeinviList[i][1]+","+valueamount+","+value);//Makes Saves store to file File
            }
        }
        if(fillAmount != 0){
                for(int i=storeinviList.length-1; i >= storeinviList.length-fillAmount; i-- ){
                    filewrite.Filewrite(storeinviList[i][1]+","+" "+","+" ");
                }
        }
        if(selected.equals("New")){
            filewrite.SaveStore(shoptxt.getText() + "," + citySize + "," + storeType + "," + fillAmount);//Saves Store name and size to Store.csv
        }
        else if(selected.equals("Old")){
          filewrite.saveStorerevisited(storesfromfile);
        }
        storeNames.add(shoptxt.getText());
        saveMI.setDisable(true);
    }
    
    public void Delete(){
        shopchoiceVB.setVisible(false);
        makeshopVB.setVisible(false);
        revisitshopPN.setVisible(false);
        enter.setVisible(false);
        deletePN.setVisible(true);
        shoptxt.setText("File-Reset to stop");
        deletionCMB.getItems().addAll(storeNames);
        deletionCMB.setOnAction(this::Startdeletion);
    }
    
    public void Startdeletion(Event event){
        enter.setVisible(true);
        shoptxt.setText(deletionCMB.getValue());
        deleteBTN.setDisable(false);
    }
    
    int doubleCheck = 0;
    public void Deleting(){
        if(doubleCheck == 0){
            System.out.println("DELETING");
            deleteBTN.setText("Yes?");
            shoptxt.setText("Foreclose "+deletionCMB.getValue());
            doubleCheck++;
        }
        else if(doubleCheck == 1){
            String goodByestore = deletionCMB.getValue();
            for(int i=0; i < storeNames.size(); i++){
                for(int j=0; j < storeNames.size(); j++){
                    if(storeNames.get(i).equals(storeNames.get(j))){
                        storeNames.remove(j);
                    }
                }
                 filewrite.DeleteStore(goodByestore, storesfromfile);
            }
           Cum(); 
        }
    }
    @FXML
    public void switchToHelp() throws IOException{
        App.setRoot("helpscreen");
    }
    
    @FXML
    public void Cum (){ ///Resetting the menu
        revisitshopPN.setVisible(false); //Pane Textfield comboboxs
        makeshopVB.setVisible(false); //Vbox Radio Buttons
        shopchoiceVB.setVisible(true); //Vbox New or Old Shop button
        shoptxt.setDisable(true);//Starting Textfield
        enter.setDisable(true);//Starting Enter Button
        enter.setVisible(true);
        shoptxt.setText("RNG Emporium");//Resetting the Textfeild
        title.setText("RNG Emporium's Inventory List"); //Resetting Title
        textTime.clear();//Clears Textfields vaule
        textTime.setPromptText("Amount");//resets Textfields prompt text
        saveMI.setDisable(true);
        addFillerCB.setSelected(false);
        shopnameCMB.getItems().clear();
        buttonListPN.setVisible(true);
        Ininventory.setVisible(false);
        inShopPN.setVisible(false);
        shopnameCMB.getItems().addAll(storeNames);
        deletePN.setVisible(false);
        for ( int i = 0; i<Ininventory.getItems().size(); i++) {
            Ininventory.getItems().clear();
         }
    }
}
