public class Enrollee {
	
	private String userId;
	private String fullName;
	private Integer version;
	private String insuranceCompany;
	
	public Enrollee(String userId, String fullName, Integer version, String insuranceCompany) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.version = version;
		this.insuranceCompany = insuranceCompany;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	@Override
	public String toString() {
		return "Enrollee [userId=" + userId + ", fullName=" + fullName + ", version=" + version + ", insuranceCompany="
				+ insuranceCompany + "]";
	}
}