package com.powernode.spring6.bean;

import java.util.Arrays;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class QianDaYe {
    private String[] aihaos;

    private Women[] women;

    @Override
    public String toString() {
        return "QianDaYe{" +
                "aihaos=" + Arrays.toString(aihaos) +
                ", women=" + Arrays.toString(women) +
                '}';
    }

    public void setWomen(Women[] women) {
        this.women = women;
    }

    public void setAihaos(String[] aihaos) {
        this.aihaos = aihaos;
    }
}
