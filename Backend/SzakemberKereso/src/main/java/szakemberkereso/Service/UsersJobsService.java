/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.List;
import szakemberkereso.Model.JobTags;
import szakemberkereso.Model.UsersJobs;

/**
 *
 * @author Sharkz
 */
public class UsersJobsService {
    
    public String addNewJobToUser(UsersJobs user_job){
        String result = UsersJobs.addNewJobToUser(user_job);
        return result;
    }
    
    public String deleteUserJob(UsersJobs user_job){
        String result = UsersJobs.deleteUserJob(user_job);
        return result;
    }
    
    public List<JobTags> getAllJobsByUser(Integer user_id_in){
        List<JobTags> result = UsersJobs.getAllJobsByUser(user_id_in);
        return result;
    }
    
    
}
