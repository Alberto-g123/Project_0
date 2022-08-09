package mockbasics;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.List;

public class MockExampleTests {
    @Test
    void mock_1(){
        List<String> mockedList = Mockito.mock(List.class);
        Mockito.when(mockedList.get(99)).thenReturn("Bowser");
        System.out.println(mockedList.get(99));
    }

    @Test
    void register_employee_must_have_fname(){

    }




}
