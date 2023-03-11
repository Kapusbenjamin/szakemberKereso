/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Configuration;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Sharkz
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(szakemberkereso.Configuration.CorsFilter.class);
        resources.add(szakemberkereso.Controller.AddressesController.class);
        resources.add(szakemberkereso.Controller.AdsController.class);
        resources.add(szakemberkereso.Controller.AdsCountiesController.class);
        resources.add(szakemberkereso.Controller.ChatsController.class);
        resources.add(szakemberkereso.Controller.CompaniesController.class);
        resources.add(szakemberkereso.Controller.CountiesController.class);
        resources.add(szakemberkereso.Controller.FavoritesController.class);
        resources.add(szakemberkereso.Controller.ImagesController.class);
        resources.add(szakemberkereso.Controller.JobTagsController.class);
        resources.add(szakemberkereso.Controller.JobsController.class);
        resources.add(szakemberkereso.Controller.MessagesController.class);
        resources.add(szakemberkereso.Controller.RatingsController.class);
        resources.add(szakemberkereso.Controller.UsersController.class);
        resources.add(szakemberkereso.Controller.UsersJobsController.class);
    }
    
}
