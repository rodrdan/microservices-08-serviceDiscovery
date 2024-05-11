package cz.rodr.accounts.service;

import cz.rodr.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {

    /**
     *
     * @param mobileNumber - Input mobile number
     * @return Customer Details based on a given mobile number
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
