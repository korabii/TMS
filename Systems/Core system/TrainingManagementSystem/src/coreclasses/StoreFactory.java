/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreclasses;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import utility.Address;
import utility.ID;
import utility.IDClass;
import utility.ServiceRequest;


public class StoreFactory
{
    private Store store;
    
    /**
     * Initialises the store object
     * 
     * @param anID 
     */
    public StoreFactory(ID anID)
    {
        initialise(anID);
    }
    
    /**
     * Retrieves the information for the store corresponding to the provided ID 
     * and creates the object with the information from the database.
     * 
     * @param anID 
     */
    public void initialise(ID anID)
    {
        String url = "http://localhost/tms_rest_api/api/stores/find_store.php?";
        String param = "store_id=" + anID.getID().toLowerCase();
        
        Gson gs = new Gson();
        JsonArray ja = gs.fromJson(new ServiceRequest().get(url, param), JsonArray.class);
        JsonObject jo = ja.get(0).getAsJsonObject();
        
        store = new Store(
            new ID(IDClass.ST, anID.getCode()),
            new Address
            (
                jo.get("number").getAsInt(), 
                jo.get("streetname").getAsString(), 
                jo.get("city").getAsString(), 
                jo.get("postcode").getAsString().toUpperCase()
            )  
        );              
    }
        
    /**
     * The linked store object.
     * 
     * @return store
     */
    public Store getStore()
    {
        return store;
    }

    /**
     * String value of store.
     * 
     * @return store
     */
    @Override
    public String toString()
    {
        return "StoreFactory{" + "store=" + store + '}';
    }
    
    
    
}
