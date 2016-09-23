import java.util.Arrays;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteClientsQuery = "DELETE FROM clients *;";
      String deleteStylistsQuery = "DELETE FROM stylists *;";
      con.createQuery(deleteClientsQuery).executeUpdate();
      con.createQuery(deleteStylistsQuery).executeUpdate();
    }
  }

  @Test
  public void stylist_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("Jimmy", "Curly Hair");
    assertTrue(testStylist instanceof Stylist);
  }

  @Test
  public void getName_instantiatesCorrectlyWithName_string() {
    Stylist testStylist = new Stylist("Jimmy", "Curly Hair");
    assertEquals("Jimmy", testStylist.getName());
  }

  @Test
  public void getSpecialty_instantiatesCorrectlyWithSpecialty_string() {
    Stylist testStylist = new Stylist("Jimmy", "Curly Hair");
    assertEquals("Curly Hair", testStylist.getSpecialty());
  }

  @Test
  public void all_returnsAllInstancesOfStylist_true() {
    Stylist firstStylist = new Stylist("Jimmy", "Curly Hair");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Johnny", "Shaving");
    secondStylist.save();
    assertTrue(Stylist.all().get(0).equals(firstStylist));
    assertTrue(Stylist.all().get(1).equals(secondStylist));
  }

  @Test
  public void getId_instantiatesCorrectlyWithAnId_1() {
    Stylist testStylist = new Stylist("Jimmy", "Curly Hair");
    testStylist.save();
    assertTrue(testStylist.getId() > 0);
  }

  @Test
  public void find_returnsStylistWithSameId_secondStylist() {
    Stylist firstStylist = new Stylist("Jimmy", "Curly Hair");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Johnny", "Shaving");
    secondStylist.save();
    assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
  }

  @Test
  public void getClients_initiallyReturnsEmptyList_ArrayList() {
    Stylist testStylist = new Stylist("Jimmy", "Curly Hair");
    assertEquals(0, testStylist.getClients().size());
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame_true() {
    Stylist firstStylist = new Stylist("Jimmy", "Shaving");
    Stylist secondStylist = new Stylist("Jimmy", "Shaving");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Stylist testStylist = new Stylist("Jimmy", "Shaving");
    testStylist.save();
    assertTrue(Stylist.all().get(0).equals(testStylist));
  }

  @Test
  public void save_assignsIdToObject() {
    Stylist testStylist = new Stylist("Jimmy", "Shaving");
    testStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(testStylist.getId(), savedStylist.getId());
  }

  @Test
  public void getClients_returnsAllClientsFromDatabase_List() {
    Stylist testStylist = new Stylist("Jimmy", "Shaving");
    Client firstClient = new Client("George", testStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Georges", testStylist.getId());
    secondClient.save();
    Client[] clients = new Client[] { firstClient, secondClient };
    assertTrue(testStylist.getClients().containsAll(Arrays.asList(clients)));
  }
}
