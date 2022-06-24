package ru.liga;

//import org.junit.Test;

import java.util.LinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LineParserTest {
    @Test
    public void getTest() {
        LineParser lineParser = new LineParser();
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("nominal;data;curs;cdx");
        linkedList.add("1;01.02.2022;” 86,5032”;Евро");
        linkedList.add("1;01.02.2022;” 86,5032”;Евро");
        assertTrue(lineParser.getAverageRate(linkedList, 2).equals(86.5032));
        assertTrue(lineParser.getAverageRate(linkedList, 1).equals(86.5032));
        assertTrue(lineParser.getAverageRate(linkedList, 3).equals(86.5032));
    }
    @Test
    public void getTest2(){
        LineParser lineParser = new LineParser();
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("nominal;data;curs;cdx");
        linkedList.add("1;01.02.2022;” 86,5032”;Евро");
        linkedList.add("1;01.02.2022;” 86,5032”;Евро");
        assertTrue(lineParser.getRatesFromList(linkedList,2).size() == 2);
        assertTrue(lineParser.getRatesFromList(linkedList,1).size() == 1);
        assertTrue(lineParser.getRatesFromList(linkedList,3).size() == 2);


    }
}
