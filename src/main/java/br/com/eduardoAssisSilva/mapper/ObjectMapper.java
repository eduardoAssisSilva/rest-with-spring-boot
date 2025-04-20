package br.com.eduardoAssisSilva.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {
    private static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <O, D> D parseObject (O origin, Class <D> destination){
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects (List<O> originItems, Class <D> destination){
        List<D> destinationObjects = new ArrayList<>();

        for(Object item : originItems){
            destinationObjects.add(mapper.map(item, destination));
        }
        return destinationObjects;
    }
}
