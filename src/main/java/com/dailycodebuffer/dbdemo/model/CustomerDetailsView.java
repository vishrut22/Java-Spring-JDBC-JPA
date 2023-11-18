package com.dailycodebuffer.dbdemo.model;

import org.springframework.beans.factory.annotation.Value;

public interface CustomerDetailsView {
    // Lets map how order entity's address looks in our projection. this is called open projection.
    // We can defined whatever we need in this special expresion i.e. like we can concat two columns or
    // do some operation
    @Value("#{target.address}")
    String getAddressDetails();

    //We kept exact same field name what we have for actual entity
    String getCustomerName();
}
