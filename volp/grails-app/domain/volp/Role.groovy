package volp

class Role {

    String role
    boolean isRoleSet
    String username    
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address    
    static belongsTo=[roletype:RoleType]
    static constraints = {
    	role nullable:false
    }
    String toString(){
    	role
    }
}
