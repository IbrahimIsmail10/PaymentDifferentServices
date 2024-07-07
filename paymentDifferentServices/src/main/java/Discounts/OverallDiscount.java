package Discounts;

import Services.Service;

public class OverallDiscount extends DiscountDecorator{

    public OverallDiscount(Service service) {
        super(service);
        this.name="OverallDiscount";

    }
    
}
