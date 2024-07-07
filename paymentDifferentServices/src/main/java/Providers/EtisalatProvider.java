package Providers;

import java.util.Scanner;

import FawarySystem.*;
import Form.Form;
import Payment.CreditCardPayment;
import Payment.OnDeleviryPayment;
import Payment.WalletPayment;
import Person.Users;

public class EtisalatProvider extends IServiceProvider {
	public EtisalatProvider() {
        this.name = "Etisalat";
    }
    public boolean creatForm(Users user,Form form) {
    	this.form=form;
        return handler(user);
        
        
    }
     //handle users payment Servecies for each transction user do

    public boolean handler(Users user) {
    	boolean x = false;
        if(this.form.Way.contains("wallet")){
         payment = new WalletPayment();
         System.out.print("done");
         x = payment.pay(user,this.form.amount);
        }
        else if(this.form.Way.contains("OnDeleviry") ){
            payment = new OnDeleviryPayment();
            x = payment.pay(user,this.form.amount);      
        }
        else{
            payment = new CreditCardPayment();
            x = payment.pay(user,this.form.amount);        	
        }
        return x;
     
    }
}
