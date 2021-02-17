package org.example.testing;

import static org.junit.Assert.assertEquals;

import org.example.auction.controller.AuctionController;
import org.junit.jupiter.api.Test;

public class ControllerTests {
	
	@Test
    public void controllerTestExample() {
        AuctionController auctionController = new AuctionController();
        String outcome = auctionController.basicTest();
        assertEquals(outcome, "For Controller Testing Example");
    }
}
