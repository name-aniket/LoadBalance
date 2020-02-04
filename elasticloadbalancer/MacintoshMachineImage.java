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
public class MacintoshMachineImage extends MachineImage {

    /*
     * Initilize all the values.
     * primary storage value is in GBs.
     * secondary storage value is in TBs.
     */
    public MacintoshMachineImage() {
        this.number_of_vCPU = 16;
        this.primary_storage_GB = 10;
        this.secondary_storage_TB = 4;
        this.MachineImageName = "Mac";
    }

    /*
     *
     */
    @Override
    public int getvCPU() {
        return this.number_of_vCPU;
    }

    /*
     *
     */
    @Override
    public int getPrimaryStorage() {
        return this.primary_storage_GB;
    }

    /*
     *
     */
    @Override
    public int getSecndaryStorage() {
        return this.secondary_storage_TB;
    }

    /*
     *
     */
    @Override
    public String getMachineImageName() {
        return this.MachineImageName;
    }

    @Override
    public void setvCPU(int number_of_vCPU) {
        this.number_of_vCPU = number_of_vCPU;
    }

    @Override
    public void setPrimaryStorage(int primary_storage_GB) {
        this.primary_storage_GB = primary_storage_GB;
    }

    @Override
    public void setSecndaryStorage(int secondary_storage_TB) {
        this.secondary_storage_TB = secondary_storage_TB;
    }

    @Override
    public void setMachineImageName(String MachineImageName) {
        this.MachineImageName = MachineImageName;
    }
}
