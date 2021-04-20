package utility;


public class Address
{
    private int number;
    private String streetName;
    private String city;
    private String postcode;

    /**
     * Constructor for address objects
     * 
     * @param aNumber
     * @param aStreetName
     * @param aCity
     * @param aPostcode 
     */
    public Address(int aNumber, String aStreetName, String aCity, String aPostcode)
    {
        number = aNumber;
        streetName = aStreetName;
        city = aCity;
        postcode = validate(aPostcode);
    }

    /**
     * Number of the address
     * 
     * @return Address number
     */
    public int getNumber()
    {
        return number;
    }

    /**
     * Street name of the address
     * 
     * @return Street name
     */
    public String getStreetName()
    {
        return streetName;
    }

    /**
     * City of the address
     * 
     * @return city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * postcode of the address
     * 
     * @return postcode
     */
    public String getPostcode()
    {
        return postcode;
    }

    /**
     * Sets the address number
     * 
     * @param aNumber 
     */
    public void setNumber(int aNumber)
    {
        number = aNumber;
    }

    /**
     * Sets the street name
     * 
     * @param aStreetName 
     */
    public void setStreetName(String aStreetName)
    {
        streetName = aStreetName;
    }

    /**
     * Sets the city
     * 
     * @param aCity
     */
    public void setCity(String aCity)
    {
        city = aCity;
    }

    /**
     * Sets the postcode
     * 
     * @param aPostcode 
     */
    public void setPostcode(String aPostcode)
    {
        postcode = validate(aPostcode);
    }
    
    /**
     * Checks that the provided post code is valid.
     * 
     * @param aPostCode
     * @return aPostCode, returns aPostCode if the postcode is valid.
     */
    private String validate(String aPostCode)
    {
        if (aPostCode.matches("^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$"))
        {
            return aPostCode;
        }
        else
        {
            throw new IllegalArgumentException("Please enter a valid UK postcode including a space.");
        }
    }

    /**
     * String representation of the address
     * 
     * @return number, streetName, city, and postcode.
     */
    @Override
    public String toString()
    {
        return "Address: \n" + number + ",\n" + streetName 
                + ",\n" + city + ",\n" + postcode;
    }
    
    
    
}
