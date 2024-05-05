package test;
import enums.ServiceType;
import maps.Map2D;
import models.Place;
import org.junit.jupiter.api.Test;
import utils.ArrayList;
import utils.Rectangle;
import static org.junit.jupiter.api.Assertions.*;

public class Map2DTest {
    @Test
    void testInsertWithinBounds1() {
        Map2D map = new Map2D(new Rectangle(100, 100, 200, 200), 15);
        assertTrue(map.insert(new Place(0b000001, 100, 100)), "Place should be inserted successfully within bounds");
    }

    @Test
    void testInsertWithinBounds2() {
        Map2D map = new Map2D(new Rectangle(150, 150, 300, 300), 20);
        assertTrue(map.insert(new Place(0b000001, 150, 150)), "Place should be inserted successfully within bounds");
    }

    @Test
    void testInsertWithinBounds3() {
        Map2D map = new Map2D(new Rectangle(200, 200, 400, 400), 25);
        assertTrue(map.insert(new Place(0b000001, 200, 200)), "Place should be inserted successfully within bounds");
    }

    @Test
    void testInsertAtBoundary1() {
        Map2D map = new Map2D(new Rectangle(100, 100, 200, 200), 15);
        assertTrue(map.insert(new Place(0b000001, 0, 0)), "Place should be inserted successfully at boundary");
    }

    @Test
    void testInsertAtBoundary2() {
        Map2D map = new Map2D(new Rectangle(150, 150, 300, 300), 20);
        assertTrue(map.insert(new Place(0b000001, 300, 300)), "Place should be inserted successfully at boundary");
    }

    @Test
    void testInsertAtBoundary3() {
        Map2D map = new Map2D(new Rectangle(200, 200, 400, 400), 25);
        assertTrue(map.insert(new Place(0b000001, 400, 400)), "Place should be inserted successfully at boundary");
    }

    @Test
    void testInsertOutsideBounds1() {
        Map2D map = new Map2D(new Rectangle(100, 100, 200, 200), 15);
        assertFalse(map.insert(new Place(0b000001, 201, 201)), "Place should not be inserted outside bounds");
    }

    @Test
    void testInsertOutsideBounds2() {
        Map2D map = new Map2D(new Rectangle(150, 150, 300, 300), 20);
        assertFalse(map.insert(new Place(0b000001, 301, 301)), "Place should not be inserted outside bounds");
    }

    @Test
    void testInsertOutsideBounds3() {
        Map2D map = new Map2D(new Rectangle(200, 200, 400, 400), 25);
        assertFalse(map.insert(new Place(0b000001, 401, 401)), "Place should not be inserted outside bounds");
    }

    @Test
    void testInsertAtCapacity1() {
        Map2D map = new Map2D(new Rectangle(100, 100, 200, 200), 1);
        map.insert(new Place(0b000001, 100, 100));
        assertTrue(map.insert(new Place(0, 150, 150)), "Place should be inserted when at capacity");
    }

    @Test
    void testRemoveExistingPlace1() {
        Map2D map = new Map2D(new Rectangle(100, 100, 200, 200), 15);
        Place place = new Place(0b000001, 100, 100);
        map.insert(place);
        assertTrue(map.removePlace(100, 100), "Place should be removed successfully");
    }

    @Test
    void testRemoveExistingPlace2() {
        Map2D map = new Map2D(new Rectangle(150, 150, 300, 300), 20);
        Place place = new Place(0b000001, 150, 150);
        map.insert(place);
        assertTrue(map.removePlace(150, 150), "Place should be removed successfully");
    }

    @Test
    void testRemoveExistingPlace3() {
        Map2D map = new Map2D(new Rectangle(200, 200, 400, 400), 25);
        Place place = new Place(0b000001, 200, 200);
        map.insert(place);
        assertTrue(map.removePlace(200, 200), "Place should be removed successfully");
    }

    @Test
    void testRemoveNonExistingPlace1() {
        Map2D map = new Map2D(new Rectangle(100, 100, 200, 200), 15);
        assertFalse(map.removePlace(100, 100), "Non-existing place should not be removed");
    }

    @Test
    void testRemoveNonExistingPlace2() {
        Map2D map = new Map2D(new Rectangle(150, 150, 300, 300), 20);
        assertFalse(map.removePlace(150, 150), "Non-existing place should not be removed");
    }

    @Test
    void testRemoveNonExistingPlace3() {
        Map2D map = new Map2D(new Rectangle(200, 200, 400, 400), 25);
        assertFalse(map.removePlace(200, 200), "Non-existing place should not be removed");
    }

    @Test
    void testRemoveAtBoundary1() {
        Map2D map = new Map2D(new Rectangle(100, 100, 200, 200), 15);
        Place place = new Place(0b000001, 0, 0);
        map.insert(place);
        assertTrue(map.removePlace(0, 0), "Place at boundary should be removed successfully");
    }

    @Test
    void testRemoveAtBoundary2() {
        Map2D map = new Map2D(new Rectangle(150, 150, 300, 300), 20);
        Place place = new Place(0b000001, 300, 300);
        map.insert(place);
        assertTrue(map.removePlace(300, 300), "Place at boundary should be removed successfully");
    }

    @Test
    void testRemoveAtBoundary3() {
        Map2D map = new Map2D(new Rectangle(200, 200, 400, 400), 25);
        Place place = new Place(0b000001, 400, 400);
        map.insert(place);
        assertTrue(map.removePlace(400, 400), "Place at boundary should be removed successfully");
    }

    @Test
    void testSearchWithinBounds1() {
        Map2D map = new Map2D(new Rectangle(100, 100, 200, 200), 15);
        map.insert(new Place(0b000001, 100, 100));
        utils.ArrayList<Place> results = map.search(new Rectangle(100, 100, 50, 50), null, null, 10);
        assertEquals(1, results.size(), "Should find 1 place within search bounds");
    }

    @Test
    void testSearchWithinBounds2() {
        Map2D map = new Map2D(new Rectangle(150, 150, 300, 300), 20);
        map.insert(new Place(0b000001, 150, 150));
        utils.ArrayList<Place> results = map.search(new Rectangle(150, 150, 75, 75), null, null, 10);
        assertEquals(1, results.size(), "Should find 1 place within search bounds");
    }

    @Test
    void testSearchWithinBounds3() {
        Map2D map = new Map2D(new Rectangle(200, 200, 400, 400), 25);
        map.insert(new Place(0b000001, 200, 200));
        ArrayList<Place> results = map.search(new Rectangle(200, 200, 100, 100), null, null, 10);
        assertEquals(1, results.size(), "Should find 1 place within search bounds");
    }

    @Test
    void testSearchNoResults1() {
        Map2D map = new Map2D(new Rectangle(100, 100, 200, 200), 15);
        ArrayList<Place> results = map.search(new Rectangle(50, 50, 20, 20), null, null, 10);
        assertEquals(0, results.size(), "Should find no places outside search bounds");
    }

    @Test
    void testSearchNoResults2() {
        Map2D map = new Map2D(new Rectangle(150, 150, 300, 300), 20);
        ArrayList<Place> results = map.search(new Rectangle(250, 250, 30, 30), null, null, 10);
        assertEquals(0, results.size(), "Should find no places outside search bounds");
    }

    @Test
    void testSearchNoResults3() {
        Map2D map = new Map2D(new Rectangle(200, 200, 400, 400), 25);
        ArrayList<Place> results = map.search(new Rectangle(350, 350, 40, 40), null, null, 10);
        assertEquals(0, results.size(), "Should find no places outside search bounds");
    }

    @Test
    void testSearchAtBoundary1() {
        Map2D map = new Map2D(new Rectangle(100, 100, 200, 200), 15);
        map.insert(new Place(0b000001, 0, 0));
        ArrayList<Place> results = map.search(new Rectangle(0, 0, 10, 10), null, null, 10);
        assertEquals(1, results.size(), "Should find 1 place at boundary");
    }

    @Test
    void testSearchAtBoundary2() {
        Map2D map = new Map2D(new Rectangle(150, 150, 300, 300), 20);
        map.insert(new Place(0b000001, 300, 300));
        ArrayList<Place> results = map.search(new Rectangle(300, 300, 10, 10), null, null, 10);
        assertEquals(1, results.size(), "Should find 1 place at boundary");
    }

    @Test
    void testSearchAtBoundary3() {
        Map2D map = new Map2D(new Rectangle(200, 200, 400, 400), 25);
        map.insert(new Place(0b000001, 400, 400));
        ArrayList<Place> results = map.search(new Rectangle(400, 400, 10, 10), null, null, 10);
        assertEquals(1, results.size(), "Should find 1 place at boundary");
    }

    @Test
    void testSearchMultipleResults1() {
        Map2D map = new Map2D(new Rectangle(100, 100, 200, 200), 15);
        map.insert(new Place(0b000001, 50, 50));
        map.insert(new Place(0b000001, 60, 60));
        ArrayList<Place> results = map.search(new Rectangle(55, 55, 30, 30), null, null, 10);
        assertEquals(2, results.size(), "Should find 2 places within search bounds");
    }

    @Test
    void testSearchMultipleResults2() {
        Map2D map = new Map2D(new Rectangle(150, 150, 300, 300), 20);
        map.insert(new Place(0b000001, 150, 150));
        map.insert(new Place(0b000001, 160, 160));
        ArrayList<Place> results = map.search(new Rectangle(155, 155, 20, 20), null, null, 10);
        assertEquals(2, results.size(), "Should find 2 places within search bounds");
    }

    @Test
    void testSearchMultipleResults3() {
        Map2D map = new Map2D(new Rectangle(200, 200, 400, 400), 25);
        map.insert(new Place(0b000001, 200, 200));
        map.insert(new Place(0b000001, 210, 210));
        ArrayList<Place> results = map.search(new Rectangle(205, 205, 25, 25), null, null, 10);
        assertEquals(2, results.size(), "Should find 2 places within search bounds");
    }
}
