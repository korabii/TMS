package utility;

import coreclasses.Type;

/**
 *Responsible for converting between core 
 * system to database training event types (adapter pattern).
 * 
 */
public class TrainingConverterHelper
{
    private Type type;
    
    public TrainingConverterHelper()
    {
        type = null;
    }
    
    /**
     * Coverts training event type from Type to String
     * @param aType
     * @return converted value
     */
    public String toString(Type aType)
    {
        switch(aType)
        {
            case SalesAssistant:
                return "sa";
            case SeniorSalesAssistant:
                return "ss";
            case JuniorSalesAssistant:
                return "js";
            case QualifiedSalesAssistant:
                return "qs";
            default:
                return null;                          
        }
    }
    
    public Type toType(String aType)
    {        
        switch(aType)
        {
            case "sa":
                return Type.SalesAssistant;
            case "ss":
                return Type.SeniorSalesAssistant;
            case "js":
                return Type.JuniorSalesAssistant;
            case "qs":
                return Type.QualifiedSalesAssistant;  
            default:
                return null;
        }        
    }
    
}
