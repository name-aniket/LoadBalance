/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticloadbalancer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author aniket
 */
public class DriverClass {

    public static void main(String[] args) {
        try {
            /**
             * 
             */
            System.out.println("\t Load Balancer instantiated ");
            ElasticLoadBalancer elasticLoadBalancer = new ElasticLoadBalancer();

            
            
            /**
             * User will create some EC2 instances We need Machine Image object.
             */
            System.out.println("\n\t EC2 instance created");
            for (int i = 0; i < 3; i++) {
                elasticLoadBalancer.addEC2(new ElasticComputeInstance(new UbuntuMachineImage()));
            }

            
            
            /**
             * Create a thread for simulating incoming traffic.
             */
            System.out.println("\n\t Incoming traffic is simulated using worker thread ");
            ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
            exec.scheduleAtFixedRate(() -> {
                elasticLoadBalancer.assignRequestToEC2();
            },
                    2, 0b101, TimeUnit.SECONDS);

            
            /**
             * 
             */
            System.out.println("\n\t Auto Scale policy is gereated for scaling ");
            AutoScalePolicy autoScalePolicy = new AutoScalePolicy(2, 4, 10.0);

            
            /**
             * 
             */
            System.out.println("\n\t Cloud Watch is initialized ");
            CloudWatch cloudWatch = new CloudWatch(elasticLoadBalancer, autoScalePolicy);

            System.out.println(" \n\t A worker thread is started which monitors health of EC2 ");
            cloudWatch.startWatching();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
