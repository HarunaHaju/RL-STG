package cn.lwx.rlstg.algorithm.QLearning;

import cn.lwx.rlstg.GlobalManager;

import java.util.HashMap;

/**
 * Package: cn.lwx.rlstg.algorithm.QLearning
 * Comments:
 * Author: lwx
 * Create Date: 2018/1/17
 * Modified Date: 2018/1/17
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class QValue {
    private HashMap<Integer,Double> values;//action-value

    public QValue(HashMap<Integer, Double> values) {
        this.values = values;
    }

    public QValue(){
        values = new HashMap<>();
        values.put(GlobalManager.ACTION_MOVE_UP , 0.0);
        values.put(GlobalManager.ACTION_MOVE_DOWN , 0.0);
        values.put(GlobalManager.ACTION_MOVE_LEFT , 0.0);
        values.put(GlobalManager.ACTION_MOVE_RIGHT , 0.0);
    }

    public int getAction(){
        if(values.get(GlobalManager.ACTION_MOVE_UP).equals(values.get(GlobalManager.ACTION_MOVE_DOWN))
                && values.get(GlobalManager.ACTION_MOVE_UP).equals(values.get(GlobalManager.ACTION_MOVE_RIGHT))
                &&values.get(GlobalManager.ACTION_MOVE_UP).equals(values.get(GlobalManager.ACTION_MOVE_LEFT))){
            return (int)(Math.random()*values.size());
        }
        int action = GlobalManager.ACTION_MOVE_UP;
        for (int i = 1;i<values.size();i++){
            if(values.get(i) > values.get(action)){
                action = i;
            }
        }
        return action;
    }

    public double getMaxValue(){
        int action = GlobalManager.ACTION_MOVE_UP;
        for (int i = 1;i<values.size();i++){
            if(values.get(i)>values.get(action)){
                action = i;
            }
        }
        return values.get(action);
    }

    public double getValue(int action){
        return values.get(action);
    }

    public void setValue(int action, double value){
        this.values.put(action,value);
    }
    public HashMap<Integer, Double> getValues() {
        return values;
    }

    public void setValues(HashMap<Integer, Double> values) {
        this.values = values;
    }
}
