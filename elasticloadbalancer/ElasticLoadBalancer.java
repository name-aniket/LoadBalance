/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticloadbalancer;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @created 30/01/2020
 * @author aniket
 */
public class ElasticLoadBalancer {

    /*
     * This variable is used to store Id for the object. 
     */
    private final String elasticLoadBalancerID;

    /*
     * This list holds all the compute instances 
     */
    private ArrayList<ElasticComputeInstance> instanceList;

    /**
     * This variable is used to share the load.
     */
    int chance;
    
    public ElasticLoadBalancer() {
        this.elasticLoadBalancerID = UUID.randomUUID().toString();
        this.instanceList = new ArrayList<>();
        this.chance = 0;
    }
    
    public String getId() {
        return this.elasticLoadBalancerID;
    }
    
    public void assignRequestToEC2() {
        /**
         * Whenever a request comes This function will assign the request to
         * available compute instance The assignment should follow round robin
         * technique.
         */
        this.instanceList.get(this.chance++ % this.instanceList.size()).newTask();
    }
    
    public void addEC2(ElasticComputeInstance newInstance) {
        /*  
         *   Whenever we want to add a new compute instance.
         *   This function will be called.
         *   The number of instance is added according to the auto scale policy.
         *   This auto scale policy is defined by the customer. 
         */
        this.instanceList.add(newInstance);
    }
    
    public void removeEC2() {
        /*  
         * Whenever we want to delete/remove the compute instance.
         * This function is used.
         * The instances were removed according to the load.
         */
        this.instanceList.remove(0);
    }

    /**
     * This function is use to scale Whenever CPU utilization crosses the
     * specified threshold All the logic for provisioning will come under this
     * only
     *
     * @param autoScalePolicyObject
     */
    public void scaleResourceAccordingToPolicy(AutoScalePolicy autoScalePolicyObject) {
        
        System.out.println("\n\t Load Balancer is scaling up current utilization > threshold");
        
        if (autoScalePolicyObject.getCountLeftResource() > 0) {
            
            autoScalePolicyObject.setCountLeftResource(autoScalePolicyObject.getCountLeftResource() - autoScalePolicyObject.getMinimumEC2());
            
            ElasticComputeInstance forCopy = this.instanceList.get(0);
            
            for (int i = 0; i < autoScalePolicyObject.getMinimumEC2(); i++) {
                
                System.out.println("\n ==Added EC2===");
                
                this.addEC2(new ElasticComputeInstance(forCopy));
            }
        }
    }

    /**
     * This method meant to return list of EC2 So that CloudWatch can check the
     *
     * @return
     */
    public ArrayList getListofEC2() {
        return this.instanceList;
    }
    
    @Override
    public String toString() {
        return "";
    }
}
