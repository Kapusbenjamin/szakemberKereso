/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.List;
import szakemberkereso.Model.JobTags;

/**
 *
 * @author Sharkz
 */
public class JobTagsService {
    
    public List<JobTags> getAllJobTags() throws Exception{
        List<JobTags> result = JobTags.getAllJobTags();
        return result;
    }
    
    public JobTags getJobTagById(Integer id) throws Exception{
        JobTags result = JobTags.getJobTagById(id);
        return result;
    }
    
}
