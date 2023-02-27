package com.test.ejb;

import jakarta.ejb.Remote;

@Remote
public interface TestSessionBeanRemote {
    
    public int remoteExecute(int a, int b);
}
