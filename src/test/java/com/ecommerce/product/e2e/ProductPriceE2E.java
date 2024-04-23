package com.ecommerce.product.e2e;
import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class ProductPriceE2E {

    @Karate.Test
    public Karate request1() {
        return perform("RequestAt10AMOnDay14");
    }

    @Karate.Test
    public Karate request2() {
        return perform("RequestAt4PMOnDay14");
    }

    @Karate.Test
    public Karate request3() {
        return perform("RequestAt9PMOnDay14");
    }

    @Karate.Test
    public Karate request4() {
        return perform("RequestAt10AMOnDay15");
    }

    @Karate.Test
    public Karate request5() {
        return perform("RequestAt9PMOnDay16");
    }

    private Karate perform(String scenario) {
        return Karate.run("classpath:product-price.feature")
                .scenarioName(scenario);
    }
}

