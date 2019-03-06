package works.weave.socks.shipping.loadtest;

import com.google.common.collect.ImmutableList;
import com.neotys.neoload.model.repository.*;
import com.neotys.testing.framework.BaseNeoLoadDesign;
import com.neotys.testing.framework.BaseNeoLoadUserPath;

import java.util.List;

import static java.util.Collections.emptyList;


public class ShippingUserPath extends BaseNeoLoadUserPath {
    public ShippingUserPath(BaseNeoLoadDesign design) {
        super(design);
    }

    @Override
    public UserPath createVirtualUser(BaseNeoLoadDesign baseNeoLoadDesign) {
        final Server server = baseNeoLoadDesign.getServerByName("Carts_Server");
        final ConstantVariable constantpath= (ConstantVariable) baseNeoLoadDesign.getVariableByName("shippingPath");
        final String jsonPayload="{\n" +
                "    \"id\":\"42\",\n" +
                "    \"name\":\"ArthurDent\"\n" +
                " }";
        final List<Header> headerList= ImmutableList.of(
                header("Cache-Control","no-cache"),
                header("Content-Type","application/json"),
                header("json","true")
        );
        final PostRequest postRequest = postTextBuilderWithHeaders(server,headerList, variabilize(constantpath),jsonPayload,emptyList(),emptyList()).build();

        final Delay delay = thinkTime(250);
        final ImmutableContainerForMulti actionsContainer = actionsContainerBuilder()
                .addChilds(container("Shipping", postRequest, delay))
                .build();

        return userPathBuilder("ShippingUserPath")
                .actionsContainer(actionsContainer)
                .build();
    }
}
