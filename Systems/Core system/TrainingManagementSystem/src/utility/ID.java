package utility;


public class ID
{
    private IDClass classifier;
    private String code;

    /**
     * ID constructor
     * 
     * @param aCode 
     */
    public ID(String aCode)
    {
        
        classifier = null;
        switch (aCode.substring(0, 2))
        {
            case "td":
                classifier = classifier.TD;
                break;
            case "pn":
                classifier = classifier.PN;
                break;
            case "st":
                classifier = classifier.ST;
                break;
            default:
                classifier = null;
        }
               
        code = validate(aCode.substring(2));
    }
    
    /**
     * ID constructor
     * 
     * @param aClassifier
     * @param aCode 
     */
    public ID(IDClass aClassifier, String aCode)
    {
        classifier = aClassifier;
        code = validate(aCode);
    }

    /**
     * Checks that the provided string contains 8 numbers.
     * 
     * @param aCode 
     * @return aCode, returns aCode if the string is valid.
     */
    private String validate(String aCode)
    {
        if (aCode.length() == 8 && aCode.matches("-?\\d+"))
        {
            return aCode;
        }
        else
        {
            throw new IllegalArgumentException("The ID must contain 8 numbers");
        }
    }

    /**
     *Id of the Person, Store or Training department.
     * 
     * @return ID
     */
    public String getID()
    {
        return classifier + code;
    }

    /**
     * Returns the first part of the ID consisting of characters
     * 
     * @return  classifier
     */
    public IDClass getClassifier()
    {
        return classifier;
    }

    /**
     * Returns the code part of the idea consisting of digits
     * 
     * @return code
     */
    public String getCode()
    {
        return code;
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    @Override 
    public boolean equals(Object obj) {
            ID anotherID = (ID)obj; 
            return getCode().equals(anotherID.getCode()) 
                    && getClassifier() == anotherID.getClassifier();
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() { 
        return getCode().length(); 
    }
    
    /**
     *the state of the object.
     * 
     * @return 
     */
    @Override
    public String toString()
    {
        return "ID{" + "classifier=" + classifier + ", code=" + code + '}';
    }

}
