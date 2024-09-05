public class Student {
	
	private int id;
	
	private String name;
	
	private char gender;
	
	private double gpa;
	
	private StudentRecord[] records;
	
	public Student(int studentId, String studentName, char studentGender){ //Constructor using gender
		id = studentId;
		name = studentName;
		gender = studentGender;
	}
	
	public Student(int studentId, String studentName){ //Constructor without using gender so it may be left null
		id = studentId;
		name = studentName;
	}
	
	public void setStudentRecords(StudentRecord[] newRecords)
	{
		this.records = newRecords;
	}
	
	public void calculateGPA()
	{
		double count = 0;
		double finalTotal = 0;
		for (StudentRecord currentRecord : records)
		{
			if (currentRecord != null)
			{
				finalTotal += currentRecord.getFinalScore();
				count++;
			}
		}
		gpa = (finalTotal/count);
	}
	
	public int getID(){return this.id;}	
	public double getGPA(){return this.gpa;}
	
	public void setName(String newName){this.name = newName;}
	public void setID(int newID){this.id = newID;}
	public void setGender(char newGender){this.gender = newGender;}

	public String printTranscript() {
		System.out.println("");
		System.out.println("");
		System.out.println("ID: " + id);
		System.out.println("Name: " + name);
		System.out.println("GPA: " + gpa);
		int recordCount = 0;
		byte currentTerm = 0;
		boolean newTerm = true;
		while (recordCount < records.length)
		{
			if(newTerm){
				newTerm = false;
				System.out.println("");
			}
			for (int index = 0; index < records.length; index++)
			{
				if (records[index] != null)
				{
					Module modToCompare = records[index].getModule();
					if (modToCompare.getTerm() == currentTerm){
						newTerm = true;
						System.out.println(createReport(records[index], modToCompare));
						recordCount += 1;
					}
				}
			}
			currentTerm++;
		}
		return "";
	}
	
	private String createReport(StudentRecord record, Module module) {
		String reportString = "| ";
		ModuleDescriptor modDesc = module.getDescriptor();
		reportString += (module.getYear() + " | " + module.getTerm() + " | " + modDesc.getCode() + " | " + record.getFinalScore() + " |");
		return reportString;
	}
}
