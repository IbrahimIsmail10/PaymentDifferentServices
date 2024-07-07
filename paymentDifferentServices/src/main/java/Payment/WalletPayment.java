package Payment;

import FawarySystem.*;
import Person.*;

public class WalletPayment implements IPayment {
    public DataBase db =DataBase.Get_Instance();
//function to pay with wallet
	public boolean pay(Users user, int amount) {
		return user.wallet.delete_amount(amount);
	}

}
