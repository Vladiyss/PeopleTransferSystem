package by.vladiyss.transferSystem.building.statistics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class GeneralBuildingStatisticsTest {

    private GeneralBuildingStatistics generalBuildingStatistics;

    @BeforeEach
    public void before() {
        generalBuildingStatistics = ObjectsForStatistics.getGeneralBuildingStatisticsObject();
    }

    @Test
    void addElementToBetweenFloorsTransfersStatisticsList() {
        BetweenFloorsTransfersStatistics betweenFloorsTransfersStatisticsElement =
                new BetweenFloorsTransfersStatistics(2, 1, 4);
        int sizeBefore_betweenFloorsTransfersStatisticsList = generalBuildingStatistics
                .getBetweenFloorsTransfersStatisticsList()
                .size();
        generalBuildingStatistics.addElementToBetweenFloorsTransfersStatisticsList(betweenFloorsTransfersStatisticsElement);
        int sizeAfter_betweenFloorsTransfersStatisticsList = generalBuildingStatistics
                .getBetweenFloorsTransfersStatisticsList()
                .size();

        assertThat(sizeAfter_betweenFloorsTransfersStatisticsList - sizeBefore_betweenFloorsTransfersStatisticsList,
                equalTo(1));
    }

    @Test
    void getElevatorsStatistics() {
        assertThat(generalBuildingStatistics.getElevatorsStatistics().size(), is(not(0)));
    }

    @Test
    void getFloorsStatistics() {
        assertThat(generalBuildingStatistics.getFloorsStatistics().size(), is(not(0)));
    }

    @Test
    void getBetweenFloorsTransfersStatisticsList() {
        assertThat(generalBuildingStatistics.getBetweenFloorsTransfersStatisticsList(), notNullValue());
    }
}