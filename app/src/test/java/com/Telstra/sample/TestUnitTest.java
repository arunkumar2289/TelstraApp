package com.Telstra.sample;

import com.Telstra.sample.model.ImageData;
import com.Telstra.sample.model.LiveData;
import com.Telstra.sample.util.Util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestUnitTest {
    @Test
    public void validation_isCorrect() throws Exception {
        ImageData data = new ImageData();
        List<ImageData> datas = new ArrayList<>();
        datas.add(data);
        List<ImageData> value = Util.removeNull(datas);
        assertEquals(1, value.size());
    }
    
     @Test
    public void checkIsNull() throws Exception{
        ImageData imageData = new ImageData();
        assertEquals(null,imageData.description);
    }
}
