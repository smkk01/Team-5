/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca.ejb;

import java.util.Date;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;

@Stateless
public class EJBTimer {

    @Schedule(second = "0", minute = "0/5", hour = "*")
    
    
    public void myTimer() {
        System.out.println("Timer event: " + new Date());
    }

    @Timeout
    public void timeout(Timer timer) {
    System.out.println("do something ... ");
}
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
