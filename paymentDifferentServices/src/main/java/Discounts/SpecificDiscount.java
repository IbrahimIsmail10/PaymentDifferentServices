package Discounts;

import Services.Service;

public class SpecificDiscount extends DiscountDecorator{

    public SpecificDiscount(Service service) {
        super(service);
        this.name="SpecificDiscount";
    }
    
}
