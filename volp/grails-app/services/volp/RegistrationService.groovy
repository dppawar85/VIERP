package volp

import grails.gorm.transactions.Transactional
import grails.gorm.services.Service
@Transactional
class RegistrationService {

    def serviceMethod() {

    }
    int registeruser(String email,String password,String firstname,String lastname,String usertype,String ip)
    {
        println("I am in registration service...")
        Login l=Login.findByUsername(email)
        println("L:"+l)
        if(l==null)
        {
            //insert
            l=new Login()
            l.username=email
            l.password=password
            l.creation_date=new java.util.Date()
            l.updation_date=new java.util.Date()
            l.creation_ip_address=ip
            l.updation_ip_address=ip
            l.isloginblocked=false
            ApplicationType at=ApplicationType.findByApplication_type("VOLP")
            UserType ut=UserType.findByTypeAndApplication_type(usertype,at)
            l.usertype=ut
            RoleType rt=RoleType.findByType("VOLP")
            Role r=null
            r=Role.findByRoleAndRoletype(usertype,rt)
            l.addToRoles(r)
            l.save(failOnError:true,flush:true)
            Person pobj=null
            if(!usertype.equals("Organization"))
            {
                Person p=new Person()
                p.email=email
                p.firstName=firstname
              //  p.middleName=null
              //  p.date_of_birth=null
                p.username=email
                p.lastName=lastname
                p.creation_date=new java.util.Date()
                p.updation_date=new java.util.Date()
                p.creation_ip_address=ip
                p.updation_ip_address=ip
               // p.gender=null
                p.save(failOnError:true,flush:true)
                pobj=Person.findByEmail(email)
            }
            if(usertype.equals("Instructor"))
            {
                Instructor i=new Instructor()
                i.uid=email
              //  i.employee_code=null
                i.username=email
                i.creation_date=new java.util.Date()
                i.updation_date=new java.util.Date()
                i.creation_ip_address=ip
                i.updation_ip_address=ip
                i.rating=0
                i.person=pobj
              //  i.organization=null
              //  i.designation=null
              //  i.program=null
                i.save(failOnError:true,flush:true)
            }
            if(usertype.equals("Learner"))
            {
                Learner len=new Learner()
                len.uid=email
              //  len.registration_number=null
               // len.graduation_status=""
                len.username=email
                len.creation_date=new java.util.Date()
                len.updation_date=new java.util.Date()
                len.creation_ip_address=ip
                len.updation_ip_address=ip
                len.person=pobj
              //  len.organization=null
               // len.current_module=null
               // len.current_year=null
              //  len.program=null
                len.save(failOnError:true,flush:true)
            }
            if(usertype.equals("Organization"))
            {
                Organization org=new Organization()
                org.uid = email
                org.email=email
              //  org.organization_name=null
              //  org.organization_code=null
              //  org.registration_number=null
              //  org.display_name=null
                org.username=email
                org.creation_date=new java.util.Date()
                org.updation_date=new java.util.Date()
                org.creation_ip_address=ip
                org.updation_ip_address=ip
               // org.organization=null
               // org.organizationtype=null
                org.save(failOnError:true,flush:true)
            }
            return 1
        }
        else
        {
            return 0
        }
    }

    int registeruserapi(String email,String password,String firstname,String lastname,String usertype,String ip,String social)
    {
        println("I am in registration service...")
        Login l=Login.findByUsername(email)
        println("L:"+l)
        if(l==null)
        {
            //insert
            SocialAccountType st = SocialAccountType.findBySocial_site_name(social)
            l=new Login()
            l.username=email
            l.password=password
            l.creation_date=new java.util.Date()
            l.updation_date=new java.util.Date()
            l.creation_ip_address=ip
            l.updation_ip_address=ip
            l.socialaccounttype = st
            l.isloginblocked=false
            ApplicationType at=ApplicationType.findByApplication_type("VOLP")
            UserType ut=UserType.findByTypeAndApplication_type(usertype,at)
            l.usertype=ut
            RoleType rt=RoleType.findByType("VOLP")
            Role r=null
            r=Role.findByRoleAndRoletype(usertype,rt)
            l.addToRoles(r)
            l.save(failOnError:true,flush:true)
            Person pobj=null
            if(!usertype.equals("Organization"))
            {
                Person p=new Person()
                p.email=email
                p.firstName=firstname
                //  p.middleName=null
                //  p.date_of_birth=null
                p.username=email
                p.lastName=lastname
                p.creation_date=new java.util.Date()
                p.updation_date=new java.util.Date()
                p.creation_ip_address=ip
                p.updation_ip_address=ip
                // p.gender=null
                p.save(failOnError:true,flush:true)
                pobj=Person.findByEmail(email)
            }
            if(usertype.equals("Instructor"))
            {
                Instructor i=new Instructor()
                i.uid=email
                //  i.employee_code=null
                i.username=email
                i.creation_date=new java.util.Date()
                i.updation_date=new java.util.Date()
                i.creation_ip_address=ip
                i.updation_ip_address=ip
                i.rating=0
                i.person=pobj
                //  i.organization=null
                //  i.designation=null
                //  i.program=null
                i.save(failOnError:true,flush:true)
            }
            if(usertype.equals("Learner"))
            {
                Learner len=new Learner()
                len.uid=email
                //  len.registration_number=null
                // len.graduation_status=""
                len.username=email
                len.creation_date=new java.util.Date()
                len.updation_date=new java.util.Date()
                len.creation_ip_address=ip
                len.updation_ip_address=ip
                len.person=pobj
                //  len.organization=null
                // len.current_module=null
                // len.current_year=null
                //  len.program=null
                len.save(failOnError:true,flush:true)
            }
            if(usertype.equals("Organization"))
            {
                Organization org=new Organization()
                org.email=email
                //  org.organization_name=null
                //  org.organization_code=null
                //  org.registration_number=null
                //  org.display_name=null
                org.username=email
                org.creation_date=new java.util.Date()
                org.updation_date=new java.util.Date()
                org.creation_ip_address=ip
                org.updation_ip_address=ip
                // org.organization=null
                // org.organizationtype=null
                org.save(failOnError:true,flush:true)
            }
            return 1
        }
        else
        {
            return 0
        }
    }
}
