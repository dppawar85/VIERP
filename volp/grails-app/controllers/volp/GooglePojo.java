package volp;
//package com.sl;

public class GooglePojo
{
    String id;
    String email;
    boolean verified_email;
    String name;
    String given_name;
    String family_name;
    String gender;
    public String getId()
    {
        return this.id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getGender()
    {
        return this.gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public boolean isVerified_email()
    {
        return this.verified_email;
    }

    public void setVerified_email(boolean verified_email)
    {
        this.verified_email = verified_email;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getGiven_name()
    {
        return this.given_name;
    }

    public void setGiven_name(String given_name)
    {
        this.given_name = given_name;
    }

    public String getFamily_name()
    {
        return this.family_name;
    }

    public void setFamily_name(String family_name)
    {
        this.family_name = family_name;
    }

    public String toString()
    {
        return

                "GooglePojo [id=" + this.id + ", email=" + this.email + ", gender=" + this.gender+ ", verified_email=" + this.verified_email + ", name=" + this.name + ", given_name=" + this.given_name + ", family_name=" + this.family_name + "]";
    }
}
