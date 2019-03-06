package works.weave.socks.shipping.loadtest;

import com.neotys.testing.framework.BaseNeoLoadDesign;
import com.neotys.testing.framework.NeoLoadTest;

public class ShippingLoadTest extends NeoLoadTest {
    @Override
    protected BaseNeoLoadDesign design() {
        return new TestingDesign();
    }

    @Override
    protected String projectName() {
        return "Shipping_NeoLoad";
    }

    @Override
    public void createComplexPopulation() {

    }

    @Override
    public void createComplexScenario() {

    }

    @Override
    public void execute() {

        createSimpleConstantLoadScenario("Shipping_Load","ShippingUserPath",600,49,10);
        createSimpleConstantIterationScenario("DynatraceSanityCheck","BasicCheckTesting",15,1,0);
        createSanityCheckScenario();
    }
}
