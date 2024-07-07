package Providers;

public class Monthly implements IReceipt{
    int numofmonthes;
    public Monthly(){
        this.numofmonthes=1;
    }
     //handle users payment Servecies for each transction user do

    public int handler(){
      return numofmonthes;
    }

}
