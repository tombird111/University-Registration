public class Module {
	
	private int year;
	
	private byte term;
	
	private ModuleDescriptor module;
	
	private StudentRecord[] records;
	
	private double finalAverageGrade;
	
	public Module (int coYear, byte coTerm, ModuleDescriptor coModule)
	{
		year = coYear;
		term = coTerm;
		module = coModule;
	}
	
	public double[] getDescWeights(){return this.module.getWeights();}
	public double getFinalAvgGrade(){return this.finalAverageGrade;}
	public int getYear(){return this.year;}
	public byte getTerm(){return this.term;}
	public ModuleDescriptor getDescriptor(){return this.module;}
	
	public void setStudentRecords(StudentRecord[] newRecords)
	{
		this.records = newRecords;
	}
	
	public void calculateFinalAvgGrade()
	{
		double recordCount = 0; //Count the number of records
		double recordSum = 0;
		for (StudentRecord currentRecord : records)
		{
			if (currentRecord != null)
			{
				currentRecord.calculateFinalScore();
				recordSum += currentRecord.getFinalScore();
				recordCount += 1;
			}
		}
		finalAverageGrade = (recordSum/recordCount);
	}
}
