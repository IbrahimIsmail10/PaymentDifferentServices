package FawarySystem;


import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import Form.Form;
import Person.*;
import Services.*;
import jakarta.websocket.server.PathParam;

@RestController
public class Display {
	Admins admin;	Users user;
	Refund refund = Refund.Get_Instance();
	Services services = new Services();
	DataBase db =DataBase.Get_Instance();

	@RequestMapping(value = "/")
    public String greeting() {

		String html = "<html>\n" +
              "    <body>\n" +
              "        <div > "+
			  "				<a style=text-decoration:none href=/loginAdmin>	Login As Admin!	</a><br>"	+
			   "			<a style=text-decoration:none href=/loginUser >	login AS Users!	</a><br> "	+
			   "			<a style=text-decoration:none href=/signUp	   >	Sign Up new users!	</a><br>"	+
			   	"		</div>" +
              "    </body>\n" +
              "</html>\n";

        return html;
    }

	@PostMapping(value = "/loginAdmin")
    public ResponseEntity<Person> loginAdmin( @RequestBody Person person) {
		admin = db.loginAdmin(person);

		if(admin!=null){
			return new ResponseEntity<>(admin, HttpStatus.OK);
		}
		else{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
    }

	@PostMapping(value = "/loginUser")
    public ResponseEntity<Person> loginUser( @RequestBody Person person) {
		user = db.loginUser(person);
		if(user!=null){
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		else{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
    }
	
	@PostMapping(value = "/signUp")
    public ResponseEntity<Person> signUp( @RequestBody Person person) {
		user = db.signUP(person);
		if(user!=null){
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		else{
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/signout")
    public ResponseEntity<String> signout() {
		if(user!=null){
			user=null;
			return new ResponseEntity<>("loged out Successfully ", HttpStatus.OK);
		}
		else{
			admin=null;
			return new ResponseEntity<>("loged out Successfully", HttpStatus.OK);
		}
	}

	@PutMapping(value = "/overAllDiscount")
    public ResponseEntity<String> overAllDiscount() {
		if(admin!=null){
			if(services.setDiscount(1,"")){
				return new ResponseEntity<>("Add Successfully", HttpStatus.OK);
			}
			else{
				return new ResponseEntity<>("Can not Add the OverAllDiscounts", HttpStatus.BAD_REQUEST);
			}
		}
		else{
			return new ResponseEntity<>("Not found ", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/specificDiscount")
    public ResponseEntity<String> specificDiscount(@RequestParam String servicename ) {
		if(admin!=null){
			if(services.setDiscount(2,servicename)){
				return new ResponseEntity<>("Add Successfully", HttpStatus.OK);
			}
			else{
				return new ResponseEntity<>("Can not Add the OverAllDiscounts", HttpStatus.BAD_REQUEST);
			}
		}
		else{	
			return new ResponseEntity<>("Not found ", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/AddToFund")
	public ResponseEntity<Person> addtofund(@RequestBody Wallet obj) {
		if(user!=null){
			if(user.wallet.add_amount(obj.Balance_amount)){
				user.ts.Add_wallet_trans(user, obj);
				db.addWalletTrans(user, obj.Balance_amount);
				return new ResponseEntity<>(user, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

	}
	
	@GetMapping(value = "/services/{servicename}")
    public ResponseEntity<List> getServices(@PathVariable String servicename) {
		Service service=services.search(servicename);
		if(service!=null){
			return new ResponseEntity<>(service.services(), HttpStatus.OK);
		}
		return null;
		
	}

	@GetMapping(value = "/services/{servicename}/{name}")
    public ResponseEntity<Users> getInternetprovider(@PathVariable String name,@PathVariable String servicename,@RequestBody Form form) {
		if(user!=null){
			Service service=services.search(servicename);
			if(service!=null){
				
				service.creatProvider(name);
				boolean x = service.provider.creatForm(user,form);
				if(x){
				    user.ts.Add_Transaction(user,service);
					db.addTransaction(user, service);
					user.service_money.put(service.ID, (double) form.amount);
					return new ResponseEntity<>(user, HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<>( null, HttpStatus.FORBIDDEN);
		
	}
	
	@GetMapping(value = "/refunds")
    public ResponseEntity<Map> getRefunds() {
		if(admin!=null){
			return  new ResponseEntity<>( refund.getReefunds(), HttpStatus.OK);
		
		}
		return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
		
	}

	@GetMapping(value = "/refund")
    public ResponseEntity<Map>  getRefund(@PathParam("id") int id,@PathParam("stauts") String stauts) {
		if(admin!=null){
			return  new ResponseEntity<>( refund.setStatusOfSpecificRefunds(id,stauts), HttpStatus.OK);
		}
		return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
		
		
	}
	
	@GetMapping(value = "/transactions")
    public ResponseEntity<Map>  getTransactions() {
		if(user!=null){
			return  new ResponseEntity<>( user.ts.trans, HttpStatus.OK);
		}
		return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
		
	}

	@PostMapping(value = "/askRefunds")
    public ResponseEntity<Map>  AskRefunds(@PathParam("id") int id) {
		if(user!=null){
			refund.ask_refund(user, id);
			return  new ResponseEntity<>( refund.printuser(user), HttpStatus.OK);
		}
		return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
		
	}
	@GetMapping(value = "/ShowmyRefunds")
    public ResponseEntity<Map>  getMyRefund() {
		if(user!=null){
			return  new ResponseEntity<>( refund.printuser(user), HttpStatus.OK);
		}
		return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
		
	}

	@GetMapping(value = "/discounts")
    public ResponseEntity<List>  showDiscounts() {
		if(user!=null){
			return  new ResponseEntity<>( services.searchDiscount(), HttpStatus.OK);
		}
		return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value = "/ShowAllUsersTransactions")
    public ResponseEntity<Map>  getAllUsersTrans() {
		if(admin!=null){
			return  new ResponseEntity<>( db.getALLTransactions(), HttpStatus.OK);
		}
		return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
		
	}@GetMapping(value = "/ShowAllUsersRefunds")
    public ResponseEntity<Map>  getAllUsersREF() {
		if(admin!=null){
			return  new ResponseEntity<>( db.getALLRefunds(), HttpStatus.OK);
		}
		return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
		
	}

}