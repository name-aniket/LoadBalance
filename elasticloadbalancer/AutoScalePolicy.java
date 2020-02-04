/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticloadbalancer;

/**
 *
 * @author aniket
 */
public class AutoScalePolicy {

    /**
     * Holds the value for minimum number of EC2 instances required.
     */
    private int min_number_EC2;

    /**
     * Holds the value for maximum number of EC2 instances required.
     */
    private int max_number_EC2;

    /**
     *
     */
    private int left_number_EC2;

    /**
     * Holds the threshold value after that the application should scale.
     */
    private double cpu_utilization_threshold_percentage;

    public AutoScalePolicy(int min_number_EC2, int max_number_EC2, double cpu_utilization_threshold_percentage) {
        this.min_number_EC2 = min_number_EC2;
        this.max_number_EC2 = max_number_EC2;
        this.left_number_EC2 = max_number_EC2;
        this.cpu_utilization_threshold_percentage = cpu_utilization_threshold_percentage;
    }

    /**
     *
     * @return
     */
    public int getMinimumEC2() {
        return this.min_number_EC2;
    }

    /**
     *
     * @return
     */
    public int getMaximumEC2() {
        return this.max_number_EC2;
    }

    /**
     *
     * @return
     */
    public double getCPUThreshold() {
        return this.cpu_utilization_threshold_percentage;
    }

    /**
     *
     * @return
     */
    public int getCountLeftResource() {
        return this.left_number_EC2;
    }

    /**
     *
     * @param min_number_EC2
     */
    public void setMinimumEC2(int min_number_EC2) {
        this.min_number_EC2 = min_number_EC2;
    }

    /**
     *
     * @param max_number_EC2
     */
    public void setMaximumEC2(int max_number_EC2) {
        this.max_number_EC2 = max_number_EC2;
    }

    /**
     *
     * @param cpu_utilization_threshold_percentage
     */
    public void setCPUThreshold(double cpu_utilization_threshold_percentage) {
        this.cpu_utilization_threshold_percentage = cpu_utilization_threshold_percentage;
    }

    public void setCountLeftResource(int left_number_EC2) {
        this.left_number_EC2 = left_number_EC2;
    }
}
