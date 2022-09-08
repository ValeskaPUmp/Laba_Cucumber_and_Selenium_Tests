package org.example;

public class Quick_Inv_Sqrt {
    public static float quickInvSqrt(float x){
        int xtobits=Float.floatToIntBits(x);
        xtobits=0x5f3759df-(xtobits>>1);
        float result=Float.intBitsToFloat(xtobits);
        return result*(1.5f-((x*0.5f)*result*result));

    }
}
