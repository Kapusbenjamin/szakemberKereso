/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.List;
import szakemberkereso.Model.Jobs;

/**
 *
 * @author Sharkz
 */
public class JobsService {
            
    public Jobs getJobById(Jobs job){
        Jobs result = Jobs.getJobById(job);
        return result;
    }
    
    public Boolean deleteJob(Jobs job){
        Boolean result = Jobs.deleteJob(job);
        return result;
    }
    
    public Boolean changeJobStatus(Jobs job){
        Boolean result = Jobs.changeJobStatus(job);
        return result;
    }
    
    public List<Jobs> getAllJobs(){
        List<Jobs> result = Jobs.getAllJobs();
        return result;
    }
    
    public List<Jobs> getAllJobsByWorker(Jobs job){
        List<Jobs> result = Jobs.getAllJobsByWorker(job);
        return result;
    }
    
    public List<Jobs> getAllJobsByCustomer(Jobs job){
        List<Jobs> result = Jobs.getAllJobsByCustomer(job);
        return result;
    }
    
    public String createJob(Jobs job){
        String result = Jobs.createJob(job);
        return result;
    }
    
    public String updateJobByWorker(Jobs job){
        String result = Jobs.updateJobByWorker(job);
        return result;
    }
    
    public String updateJobByCustomer(Jobs job){
        String result = Jobs.updateJobByCustomer(job);
        return result;
    }
    
    public Boolean acceptByWorker(Jobs job){
        Boolean result = Jobs.acceptByWorker(job);
        return result;
    }
    
    public Boolean acceptByCustomer(Jobs job){
        Boolean result = Jobs.acceptByCustomer(job);
        return result;
    }
    
            
}
