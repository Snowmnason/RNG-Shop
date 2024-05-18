package snowpost.rngshop;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class HelpController {
@FXML
private Pane revisitshopPN, buttonListPN, deletePN;
@FXML
private VBox shopchoiceVB, makeshopVB;
@FXML
private TextArea newold, towns, revisit,delete;
@FXML
public Text title;
@FXML 
private TableView <ShopKeep>Ininventory;
@FXML
private Button enter;

    @FXML
    private void switchToMain() throws IOException {
        App.setRoot("mainframe");
    }
    public void showScreen(){ //dunno how to run wait on start up!
        int timeToWait = 3; //second
        System.out.print("Scanning");
        try {
            for (int i=0; i<timeToWait ; i++) {
                Thread.sleep(1000);
                System.out.print(".");
            }
        } catch (InterruptedException ie)
        {
            Thread.currentThread().interrupt();
        }

        //title.setVisible(false);
        //Ininventory.setVisible(false);
        //newold.setVisible(true);
    }
    
    int timesRan = 0;
    @FXML
    private void onEnter() throws IOException{
        if(timesRan == 0 || timesRan == 1){
            Resart();
            timesRan++;
        }
        if(timesRan == 2){
            delete();
            timesRan++;
        }
        else if(timesRan == 3){
            App.setRoot("mainframe");
        }        
        
    }
    @FXML
    private void oldShop(){
        revisitshopPN.setVisible(true); //Pane Textfield comboboxs  
        shopchoiceVB.setVisible(false); //Vbox New or Old Shop button
        newold.setVisible(false);
        revisit.setVisible(true);
        enter.setDisable(false);
        
    }
    @FXML 
    private void newShop(){
        makeshopVB.setVisible(true); //Vbox Radio Buttons
        shopchoiceVB.setVisible(false); //Vbox New or Old Shop button
        newold.setVisible(false);
        towns.setVisible(true);
        enter.setDisable(false);
    }
    public void delete(){
        makeshopVB.setVisible(false); //Vbox Radio Buttons
        towns.setVisible(false);
        shopchoiceVB.setVisible(false);
        revisitshopPN.setVisible(false); //Pane Textfield comboboxs  
        deletePN.setVisible(true);
        delete.setVisible(true);
        newold.setVisible(false);
        revisit.setVisible(false);
        enter.setDisable(false);
        
        
    }
    private void Resart(){
        shopchoiceVB.setVisible(true); //Vbox New or Old Shop button
        newold.setVisible(true);
        enter.setDisable(true);
        makeshopVB.setVisible(false); //Vbox Radio Buttons
        towns.setVisible(false);
        revisitshopPN.setVisible(false); //Pane Textfield comboboxs  
        revisit.setVisible(false);
        
    }
}
