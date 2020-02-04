/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticloadbalancer;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author aniket
 */
public class CloudWatch {

    /**
     * Cloud Watch will be used to monitor usage Also use to provision resources
     * This will continously checks the CPU utilization.
     *
     * This attribute 'cloudWatchID' will store a unique ID
     */
    private final String cloudWatchID;

    /**
     * This attribute 'autoScalePolicyObject' will hold information regarding
     * the policy specified by the user while configuring auto scale policy.
     */
    AutoScalePolicy autoScalePolicyObject;

    /**
     * This thread will check the usage continously.
     */
    ScheduledFuture<?> scheduleAtFixedRate;

    /**
     * This object holds the instance for load balancing This object helps in
     * getting the list of EC2 instances.
     */
    ElasticLoadBalancer elasticLoadBalancer;

    /**
     * Initialize variables Don not start the thread now.
     *
     * @param elasticLoadBalancer
     * @param autoScalePolicy
     */
    public CloudWatch(ElasticLoadBalancer elasticLoadBalancer, AutoScalePolicy autoScalePolicy) {
        this.cloudWatchID = UUID.randomUUID().toString();
        this.autoScalePolicyObject = autoScalePolicy;
        this.elasticLoadBalancer = elasticLoadBalancer;
    }

    public void startWatching() {
        /**
         * Thread will be started Before calling this method, EC2 instances
         * should be initialized.
         */
        ScheduledExecutorService exec1 = Executors.newSingleThreadScheduledExecutor();

        this.scheduleAtFixedRate = exec1.scheduleAtFixedRate(() -> {

            ArrayList<ElasticComputeInstance> instanceList = elasticLoadBalancer.getListofEC2();

            double percentageUsage = instanceList.stream().mapToDouble(obj -> obj.getUtilizationPercetage()).sum() / instanceList.size();

            System.out.println("\n\t EC2 utilization % = " + percentageUsage);

            if (percentageUsage >= autoScalePolicyObject.getCPUThreshold()) {
                /**
                 * If the EC2 crosses the threshold value, Then inform the load
                 * balancer to scale up
                 */
                elasticLoadBalancer.scaleResourceAccordingToPolicy(autoScalePolicyObject);
            }

        },
                3, 0x5, TimeUnit.SECONDS);
    }

    public void stopWatching() {
        /**
         *
         */
        this.scheduleAtFixedRate.cancel(true);
    }

    public String getCloudWatchID() {
        /**
         * This will return the ID of the object.
         */
        return this.cloudWatchID;
    }

}
