package com.hyun.mockto.mockito_demo.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SomeBusinessImplStubTest {
    @Test
    void findTheGreatestFromAllData_basicScenario() {
        DataServiceStub dataServiceStub = new DataServiceStub();
        SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceStub);
        int result = businessImpl.findTheGreatestFromAllData();
        assertEquals(25, result);
    }
}

class DataServiceStub implements DataService {
    @Override
    public int[] getAllData() {
        return new int[] {25, 15, 5};
    }
}