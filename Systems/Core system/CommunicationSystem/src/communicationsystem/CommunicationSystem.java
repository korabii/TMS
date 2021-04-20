package communicationsystem;

public class CommunicationSystem
{
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Communicator communicator = new Communicator();
        //communicator.sendQualifiedAssistantReminder();
        //communicator.sendJuniorSalesAssistantReminder();
        //communicator.sendSalesAssistantReminder();
        //communicator.sendSeniorSalesAssistantReminder();
        
        communicator.sendAll();
    }
    
}
