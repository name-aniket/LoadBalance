/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticloadbalancer;

/**
 * @created 31-01-2020
 * @author aniket
 */

public abstract class MachineImage {
    /*
     * Number of virtual CPU can vary from machine to machine.
     * This variable stores the value of number of vCPUs
     */
    protected int number_of_vCPU;
    
    /*
     * Size of RAM can vary from machine to machine.
     * This variable stores the size of RAM
     */
    protected int primary_storage_GB;
    
    /*
     * Size of secondary memory/disk storage can vary from machine to machine.
     * This variable stores the size for disk.
     */
    protected int secondary_storage_TB;
    
    /*
     * Label which identifies which image is being used.
     */
    protected String MachineImageName;
    
    /*
     * Get the number of vCPU used by the image 
     */
    abstract int getvCPU();
    
    /*
     * Get the size of primary storage in GBs.
     */
    abstract int getPrimaryStorage();

    /*
     * Get the size of secondary storage in TBs.
     */
    abstract int getSecndaryStorage();

    /*
     * Get the name for the machine image.
     */
    abstract String getMachineImageName();
    
    
    /*
     * Set the number of vCPU used by the image 
     */
    abstract void setvCPU(int number_of_vCPU);
    
    /*
     * Set the size of primary storage in GBs.
     */
    abstract void setPrimaryStorage(int primary_storage_GB);

    /*
     * Set the size of secondary storage in TBs.
     */
    abstract void setSecndaryStorage(int secondary_storage_TB);

    /*
     * Set the name for the machine image.
     */
    abstract void setMachineImageName(String MachineImageName);
}
