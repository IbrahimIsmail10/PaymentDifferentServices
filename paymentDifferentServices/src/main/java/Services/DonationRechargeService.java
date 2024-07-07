package Services;
import java.util.ArrayList;
import java.util.List;

import Providers.CancerHospital;
import Providers.IServiceProvider;
import Providers.NGOs;
import Providers.Schools;
import ServiceProviderFactorys.DonationProviderFactory;
public class DonationRechargeService extends Service{
   
    public DonationRechargeService(){
		this.name="DonationRecharge";          
    }
	public void creatProvider(String name) {
		this.ID++;
		this.servicefactory=new DonationProviderFactory();
        this.provider=servicefactory.createServiceProvider(name);
	}

	public List services(){
        List <IServiceProvider> providers = new ArrayList<IServiceProvider>(); 

		providers.add(new NGOs());
		providers.add(new Schools());
		providers.add(new CancerHospital());
		
        return providers;
    }
   

}
