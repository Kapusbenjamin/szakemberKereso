///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
// */
//package szakemberkereso.Controller;
//
//import java.util.List;
//import java.util.Objects;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.UriInfo;
//import javax.ws.rs.Produces;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PUT;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import szakemberkereso.Configuration.Roles;
//import szakemberkereso.Model.Jobs;
//import szakemberkereso.Service.AuthService;
//import szakemberkereso.Service.JobsService;
//
///**
// * REST Web Service
// *
// * @author Sharkz
// */
//@Path("Jobs")
//public class JobsController {
//
//    @Context
//    private UriInfo context;
//    
//    private JobsService js = new JobsService();
//
//    /**
//     * Creates a new instance of JobsController
//     */
//    public JobsController() {
//    }
//
//    /**
//     * Retrieves representation of an instance of szakemberkereso.Controller.JobsController
//     * @return an instance of java.lang.String
//     */
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getJson() {
//        //TODO return proper representation object
//        throw new UnsupportedOperationException();
//    }
//
//    /**
//     * PUT method for updating or creating an instance of JobsController
//     * @param content representation for the resource
//     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void putJson(String content) {
//    }
//    
//    @POST
//    @Path("getJobById")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response getJobById(Jobs job){
//        if (AuthService.isUserAuthorized(job.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.USER, Roles.WORKER})) {
//            if(!AuthService.isUserAuthorized(job.getCurrentUserId(), new Roles[]{Roles.ADMIN})){
//                //a nem ADMIN jogosultságú user-ek csak a saját munkájukat kérhetik le
//                if(Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getCustomerId()) || Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getWorkerId())){
//                    Jobs result = js.getJobById(job);
//                    return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
//                }
//                else{
//                    return Response.status(Response.Status.FORBIDDEN).entity("Nincs jogosultsága ehhez a kéréshez.").build();
//                }
//            }
//            Jobs result = js.getJobById(job);
//            return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
//        }
//        return Response.status(Response.Status.UNAUTHORIZED).entity("Nem sikerült azonosítani.").build();
//    }
//    
//    @POST
//    @Path("deleteJob")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response deleteJob(Jobs job){
//        if (AuthService.isUserAuthorized(job.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.USER, Roles.WORKER})) {
//            //csak azok a user-ek törölhetik akik a munka tagjai
//            if(Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getCustomerId()) || Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getWorkerId())){
//                Boolean result = js.deleteJob(job);
//                return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
//            }
//            else{
//                return Response.status(Response.Status.FORBIDDEN).entity("Nincs jogosultsága ehhez a kéréshez.").build();
//            }
//        }
//        return Response.status(Response.Status.UNAUTHORIZED).entity("Nem sikerült azonosítani.").build();
//    }
//    
//    @POST
//    @Path("changeJobStatus")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response changeJobStatus(Jobs job){
//        if (AuthService.isUserAuthorized(job.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.USER, Roles.WORKER})) {
//            //a user-ek csak a saját munkájukat módosíthatják
//            if(Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getCustomerId()) || Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getWorkerId())){
//                Boolean result = js.changeJobStatus(job);
//                return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
//            }
//            else{
//                return Response.status(Response.Status.FORBIDDEN).entity("Nincs jogosultsága ehhez a kéréshez.").build();
//            }
//        }
//        return Response.status(Response.Status.UNAUTHORIZED).entity("Nem sikerült azonosítani.").build();
//    }
//    
//    @POST
//    @Path("getAllJobs")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response getAllJobs(Integer userId){
//        //csak ADMIN jogosultságú user-ek kérhetik le
//        if (AuthService.isUserAuthorized(userId, new Roles[]{Roles.ADMIN})) {
//            List<Jobs> result = js.getAllJobs();
//            return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
//        }
//        else{
//            return Response.status(Response.Status.FORBIDDEN).entity("Nincs jogosultsága ehhez a kéréshez.").build();
//        }
//    }
//    
//    @POST
//    @Path("getAllJobsByWorker")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response getAllJobsByWorker(Jobs job){
//        if (AuthService.isUserAuthorized(job.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})) {
//            //csak azok a user-ek módosíthatják akik a munkában a worker oldalon állnak
//            if(!Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getWorkerId())){
//                return Response.status(Response.Status.FORBIDDEN).entity("Nincs jogosultsága ehhez a kéréshez.").build();
//            }
//            List<Jobs> result = js.getAllJobsByWorker(job);
//            return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
//        }
//        return Response.status(Response.Status.UNAUTHORIZED).entity("Nem sikerült azonosítani.").build();
//    
//    }
//    
//    @POST
//    @Path("getAllJobsByCustomer")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response getAllJobsByCustomer(Jobs job){
//        if (AuthService.isUserAuthorized(job.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.USER, Roles.WORKER})) {
//            //csak azok a user-ek módosíthatják akik a munkában a customer oldalon állnak
//            if(!Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getCustomerId())){
//                return Response.status(Response.Status.FORBIDDEN).entity("Nincs jogosultsága ehhez a kéréshez.").build();
//            }
//            List<Jobs> result = js.getAllJobsByCustomer(job);
//            return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
//        }
//        return Response.status(Response.Status.UNAUTHORIZED).entity("Nem sikerült azonosítani.").build();
//    }
//    
//    @POST
//    @Path("createJob")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createJob(Jobs job){
//        String result = js.createJob(job);
//        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
//    }
//    
//    @POST
//    @Path("updateJobByWorker")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateJobByWorker(Jobs job){
//        if (AuthService.isUserAuthorized(job.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})) {
//            //csak azok a user-ek módosíthatják akik a munkában a worker oldalon állnak
//            if(!Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getWorkerId())){
//                return Response.status(Response.Status.FORBIDDEN).entity("Nincs jogosultsága ehhez a kéréshez.").build();
//            }
//            String result = js.updateJobByWorker(job);
//            return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
//        }
//        return Response.status(Response.Status.UNAUTHORIZED).entity("Nem sikerült azonosítani.").build();
//    }
//    
//    @POST
//    @Path("updateJobByCustomer")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateJobByCustomer(Jobs job){
//        if (AuthService.isUserAuthorized(job.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.USER, Roles.WORKER})) {
//            //csak azok a user-ek módosíthatják akik a munkában a customer oldalon állnak
//            if(!Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getCustomerId())){
//                return Response.status(Response.Status.FORBIDDEN).entity("Nincs jogosultsága ehhez a kéréshez.").build();
//            }
//            String result = js.updateJobByCustomer(job);
//            return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
//        }
//        return Response.status(Response.Status.UNAUTHORIZED).entity("Nem sikerült azonosítani.").build();
//    }
//    
//    @POST
//    @Path("acceptByWorker")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response acceptByWorker(Jobs job){
//        if (AuthService.isUserAuthorized(job.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})) {
//            //csak azok a user-ek módosíthatják akik a munkában a worker oldalon állnak
//            if(!Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getWorkerId())){
//                return Response.status(Response.Status.FORBIDDEN).entity("Nincs jogosultsága ehhez a kéréshez.").build();
//            }
//            Boolean result = js.acceptByWorker(job);
//            return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
//        }
//        return Response.status(Response.Status.UNAUTHORIZED).entity("Nem sikerült azonosítani.").build();
//    }
//    
//    @POST
//    @Path("acceptByCustomer")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response acceptByCustomer(Jobs job){
//        if (AuthService.isUserAuthorized(job.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.USER, Roles.WORKER})) {
//            //csak azok a user-ek módosíthatják akik a munkában a worker oldalon állnak
//            if(!Objects.equals(job.getCurrentUserId(), Jobs.getJobById(job).getCustomerId())){
//                return Response.status(Response.Status.FORBIDDEN).entity("Nincs jogosultsága ehhez a kéréshez.").build();
//            }
//            Boolean result = js.acceptByCustomer(job);
//            return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
//        }
//        return Response.status(Response.Status.UNAUTHORIZED).entity("Nem sikerült azonosítani.").build();
//    }
//    
//    
//}
