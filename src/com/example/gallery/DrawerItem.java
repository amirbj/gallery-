package com.example.gallery;

public class DrawerItem {
	 
    int ItemName;
    int imgResID;

    public DrawerItem(int itemName){
          super();
          ItemName = itemName;
          //this.imgResID = imgResID;
    }

    public int getItemName() {
          return ItemName;
    }
    public void setItemName(int itemName) {
          ItemName = itemName;
    }
   

}