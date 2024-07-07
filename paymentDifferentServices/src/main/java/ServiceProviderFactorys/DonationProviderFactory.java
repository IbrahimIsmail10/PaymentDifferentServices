package ServiceProviderFactorys;

import java.util.*;

import Providers.CancerHospital;
import Providers.IServiceProvider;
import Providers.NGOs;
import Providers.Schools;

public class DonationProviderFactory implements IServiceProviderFactory {
    //create provider to the user for service  donation when he chooce it
    public IServiceProvider createServiceProvider(String choice ) {
        choice=choice.toLowerCase();

        if(choice.contains("schools")) {
            return new Schools();
        }
        else if(choice.contains("cancerHospital")) {
            return new CancerHospital();
        }
        else if(choice.contains("ngos")) {
            return new NGOs();
        }
        return null;
    }
    
}
