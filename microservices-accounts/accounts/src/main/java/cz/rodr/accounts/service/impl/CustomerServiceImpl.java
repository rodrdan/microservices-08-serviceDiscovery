package cz.rodr.accounts.service.impl;

import cz.rodr.accounts.dto.*;
import cz.rodr.accounts.entity.Accounts;
import cz.rodr.accounts.entity.Customer;
import cz.rodr.accounts.exception.ResourceNotFoundException;
import cz.rodr.accounts.mapper.AccountsMapper;
import cz.rodr.accounts.mapper.CustomerMapper;
import cz.rodr.accounts.repository.AccountsRepository;
import cz.rodr.accounts.repository.CustomerRepository;
import cz.rodr.accounts.service.ICustomerService;
import cz.rodr.accounts.service.client.CardsFeignClient;
import cz.rodr.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;

    private CustomerRepository customerRepository;

    private CardsFeignClient cardsFeignClient;

    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        // this will use the Cards feign client available through Eureka server
        ResponseEntity<CardDto> cardDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);

        // this will use the Loans feign client available through Eureka server
        ResponseEntity<LoanDto> loanDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);

        customerDetailsDto.setCardDto(cardDtoResponseEntity.getBody());
        customerDetailsDto.setLoanDto(loanDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
