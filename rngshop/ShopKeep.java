package snowpost.rngshop;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ShopKeep {
    private SimpleIntegerProperty  stock = new SimpleIntegerProperty(this, "stock");
     public void setStock(int stock) {
        this.stock.set(stock);
    }   
    public int getStock() {
        return stock.get();
    }
    public IntegerProperty stockProperty(){
        return stock;
    }

    private SimpleStringProperty  itemname = new SimpleStringProperty(this, "itemname");    
    public void setItemname(String itemname) {
        this.itemname.set(itemname);;
    }
    public String getItemname() {
        return itemname.get();
    }
    public StringProperty itemnameProperty(){
        return itemname;
    }
    private SimpleStringProperty price = new SimpleStringProperty(this, "price");
    public void setPrice(String price) {
        this.price.set(price);
    }
    public String getPrice() {
        return price.get();
    }
   public StringProperty priceProperty(){
        return price;
    }

    
    ShopKeep(int aStock, String aitem, String aPrice) {
        setStock(aStock);
        setItemname(aitem);
        setPrice(aPrice);
    }

}
