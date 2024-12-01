package models;

public class Triage {
	/*
	 * 
	 * tid int not null auto_increment primary key, title varchar(55), date
	 * char(10), time char(10), location varchar(50), physician varchar(30),
	 * firstname varchar(20), middlename varchar(20), lastname varchar(20), id
	 * varchar(20), phone varchar(15), email varchar(15), addresss varchar(200),
	 * insurance_company varchar(30), insured_name varchar(30), memberid
	 * varchar(15), groupid varchar(15), bp varchar(10), hr int, o2 float, temp
	 * float, othervitals varchar(70), patienthistory varchar(300), medications
	 * varchar(300), allergies varchar(150), complaints varchar(300), pain int,
	 * refill varchar(300), diagnosis varchar(300), referal boolean
	 */
	int tid;
	String title;
	String triageDate;
	String triageTime;
	String location;
	String physician;
	String firstname;
	String middlename;
	String lastname;
	int id;
	String phone;
	String email;
	String addresss;
	String insuranceCompany;
	String insuredName;
	String memberid;
	String groupid;
	String bp;
	int hr;
	float o2;
	float temp;
	String othervitals;
	String patienthistory;
	String medications;
	String allergies;
	String complaints;
	int pain;
	String refill;
	String diagnosis;
	Boolean referral;
	int status;

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTriageDate() {
		return triageDate;
	}

	public void setTriageDate(String triageDate) {
		this.triageDate = triageDate;
	}

	public String getTriageTime() {
		return triageTime;
	}

	public void setTriageTime(String triageTime) {
		this.triageTime = triageTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPhysician() {
		return physician;
	}

	public void setPhysician(String physician) {
		this.physician = physician;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getAddresss() {
		return addresss;
	}

	public void setAddresss(String addresss) {
		this.addresss = addresss;
	}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getBp() {
		return bp;
	}

	public void setBp(String bp) {
		this.bp = bp;
	}

	public int getHr() {
		return hr;
	}

	public void setHr(int hr) {
		this.hr = hr;
	}

	public float getO2() {
		return o2;
	}

	public void setO2(float o2) {
		this.o2 = o2;
	}

	public float getTemp() {
		return temp;
	}

	public void setTemp(float temp) {
		this.temp = temp;
	}

	public String getOthervitals() {
		return othervitals;
	}

	public void setOthervitals(String othervitals) {
		this.othervitals = othervitals;
	}

	public String getPatienthistory() {
		return patienthistory;
	}

	public void setPatienthistory(String patienthistory) {
		this.patienthistory = patienthistory;
	}

	public String getMedications() {
		return medications;
	}

	public void setMedications(String medications) {
		this.medications = medications;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getComplaints() {
		return complaints;
	}

	public void setComplaints(String complaints) {
		this.complaints = complaints;
	}

	public int getPain() {
		return pain;
	}

	public void setPain(int pain) {
		this.pain = pain;
	}

	public String getRefill() {
		return refill;
	}

	public void setRefill(String refill) {
		this.refill = refill;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Boolean getReferral() {
		return referral;
	}

	public void setReferral(Boolean referral) {
		this.referral = referral;
	}

	public int getStatus() {
		return this.status;
	}

	public static final String[] columns = { "tid", "title", "date", "time", "location", "physician", "firstname",
			"middlename", "lastname", "id", "phone", "email", "addresss", "insurance_company", "insured_name",
			"memberid", "groupid", "bp", "hr", "o2", "temp", "othervitals", "patienthistory", "medications",
			"allergies", "complaints", "pain", "refill", "diagnosis", "referal", "status" };

	@Override
	public String toString() {
		return "Triage [tid=" + tid + ", title=" + title + ", triageDate=" + triageDate + ", triageTime=" + triageTime
				+ ", location=" + location + ", firstname=" + firstname + ", middlename=" + middlename + ", lastname="
				+ lastname + ", physician=" + physician + ", id=" + id + ", phone=" + phone + ", email=" + email
				+ ", addresss=" + addresss + ", insuranceCompany=" + insuranceCompany + ", insuredName=" + insuredName
				+ ", memberid=" + memberid + ", groupid=" + groupid + ", bp=" + bp + ", hr=" + hr + ", o2=" + o2
				+ ", temp=" + temp + ", othervitals=" + othervitals + ", patienthistory=" + patienthistory
				+ ", medications=" + medications + ", allergies=" + allergies + ", complaints=" + complaints + ", pain="
				+ pain + ", refill=" + refill + ", diagnosis=" + diagnosis + ", referral=" + referral + ", status="
				+ status + "]";
	}

	/*
	 * 
	 * INSERT INTO "steri"."triage_form" ("tid", "title", "date", "time",
	 * "location", "physician", "firstname", "middlename", "lastname", "id",
	 * "phone", "email", "addresss", "insurance_company", "insured_name",
	 * "memberid", "groupid", "bp", "hr", "o2", "temp", "othervitals",
	 * "patienthistory", "medications", "allergies", "complaints", "pain", "refill",
	 * "diagnosis", "referal") VALUES (<{tid: }>, <{title: }>, <{date: }>, <{time:
	 * }>, <{location: }>, <{physician: }>, <{firstname: }>, <{middlename: }>,
	 * <{lastname: }>, <{id: }>, <{phone: }>, <{email: }>, <{addresss: }>,
	 * <{insurance_company: }>, <{insured_name: }>, <{memberid: }>, <{groupid: }>,
	 * <{bp: }>, <{hr: }>, <{o2: }>, <{temp: }>, <{othervitals: }>,
	 * <{patienthistory: }>, <{medications: }>, <{allergies: }>, <{complaints: }>,
	 * <{pain: }>, <{refill: }>, <{diagnosis: }>, <{referal: }>);
	 * 
	 */

}
