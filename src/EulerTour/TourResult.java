/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EulerTour;

/**
 *
 * @author MIPC
 * @param <R>
 */
public class TourResult<R> {
    protected R left;
    protected R right;
    protected R out;
    
    public TourResult(){}
    
    public TourResult(R left, R rigth, R out) {
        this.left = left;
        this.right = rigth;
        this.out = out;
    }

    public R getLeft() {
        return left;
    }

    public void setLeft(R left) {
        this.left = left;
    }

    public R getRigth() {
        return right;
    }

    public void setRigth(R rigth) {
        this.right = rigth;
    }

    public R getOut() {
        return out;
    }

    public void setOut(R out) {
        this.out = out;
    }
    
}
