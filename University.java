public class University {

	private ModuleDescriptor[] moduleDescriptors;
	
	private Student[] students;
	
	private Module[] modules;
	
	public University (ModuleDescriptor[] coModDescs, Student[] coStudents, Module[] coModules){
		this.moduleDescriptors = coModDescs;
		this.students = coStudents;
		this.modules = coModules;
	}

	/**
	 * @return The number of students registered in the system.
	 */
	public int getTotalNumberStudents() {
		return this.students.length;
	}

	/**
	 * @return The student with the highest GPA.
	 */
	public Student getBestStudent() {
		double bestGPA = 0;
		Student bestStudent = this.students[0];
		for (Student student : this.students)
		{
			double currentGPA = student.getGPA();
			if(currentGPA > bestGPA)
			{
				bestStudent = student;
			}
		}
		return bestStudent;
	}

	/**
	 * @return The module with the highest average score.
	 */
	public Module getBestModule() {
		double bestFinalAvgGrade = 0;
		Module bestModule = this.modules[0];
		for (Module module : this.modules)
		{
			if(module != null)
			{
				double currentFinalAvg = module.getFinalAvgGrade();
				if(currentFinalAvg > bestFinalAvgGrade)
				{
					bestModule = module;
				}
			}
		}
		return bestModule;
	}
	
	public static void main(String[] args) {
		ModuleDescriptor[] newModuleDescriptors = assignModuleDescriptors();
		Student[] newStudents = assignStudents();
		Module[] newModules = assignModules(newModuleDescriptors);
		StudentRecord[] newRecords = assignRecords(newModules, newStudents);
		assignStudentRecords(newRecords, newStudents);
		assignModuleRecords(newRecords, newModules);
		for (Module module : newModules)
		{
			module.calculateFinalAvgGrade();
		}
		for (StudentRecord record : newRecords)
		{
			record.aboveAvgCheck();
		}
		for (Student student : newStudents)
		{
			student.calculateGPA();
		}
		University uni = new University(newModuleDescriptors, newStudents, newModules);
		System.out.println("Number of students: " + uni.getTotalNumberStudents());
		Module bestModule = uni.getBestModule();
		System.out.println("The best module: " + bestModule.getYear() + " | " + bestModule.getTerm() + " | " + bestModule.getDescriptor().getCode() + " | " + bestModule.getFinalAvgGrade());
		uni.getBestStudent().printTranscript();
	}
	
	
	public static boolean checkWeightSum(double[] weights) {
		double weightSum = 0;
		for (double weight : weights)
		{
			weightSum += weight;
		}
		if (weightSum == 1)
		{
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isCodePresent(String newCode, ModuleDescriptor[] modArray) {
		for (ModuleDescriptor module : modArray)
		{
			if (module != null)
			{
				if(module.getCode() == newCode)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static ModuleDescriptor[] assignModuleDescriptors() {
		String[] names = {"Real World Mathematics", "Programming", "Data Structures", "Object-Oriented Programming", "Information Systems", "Thermal Physics"};
		String[] codes = {"ECM0002", "ECM1400", "ECM1406", "ECM1410", "BEM2027", "PHY2023"};
		double[][] weights = {{0.1, 0.3, 0.6}, {0.25, 0.25, 0.25, 0.25}, {0.25, 0.25, 0.5}, {0.25, 0.25, 0.5}, {0.1, 0.3, 0.3, 0.3}, {0.4, 0.6}};
		ModuleDescriptor[] modDescs = new ModuleDescriptor[names.length]; //Make a new array of ModuleDescriptors
		if (names.length == codes.length && codes.length == weights.length) //Check that the lengths of each array are correct
		{
			for (int index = 0; index < names.length; index++) //For every possible modules
			{
				if (isCodePresent(codes[index],modDescs) == false)
				{
					ModuleDescriptor modDescToAdd = new ModuleDescriptor(codes[index], names[index], weights[index]);
					modDescs[index] = modDescToAdd;
				}
			}
		}
		return modDescs;
	}
	
	public static boolean isIDPresent(int newID, Student[] studentArray) {
		for (Student student : studentArray)
		{
			if (student != null)
			{
				if (student.getID() == newID)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static char[] genderSort(char[] genderArray) {
		for (char currentGender : genderArray)
		{
			if (currentGender != 'M' || currentGender != 'F' || currentGender != 'X')
			{
				currentGender = 'Z';
			}
		}
		return genderArray;
	}
	
	public static Student[] assignStudents(){
		String[] names = {"Anna", "Oliver", "Mary", "John", "Noah", "Chico", "Maria", "Mark", "Lia", "Rachel"};
		int[] ids = {1000,1001,1002,1003,1004,1005,1006,1007,1008,1009};
		char[] genders = {'F','M','F','M','M','M','F','X','F','F'};
		genderSort(genders);
		Student[] studentsArray = new Student[names.length];
		if (names.length == ids.length && ids.length == genders.length)
		{
			for (int index = 0; index < names.length; index++)
			{
				if (isIDPresent(ids[index], studentsArray) == false)
				{
					if (genders[index] == 'Z') {
						Student studentToAdd = new Student(ids[index], names[index]);
						studentsArray[index] = studentToAdd;
					} else {
						Student studentToAdd = new Student(ids[index], names[index], genders[index]);
						studentsArray[index] = studentToAdd;
					}
				}
			}
		}
		return studentsArray;
	}
	
	public static Module[] assignModules(ModuleDescriptor[] modDescs){
		int[] years = {2019,2019,2019,2019,2019,
		2019,2019,2019,2019,2019,
		2019,2019,2019,2019,2019,
		2020,2020,2020,2020,2020,
		2020,2020,2020,2020,2020,
		2020,2020,2020,2020,2020,
		2020,2020,2020,2020,2020
		};
		byte[] terms = {1,1,1,1,1,
		1,1,1,1,1,
		2,2,2,2,2,
		2,2,2,2,2,
		1,1,1,1,1,
		1,1,1,1,1,
		2,2,2,2,2
		};
		String[] modDescCodes = {"ECM1400","ECM1400","ECM1400","ECM1400","ECM1400",
		"PHY2023","PHY2023","PHY2023","PHY2023","PHY2023", 
		"BEM2027","BEM2027","BEM2027","BEM2027","BEM2027",
		"ECM1406","ECM1406","ECM1406","ECM1406","ECM1406", "ECM1406","ECM1406","ECM1406","ECM1406","ECM1406",
		"ECM1410","ECM1410","ECM1410","ECM1410","ECM1410",
		"ECM1402","ECM1402","ECM1402","ECM1402","ECM1402"
		};
		//First, we create an array of all possible modules
		Module[] nonUniqueModArray = createNonUniqueModules(years, terms, modDescCodes, modDescs);		
		//Then, we compare each module, getting the number of unique modules
		int uniqueCount = 0;
		Module[] uniqueModArray = new Module[nonUniqueModArray.length]; //Create an array to store unique modules
		for (int index1 = 0; index1 < nonUniqueModArray.length; index1++) //For each module
		{ 
			boolean unique = true;
			for(int index2 = 0; index2 < uniqueModArray.length; index2++) //Check for each unique module
			{
				if(nonUniqueModArray[index1] == uniqueModArray[index2]) //If a similar module can be found
				{
					unique = false;
				}
			}
			if (unique == true)
			{					
				uniqueModArray[uniqueCount] = nonUniqueModArray[uniqueCount]; //If no similar module can be found, add it to the unique array
				uniqueCount = uniqueCount + 1; //Note that there is an increase in the number of unique modules
			}
		}
		Module[] modArray = new Module[uniqueCount]; //Create an array with length equal to the number of unique modules
		for (int modIndex = 0; modIndex < uniqueCount; modIndex++) //For every unique value
		{
			modArray[modIndex] = uniqueModArray[modIndex]; //Add it to the array
		}
		return modArray;
	}
	
	public static Module[] createNonUniqueModules(int[] years, byte[] terms, String[] modDescCodes, ModuleDescriptor[] modDescs){
		Module[] modArray = new Module[years.length];
		for (int index = 0; index < years.length; index++)
		{
			Module newModule = new Module(years[index], terms[index], getModDesc(modDescCodes[index], modDescs));
			modArray[index] = newModule;
		}
		return modArray;
	}
	
	public static ModuleDescriptor getModDesc(String descCode, ModuleDescriptor[] modDescs){
		String codeToFind = descCode;
		ModuleDescriptor[] descArray = modDescs;
		for (ModuleDescriptor modDesc : descArray) //For every module descriptor
		{
			if(modDesc.getCode() == descCode)
			{
				return modDesc;
			}
		}
		double[] invalidWeights = {1};
		ModuleDescriptor invalid = new ModuleDescriptor("N/A", "N/A", invalidWeights);
		return invalid;
	}
	
	public static StudentRecord[] assignRecords(Module[] modules, Student[] students){
		double[][] marks = {{9,10,10,10},{8,8,8,9},{5,5,6,5},{6,4,7,9},{10,9,10,9},
		{9,9},{6,9},{5,6},{9,7},{8,5},
		{10,10,9.5,10}, {7, 8.5, 8.2, 8}, {6.5, 7.0, 5.5, 8.5}, {5.5, 5, 6.5, 7}, {7, 5, 8, 6},
		{9, 10, 10, 10},{8, 8, 8, 9},{5, 5, 6, 5},{6, 4, 7, 9},{10, 9, 8, 9},
		{10, 10, 10},{8, 7.5, 7.5},{9, 7, 7},{9, 8, 7},{2, 7, 7},{10, 10, 10},{8, 7.5, 7.5},{10, 10, 10},{9, 8, 7},{8, 9, 10},
		{10, 9, 10},{8.5, 9, 7.5},{10, 10, 5.5},{7, 7, 7},{5, 6, 10},
		{8, 9, 8},{6.5, 9, 9.5},{8.5, 10, 8.5},{7.5, 8, 10},{10, 6, 10}
		};
		int[] ids = {1000,1001,1002,1003,1004,
		1005,1006,1007,1008,1009,
		1000,1001,1002,1003,1004,
		1005,1006,1007,1008,1009,
		1000,1001,1002,1003,1004,1005,1006,1007,1008,1009,
		1000,1001,1002,1003,1004,
		1005,1006,1007,1008,1009
		};
		String[] codes = {"ECM1400","ECM1400","ECM1400","ECM1400","ECM1400",
		"PHY2023","PHY2023","PHY2023","PHY2023","PHY2023", 
		"BEM2027","BEM2027","BEM2027","BEM2027","BEM2027",
		"ECM1400","ECM1400","ECM1400","ECM1400","ECM1400",
		"ECM1406","ECM1406","ECM1406","ECM1406","ECM1406", "ECM1406","ECM1406","ECM1406","ECM1406","ECM1406",
		"ECM1410","ECM1410","ECM1410","ECM1410","ECM1410",
		"ECM1402","ECM1402","ECM1402","ECM1402","ECM1402"
		};
		StudentRecord[] records = new StudentRecord[marks.length];
		for (int index = 0; index < marks.length; index++) //For every set of marks
		{
			Module currentMod = getModFromCode(codes[index], modules);
			Student currentStudent = getStudentFromID(ids[index], students);
			records[index] = new StudentRecord(currentStudent, currentMod, marks[index]);
		}
		return records;
	}
	
	public static Module getModFromCode(String code, Module[] modules){
		for (Module module : modules) //For every module
		{
			ModuleDescriptor modDesc = module.getDescriptor(); //Check that the code matches the current module
			if (modDesc.getCode() == code)
			{
				return module; //Return the module if there is a match
			}
		}
		return null;
	}
	
	public static Student getStudentFromID(int id, Student[] students){
		for (Student student : students) //For every student
		{
			if (student.getID() == id) //Check that the ID matches the current student
			{
				return student; //Return the student if there is a match
			}
		}
		return null;
	}
	
	public static void assignStudentRecords(StudentRecord[] records, Student[] students){
		for (Student student : students) //For every student
		{
			int matchCount = 0; //Count how many records belong to them
			for (StudentRecord record : records)
			{
				if(record.getStudent() == student)
				{
					matchCount += 1;
				}
			}
			StudentRecord[] recordsForStudent = new StudentRecord[matchCount]; //Create an array of the appropriate size
			for (StudentRecord record : records) //For every record
			{
				int index = 0;
				if(record.getStudent() == student) //Check it belongs to the student
				{
					recordsForStudent[index] = record; //Add it to the array if it does
					index += 1;
				}
			}
			student.setStudentRecords(recordsForStudent);
		}
	}
	
	public static void assignModuleRecords(StudentRecord[] records, Module[] modules){
		for (Module module : modules) //For every module
		{
			int matchCount = 0; //Count how many records belong to them
			for (StudentRecord record : records)
			{
				if(record.getModule() == module)
				{
					matchCount += 1;
				}
			}
			StudentRecord[] recordsForModule = new StudentRecord[matchCount]; //Create an array of the appropriate size
			for (StudentRecord record : records) //For every record
			{
				int index = 0;
				if(record.getModule() == module) //Check it belongs to the module
				{
					recordsForModule[index] = record; //Add it to the array if it does
					index += 1;
				}
			}
			module.setStudentRecords(recordsForModule);
		}
	}
}
