import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * This project implements a simple application. Properties from a fixed
 * file can be displayed. 
 * Student Name: Jiangjing Xu
 * K Number: 21134158
 * @author Michael Kölling and Josh Murphy
 * @version 1.0
 */
public class PropertyViewer
{    
    private PropertyViewerGUI gui;     // the Graphical User Interface
    private Portfolio portfolio;    
    private Property property;
    private int index;
    private int numberOfPropertiesViewed;
    private int totalPropertiesPrice;
    /**
     * Create a PropertyViewer and display its GUI on screen.
     */
    public PropertyViewer()
    {
        index = 0;
        numberOfPropertiesViewed = 1;
        
        gui = new PropertyViewerGUI(this);
        portfolio = new Portfolio("airbnb-london.csv");
        property = portfolio.getProperty(index);
       
        gui.showProperty(property);
        gui.showID(property);
        gui.showFavourite(property);
        
        totalPropertiesPrice = property.getPrice();
    }

    /**
     * Display the next property and update the data of ID and isFavourite correctly.
     * Return to the first property if the Next button is pressed while on the last property.
     */
    public void nextProperty()
    {
        index += 1;
        if(index < portfolio.numberOfProperties()){
            property = portfolio.getProperty(index);
        }
        else{
            property = portfolio.getProperty(0);
            index = 0;
        }
        
        gui.showProperty(property);
        gui.showID(property);
        gui.showFavourite(property);
        
        numberOfPropertiesViewed += 1;
        totalPropertiesPrice += property.getPrice();
    }

    /**When the Previous button is pressed, the previous property should be displayed, with the
     * correct data. 
     * Furthermore, the ID at the top should be updated correctly, as well as whether the property is one of the user’s favourites. 
     * The application should go to the last property if the Previous button is pressed while on the first property.
     */
    public void previousProperty()
    {
        index -= 1;
        if(index >= 0){
            property = portfolio.getProperty(index);
        }
        else{
            property = portfolio.getProperty(portfolio.numberOfProperties()-1);
            index = portfolio.numberOfProperties()-1;
        }
        
        gui.showProperty(property);
        gui.showID(property);
        gui.showFavourite(property); 
        
        numberOfPropertiesViewed += 1;
        totalPropertiesPrice += property.getPrice();
    }

    /**
     * When the Toggle Favourite button is pressed, the isFavourite field 
     * of that property should be updated. 
     * There is a method in the Property class to do this.
     */
    public void toggleFavourite()
    {
        property.toggleFavourite();
    }
    

    //----- methods for challenge tasks -----
    
    /**
     * This method opens the system's default internet browser
     * The Google maps page should show the current properties location on the map.
     */
    public void viewMap() throws Exception
    {
       double latitude = property.getLatitude();
       double longitude = property.getLongitude();
       
       URI uri = new URI("https://www.google.com/maps/place/" + latitude + "," + longitude);
       java.awt.Desktop.getDesktop().browse(uri); 
    }
    
    /**
     * Return the total count of viewed properties since the application started.
     * Viewing the same property twice counts as two views
     */
    public int getNumberOfPropertiesViewed()
    {
        return numberOfPropertiesViewed;
    }
    
    /**
     * Returns the average price of the properties viewed so far.
     */
    public int averagePropertyPrice()
    {
        return totalPropertiesPrice/numberOfPropertiesViewed;
    }
}
