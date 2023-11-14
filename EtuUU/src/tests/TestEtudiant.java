package tests;

public class TestEtudiant {
	private Etudiant etudiant1;
	private Etudiant etudiant2;
	
	@BeforeEach
    void setUp() throws Exception {
		etudiant1=new Etudiant("ki","lo","ol");
		etudiant2=new GestionFormation();
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testajoutformation() {
        gesform2.creerFormation("ki", "lo", "ju");
        assertTrue(gesform2.SetContien("ki", "lo", "ju"));
    }

}
