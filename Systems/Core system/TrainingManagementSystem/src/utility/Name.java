package utility;

/**
 *
 * @author Home
 */
public class Name {
    private Title title;
    private String firstName;
    private String lastName;

    /**
     * Constructor for Name
     * 
     * @param aTitle
     * @param aFirstName
     * @param aLastName 
     */
    public Name(Title aTitle, String aFirstName, String aLastName) {
        title = aTitle;
        firstName = validate(aFirstName);
        lastName = validate(aLastName);
    }
    
    /**
     * Constructor for Name
     * 
     * @param aTitle
     * @param aFirstName
     * @param aLastName 
     */
    public Name(String aTitle, String aFirstName, String aLastName) {
        title = toTitle(aTitle);
        firstName = validate(aFirstName);
        lastName = validate(aLastName);
    }
    
    /**
     * Converts a string title into the corresponding type Enum
     * 
     * @param aTitle
     * @return 
     */
    public Title toTitle(String aTitle)
    {
        switch(aTitle)
        {
            case "mr":
                return Title.Mr;
            case "mrs":
                return Title.Mrs;
            case "miss":
                return Title.Miss;
            case "mx":
                return Title.Mx;
            case "ms":
                return Title.Ms;                 
        }
        return null;
    }
    
    /**
     * Returns the title for a person
     * 
     * @return title
     */
    public Title getTitle() {
        return title;
    }

    /**
     * Returns the first name of a person.
     * 
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of a person.
     * 
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the title of a person.
     * 
     * @param aTitle title of a person
     */
    public void setTitle(Title aTitle) {
        title = aTitle;
    }

    /**
     * Sets the first name of a person.
     * 
     * @param aFirstName first name of a person.
     */
    public void setFirstName(String aFirstName) {
        firstName = aFirstName;
    }

    /**
     * The first name of a person.
     * 
     * @param aLastName, first name of a person.
     */
    public void setLastName(String aLastName) {
        lastName = aLastName;
    }

    /**
     * A method to check for no input of name fields
     * 
     * @param aString 
     */
    private String validate(String aString)
    {
        if(aString.isEmpty())
        {
            throw new IllegalArgumentException("Please enter a name");
        }
        return aString;
    }
    
    /**
     * String value showing the state of the object.
     * 
     * @return title, firstName, lastName
     */
    @Override
    public String toString() {
        return title + " " + firstName + " " + lastName;
    }
    
    
    
}
