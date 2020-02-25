import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LeagueTester {
	private League sut = new League("testing", "owner");

	@Test
	void addTeamToLeague() {
		assertTrue(sut.addTeam("testAdd1"), "testAdd1 must be successfully added to League");
		assertTrue(sut.addTeam("testAdd2"), "testAdd2 must be successfully added to League");
		assertTrue(sut.addTeam("testAdd3"), "testAdd3 must be successfully added to League");
//		sut.getTeams().forEach((id) -> System.out.println(id));
	}
	
	@Test
	void removeTeamFromLeague()
	{
		sut.addTeam("removeTest");
		assertFalse(sut.removeTeam("teamNotPresent"), "teamNotPresent is not present in League to be removed");
		assertTrue(sut.removeTeam("removeTest"), "removeTest must be removed from the League");
	}
	
	

}
