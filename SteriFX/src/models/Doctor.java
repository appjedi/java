<<<<<<< HEAD
<<<<<<< HEAD
package models;

public class Doctor {
	int id;  // Patient ID
	int patientId;
	String email;
	String password;
	String lastName;
	String firstName;
	String workAddress;
	String certification;
	String phone;
	
	public Doctor() {}
	
	public Doctor (int id, String email, String password, String lastName, String firstName,
				String workAddress, String certification) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.workAddress = workAddress;
		this.certification = certification;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getWorkAddress() {
		return this.workAddress;
	}
	
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
	
	public String getCertification() {
		return this.certification;
	}
	
	public void setCertification(String certification) {
		this.certification = certification;
	}
	
	@Override
	public String toString() {
		return "Doctor [id="+id+", email=" + email + ", password=" + password + ", lastName=" + lastName + ", firstName="
				+ firstName + ", workAddress=" + workAddress + ", certification=" + certification + "]";
	}
	
}
=======
=======
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
package models;

public class Doctor {
	int id;  // Patient ID
	int patientId;
	String email;
	String password;
	String lastName;
	String firstName;
	String workAddress;
	String certification;
	String phone;
	
	public Doctor() {}
	
	public Doctor (int id, String email, String password, String lastName, String firstName,
				String workAddress, String certification) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.workAddress = workAddress;
		this.certification = certification;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getWorkAddress() {
		return this.workAddress;
	}
	
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
	
	public String getCertification() {
		return this.certification;
	}
	
	public void setCertification(String certification) {
		this.certification = certification;
	}
	
	@Override
	public String toString() {
		return "Doctor [id="+id+", email=" + email + ", password=" + password + ", lastName=" + lastName + ", firstName="
				+ firstName + ", workAddress=" + workAddress + ", certification=" + certification + "]";
	}
	
}
<<<<<<< HEAD
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
=======
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
