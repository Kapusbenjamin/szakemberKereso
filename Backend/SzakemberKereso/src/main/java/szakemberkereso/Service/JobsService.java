/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.List;
import java.util.Objects;
import javax.mail.AuthenticationFailedException;
import javax.ws.rs.ForbiddenException;
import szakemberkereso.Configuration.Roles;
import szakemberkereso.Model.Jobs;

/**
 *
 * @author Sharkz
 */
public class JobsService {
            
    public Jobs getJobById(Jobs job) throws Exception{
        if (AuthService.isUserAuthorized(job.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.USER, Roles.WORKER})) {
            if(!AuthService.isUserAuthorized(job.getCurrentUserId(), new Roles[]{Roles.ADMIN})){
                //a nem ADMIN jogosultságú user-ek csak a saját munkájukat kérhetik le
                if(Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getCustomerId()) || Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getWorkerId())){
                    Jobs result = Jobs.getJobById(job);
                    return result;
                }
                else{
                    throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
                }
            }
            Jobs result = Jobs.getJobById(job);
            return result;
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani.");
        }
    }
    
    public void deleteJob(Jobs job) throws Exception{
        if (AuthService.isUserAuthorized(job.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.USER, Roles.WORKER})) {
            //csak azok a user-ek törölhetik akik a munka tagjai
            if(Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getCustomerId()) || Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getWorkerId())){
                Jobs.deleteJob(job);
            }
            else{
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani.");
        }
    }
    
    public void changeJobStatus(Jobs job) throws Exception{
        if (AuthService.isUserAuthorized(job.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.USER, Roles.WORKER})) {
            //a user-ek csak a saját munkájukat módosíthatják
            if(Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getCustomerId()) || Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getWorkerId())){
                Jobs.changeJobStatus(job);
            }
            else{
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani.");
        }
    }
    
    public List<Jobs> getAllJobs(Integer userId) throws Exception{
        //csak ADMIN jogosultságú user-ek kérhetik le
        if (AuthService.isUserAuthorized(userId, new Roles[]{Roles.ADMIN})) {
            List<Jobs> result = Jobs.getAllJobs();
            return result;
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
    
    public List<Jobs> getAllJobsByWorker(Jobs job) throws Exception{
        if (AuthService.isUserAuthorized(job.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})) {
            //csak azok a user-ek kérhetik le akik a munkában a worker oldalon állnak
            if(!Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getWorkerId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            List<Jobs> result = Jobs.getAllJobsByWorker(job);
            return result;
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
    
    public List<Jobs> getAllJobsByCustomer(Jobs job) throws Exception{
        if (AuthService.isUserAuthorized(job.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.USER, Roles.WORKER})) {
            //csak azok a user-ek kérhetik le akik a munkában a customer oldalon állnak
            if(!Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getCustomerId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            List<Jobs> result = Jobs.getAllJobsByCustomer(job);
            return result;
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani.");            
        }
    }
    
    public void createJob(Jobs job) throws Exception{
        Jobs.createJob(job);
    }
    
    public void updateJobByWorker(Jobs job) throws Exception{
        if (AuthService.isUserAuthorized(job.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})) {
            //csak azok a user-ek módosíthatják akik a munkában a worker oldalon állnak
            if(!Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getWorkerId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            Jobs.updateJobByWorker(job);
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
    
    public void updateJobByCustomer(Jobs job) throws Exception{
        if (AuthService.isUserAuthorized(job.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.USER, Roles.WORKER})) {
            //csak azok a user-ek módosíthatják akik a munkában a customer oldalon állnak
            if(!Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getCustomerId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            Jobs.updateJobByCustomer(job);
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani.");
        }
    }
    
    public void acceptByWorker(Jobs job) throws Exception{
        if (AuthService.isUserAuthorized(job.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})) {
            //csak azok a user-ek fogadhatják el akik a munkában a worker oldalon állnak
            if(!Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getWorkerId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            Jobs.acceptByWorker(job);
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
    
    public void acceptByCustomer(Jobs job) throws Exception{
        if (AuthService.isUserAuthorized(job.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.USER, Roles.WORKER})) {
            //csak azok a user-ek fogadhatják el akik a munkában a customer oldalon állnak
            if(!Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getCustomerId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            Jobs.acceptByCustomer(job);
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani.");
        }
    }
            
}
