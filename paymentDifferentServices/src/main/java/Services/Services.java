package Services;

import java.time.Instant;
import java.util.*;

import Discounts.*;

@org.springframework.stereotype.Service
public class Services {
    private List <Service>services=new ArrayList<Service>();

    public Services(){
        services.add(new MobileRechargeService());
        services.add(new InternetRechargeService());
        services.add(new LandlineRechargeService());
        services.add(new DonationRechargeService());

    }
    //return to the user providers of service that he search for it
    public Service search(String query){
        Service selections=null;
        char ch =Character.toUpperCase(query.charAt(0));
        query=ch+query.substring(1);

        for(int i=0;i<services.size();i++){
            if(services.get(i).getName().trim().contains(query)) 
                selections=services.get(i);      
        }
        if(selections!=null){
            return selections;
            
        }
        else {
            return null;
        }
    }

    public Boolean setDiscount(int choice,String specificService){
        int n=services.size();
        if(choice==1){
            // OverAll Discount
            for(int i=0;i<n;i++){
                services.add(new OverallDiscount(services.get(i)));
            }
            return true;
        }
        else{
            // Specific Discount
            char ch =Character.toUpperCase(specificService.charAt(0));
            specificService=ch+specificService.substring(1);
            for(int i=0;i<n;i++){
                if(services.get(i).getName().trim().contains(specificService)){
                    services.add(new SpecificDiscount(services.get(i)));
                    break;
                }
            }
            // Specific Discount
            return true;
        }

    }
    
    public List searchDiscount(){
        List <Service>servicesDiscounts=new ArrayList<Service>();
        for(int i=0;i<services.size();i++){
            if(services.get(i).getName().contains("Discount")){
                servicesDiscounts.add(services.get(i));
            }
        }
        return servicesDiscounts;
    }
}
