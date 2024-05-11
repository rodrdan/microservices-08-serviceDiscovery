package cz.rodr.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "CustomerDetails",
        description = "Schema to hold Customer, Account, Cards and Loans information"
)
public class CustomerDetailsDto {

    @Schema(
            description = "Name of the customer", example = "John Doe"
    )
    @NotEmpty(message = "Name can not be empty.")
    @Size(min = 5, max = 30, message = "The lenght of the customer name should be between 5 and 30 characters.")
    private String name;

    @Schema(
            description = "Email address of the customer", example = "johndoe@writemee.com"
    )
    @NotEmpty(message = "Email can not be empty.")
    @Email(message = "Email address should be a valid value.")
    private String email;

    @Schema(
            description = "Mobile number of the customer", example = "1234567890"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
    private String mobileNumber;

    @Schema(
            description = "Account details of the customer"
    )
    private AccountsDto accountsDto;

    @Schema(
            description = "Cards details of the customer"
    )
    private CardDto cardDto;

    @Schema(
            description = "Loans details of the customer"
    )
    private LoanDto loanDto;

}
