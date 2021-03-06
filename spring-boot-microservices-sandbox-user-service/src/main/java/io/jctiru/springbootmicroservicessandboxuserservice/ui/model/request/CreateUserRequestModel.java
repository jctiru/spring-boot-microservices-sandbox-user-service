package io.jctiru.springbootmicroservicessandboxuserservice.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {

	@NotBlank(message = "First name must not be empty")
	private String firstName;

	@NotBlank(message = "Last name must not be empty")
	private String lastName;

	@NotBlank(message = "Password must not be empty")
	@Size(min = 6, message = "Password must be at least 6 characters")
	private String password;

	@NotBlank(message = "Email must not be empty")
	@Email(message = "Email must be a valid email address")
	private String email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
