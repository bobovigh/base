package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.sensor.TrainSensorImpl;

public class TrainSensorTest {
    TrainController mockTC;
    TrainUser mockTU;
    TrainSensorImpl trainSensor;

    @Before
    public void before() {
        mockTC = mock(TrainController.class);
        mockTU = mock(TrainUser.class);
        trainSensor = new TrainSensorImpl(mockTC, mockTU);
    }

    @Test
    public void TestLowerLimit() {
        trainSensor.overrideSpeedLimit(0);
        verify(mockTU, times(1)).setAlarmState(false);
    }

    @Test
    public void TestUpperLimit() {
        trainSensor.overrideSpeedLimit(500);
        verify(mockTU, times(1)).setAlarmState(false);
    }

    @Test
    public void TestBelowLimit() {
        trainSensor.overrideSpeedLimit(-1);
        verify(mockTU, times(1)).setAlarmState(true);
    }

    @Test
    public void TestAboveLimit() {
        trainSensor.overrideSpeedLimit(501);
        verify(mockTU, times(1)).setAlarmState(true);
    }
}
