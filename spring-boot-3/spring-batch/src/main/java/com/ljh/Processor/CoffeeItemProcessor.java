package com.ljh.Processor;

import com.ljh.domain.Coffee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * CoffeeItemProcessor
 *
 * @author ljh
 * @since 2023/7/25 14:43
 */
public class CoffeeItemProcessor implements ItemProcessor<Coffee, Coffee> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoffeeItemProcessor.class);

    @Override
    public Coffee process(final Coffee coffee) throws Exception {
        String brand = coffee.getBrand().toUpperCase();
        String origin = coffee.getOrigin().toUpperCase();
        String characteristics = coffee.getCharacteristics().toUpperCase();

        Coffee transformedCoffee = new Coffee(brand, origin, characteristics);
        LOGGER.info("Converting ( {} ) into ( {} )", coffee, transformedCoffee);

        return transformedCoffee;
    }
}
