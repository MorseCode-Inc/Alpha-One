package test.alpha_core.zk;

import inc.morsecode.zk.SerializedEncoder;

import java.util.Arrays;

public class EncoderTest {



    public static void main(String[] args) {

        Arrays.stream(new String[] {
                ("1.2"),
                ("-11.2"),
                ("1.2"),
                ("-73"),
                ("-134.73"),
                ("134.73"),
                ("13473"),
                ("0023948883413473")
        }).forEach(v -> System.out.println(v +" : "+ SerializedEncoder.looksNumeric(v)));

    }

}

