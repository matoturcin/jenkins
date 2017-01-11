package com.anritsu.oralb;

import com.anritsu.oralb.config.AppContext;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author SK040191
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppContext.class)
public class FirstUnitTest {
    
    @Value("${url}")
    private String appiumIP;

    @Test
    public void testingMethod(){
        assertEquals("hello", "hello");
    }
        
    @Test
    public void testingMethodFailure(){
        assertEquals("hello", "helloxx");
    }
    
    @Test
    public void htcTest(){
        assertEquals("192.168.126.128:4031", appiumIP);
    }
    
//    @Test
//    public void samsungTest(){
//        assertEquals("192.168.126.128:4032", appiumIP);
//    }
//    
//    @Test
//    public void ihpneTest(){
//        assertEquals("192.168.126.128:4033", appiumIP);
//    }
    
}
