/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticloadbalancer;

import java.util.UUID;

/**
 * @created 31-01-2020
 * @author aniket
 */
public class ElasticComputeInstance {

    private final String EC2ID;
    private final MachineImage machineImage;
    private final int MAX_CAPACITY = 20;
    private int CURRENT_CAPACITY = 0x0;
    private boolean overFlow = false;

    /**
     *
     * @param machineImage
     */
    public ElasticComputeInstance(MachineImage machineImage) {
        this.EC2ID = UUID.randomUUID().toString();
        this.machineImage = machineImage;
    }

    public ElasticComputeInstance(ElasticComputeInstance elasticComputeInstance) {

        this.EC2ID = UUID.randomUUID().toString();

        this.machineImage = elasticComputeInstance.getMachineImage();

    }

    /**
     * New task comes we simply add 1 to current_capacity;
     */
    public void newTask() {
        /**
         *
         */
        this.overFlow = (this.CURRENT_CAPACITY >= this.MAX_CAPACITY);
        this.CURRENT_CAPACITY += (this.overFlow) ? 0 : 1;
    }

    /**
     *
     */
    public void removeTask() {

        this.overFlow = (this.CURRENT_CAPACITY > this.MAX_CAPACITY);

        this.CURRENT_CAPACITY -= (this.CURRENT_CAPACITY > 0) ? 1 : 0;
    }

    @Override
    public String toString() {

        return this.EC2ID + " " + this.CURRENT_CAPACITY;
    }

    public double getUtilizationPercetage() {

        return this.CURRENT_CAPACITY / (double) this.MAX_CAPACITY * 100;
    }

    /**
     *
     * @return
     */
    public MachineImage getMachineImage() {
        return this.machineImage;
    }

}
