package dev.gutierrez.smoketests;

import dev.gutierrez.utils.ConnectionUtil;
import org.junit.jupiter.api.Test;

public class ConnectionTests {

    @Test
    void connection_available(){
        Connection connection = ConnectionUtil.createConnection();
        System.out.println(connection);
    }
}
