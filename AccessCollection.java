class AccessCollection{
	private static AccessCollection accessCollection;
	private static StudentManagementSystemOOP studentManagement;
	
	private  AccessCollection(){
		studentManagement=new StudentManagementSystemOOP();
	}
	
	public static AccessCollection getInstatnce(){
		if(accessCollection==null){
			accessCollection=new AccessCollection();
		}
		return accessCollection;
	}
	
	public StudentManagementSystemOOP getStudentManagement(){
		return studentManagement;
	}
	
}
