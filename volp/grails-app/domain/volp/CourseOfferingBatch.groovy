package volp

class CourseOfferingBatch {

	int batch_number
    String username    
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address	
	static belongsTo=[courseoffering:CourseOffering,loadtype:LoadType,division:Division]
    static constraints = {
    }
}
