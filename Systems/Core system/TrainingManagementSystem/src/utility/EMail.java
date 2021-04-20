package utility;

public class EMail
{

    private String eMail;

    /**
     * Constructor for an Email object.
     * 
     * @param anEMail an email address
     */
    public EMail(String anEMail)
    {
        eMail = validate(anEMail);
    }

    /**
     * A validated email address
     * 
     * @return an email address.
     */
    public String geteMail()
    {
        return eMail;
    }

    /**
     * Set and validate a new email address.
     * 
     * @param anEMail 
     */
    public void seteMail(String anEMail)
    {
        eMail = validate(anEMail);
    }

    /**
     * Checks weather the email address is written in a standard email 
     * address form.
     * 
     * @param anEMail
     * @return 
     */
    private String validate(String anEMail)
    {
        if(anEMail.matches("^.+@.+\\..+$"))
        {
            return anEMail;
        }
        else
        {
            throw new IllegalArgumentException("Enter a valid email address");
        }
    }

    /**
     * returns email
     * 
     * @return email
     */
    @Override
    public String toString()
    {
        return "eMail = " + eMail;
    }

    
}
