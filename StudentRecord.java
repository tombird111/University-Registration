public class StudentRecord {

	private Student student;
	
	private Module module;
	
	private double[] marks;
	
	private double finalScore;
	
	private Boolean isAboveAverage;
	
	public StudentRecord(Student coStudent, Module coModule, double[] coMarks)
	{
		student = coStudent;
		module = coModule;
		marks = coMarks;
	}
	
	public void calculateFinalScore ()
	{
		finalScore = 0;
		double weights[] = module.getDescWeights();
		if (marks.length == weights.length)
		{
			for(int index = 0; index < marks.length; index++)
			{
				this.finalScore += (marks[index]*weights[index]);
			}
		}
	}
	
	public void aboveAvgCheck()
	{
		if (this.module != null)
		{
			if (this.finalScore >= this.module.getFinalAvgGrade()) {
				isAboveAverage = true;
			} else {
				isAboveAverage = false;
			}
		}
	}
	
	public Student getStudent() {return student;}
	public double getFinalScore() {return finalScore;}
	public Boolean getAboveAvg() {return isAboveAverage;}
	public Module getModule() {return module;}
}
