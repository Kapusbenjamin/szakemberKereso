/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.List;
import java.util.Objects;
import javax.ws.rs.ForbiddenException;
import szakemberkereso.Configuration.Roles;
import szakemberkereso.Model.JobTags;
import szakemberkereso.Model.UsersJobs;

/**
 *
 * @author Sharkz
 */
public class UsersJobsService {
    
    public void addNewJobToUser(UsersJobs user_job) throws Exception{
        //USER jogosultsággal nem lehet szakmája
        if(AuthService.isUserAuthorized(user_job.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})) {
            //a user-ek csak maguknak adhatnak hozzá job_taget/szakmát
            if(!Objects.equals(user_job.getUserId(), user_job.getCurrentUserId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            UsersJobs.addNewJobToUser(user_job);
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
    
    public void deleteUserJob(UsersJobs user_job) throws Exception{
        //USER jogosultsággal nem lehet szakmája
        if(AuthService.isUserAuthorized(user_job.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})) {
            //a user-ek csak maguktól vehetnek el job_taget/szakmát
            if(!Objects.equals(user_job.getUserId(), user_job.getCurrentUserId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            UsersJobs.deleteUserJob(user_job);
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
    
    public List<JobTags> getAllJobsByUser(Integer user_id_in) throws Exception{
        List<JobTags> result = UsersJobs.getAllJobsByUser(user_id_in);
        return result;
    }
    
}
