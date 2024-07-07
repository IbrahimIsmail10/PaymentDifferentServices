package Providers;

public class Quarter implements IReceipt {
    int numofmonthes;
    public Quarter(){
        this.numofmonthes=3;
    }
     //handle users payment Servecies for each transction user do

    public int handler(){
        return this.numofmonthes;
    }

}
