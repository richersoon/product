package com.company.service;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.ConfigurableMapper;

import javax.inject.Named;
import java.time.LocalDateTime;

/**
 * Created by richersoon on 7/13/16.
 */
@Named
public class MappingConfigurer extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDateTime.class));
    }

}
