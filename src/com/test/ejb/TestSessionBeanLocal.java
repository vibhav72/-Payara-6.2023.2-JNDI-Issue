package com.test.ejb;

import jakarta.ejb.Local;

@Local
public interface TestSessionBeanLocal {
    
    public int execute(int a, int b);
}
