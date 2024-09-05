public class ModuleDescriptor {
	
	private String code;
	
	private String name;
	
	private double[] continuousAssignmentWeights;

	public ModuleDescriptor(String coCode,String coName, double[] coWeights) //Construct a ModuleDescriptor using a given code, name, and array of weights.
	{
		this.code = coCode;
		this.name = coName;
		this.continuousAssignmentWeights = coWeights;
	}
	
	public ModuleDescriptor(String coCode) {this.code = coCode;}//Overload Construction using only a code (as the code cannot be null).
	
	public String getCode(){return this.code;} //Return the code of the current ModuleDescriptor
	public String getName(){return this.name;} //Return the name of the current ModuleDescriptor
	public double[] getWeights(){return this.continuousAssignmentWeights;} //Return the weights of the current ModuleDescriptor
}
