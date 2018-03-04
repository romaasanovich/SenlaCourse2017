package com.senla.autoservice.dao.abstractdao;

import com.senla.autoservice.api.bean.AEntity;

public class GenericDao<T extends AEntity> extends  AGenericDao<T> {

     public  GenericDao(Class<T> clazz){
         super(clazz);
     }

}
