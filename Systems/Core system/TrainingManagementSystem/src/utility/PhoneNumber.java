package utility;

public class PhoneNumber
{

    private String mobileNumber;

    /**
     * PhoneNumber constructor
     * 
     * @param aMobileNumber 
     */
    public PhoneNumber(String aMobileNumber)
    {
        mobileNumber = validate(aMobileNumber);
    }

    /**
     * Gets a valid mobile phone number
     * 
     * @return mobileNumber; a valid mobile phone number
     */
    public String getMobileNumber()
    {
        return mobileNumber;
    }

    /**
     * Sets and checks the new mobile phone number.
     * 
     * @param aMobileNumber 
     */
    public void setMobileNumber(String aMobileNumber)
    {
        mobileNumber = aMobileNumber;
    }
    
    /**
     * Checks that the string contains only 11 numbers and no other characters.
     * 
     * @param aMobileNumber
     * @return aMobileNumber; a valid mobile phone number.
     */
    private String validate(String aMobileNumber)
    {
        if (aMobileNumber.length() == 11 && aMobileNumber.matches("-?\\d+")
                && aMobileNumber.charAt(0) == '0' && aMobileNumber.charAt(1) == '7')
        {
            return aMobileNumber;
        } 
        else
        {
            throw new IllegalArgumentException("Enter a valid UK mobile phone number");
        }
    }

    /**
     * Returns the State of the object
     * 
     * @return mobileNumber.
     */
    @Override
    public String toString()
    {
        return "PhoneNumber = "  + mobileNumber;
    }
}
