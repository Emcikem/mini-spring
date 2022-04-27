package com.lyq.minispring.test.ioc.common;

import com.lyq.minispring.beans.factory.FactoryBean;
import com.lyq.minispring.test.ioc.bean.Car;

public class CarFactoryBean implements FactoryBean<Car> {

    private String brand;

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        car.setBrand(brand);
        return car;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
