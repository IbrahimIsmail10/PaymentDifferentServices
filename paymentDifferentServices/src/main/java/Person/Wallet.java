package Person;

import java.util.Scanner;

public class Wallet {
	public double Balance_amount ;
	public Wallet(){
		Balance_amount = 0;
	}
	//add amount to user wallet
	public boolean add_amount(double x){
		Balance_amount += x;
		return true;
	}
	//delete amount from users
	public boolean delete_amount(double x){
		if(Balance_amount >= x){
		Balance_amount -= x;
	return true;
	}
	return false;

}
}

