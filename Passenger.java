
public class Passenger {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getBerthPreference() {
		return berthPreference;
	}
	public Passenger() {

		this.pid = ++count;
	}
	public void setBerthPreference(Integer berthPreference) {
		this.berthPreference = berthPreference;
	}
	public Integer getPid() {
		return pid;
	}
	Integer pid;
	static int count=0;
	String name;
	Integer age;
	String gender;
	public Integer getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(Integer seatNo) {
		this.seatNo = seatNo;
	}
	Integer berthPreference;
	Integer seatNo;
	
}
