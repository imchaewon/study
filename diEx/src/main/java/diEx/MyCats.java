package diEx;

public class MyCats {

	Cats cats; 
	private String firstCatName;
	private int firstCatAge;
	private String secondCatName;
	private int secondCatAge;

	public void catsNameInfo() {
		cats.catsName(firstCatName, secondCatName);
	}

	public void catsAgeInfo() {
		cats.catsAge(firstCatAge, secondCatAge);
	}

	public void setCats(Cats cats) {
		this.cats = cats;
	}

	public void setFirstCatName(String firstCatName) {
		this.firstCatName = firstCatName;
	}

	public void setFirstCatAge(int firstCatAge) {
		this.firstCatAge = firstCatAge;
	}

	public void setSecondCatName(String secondCatName) {
		this.secondCatName = secondCatName;
	}

	public void setSecondCatAge(int secondCatAge) {
		this.secondCatAge = secondCatAge;
	}

}
