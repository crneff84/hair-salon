import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Date;

public class ClientTest {

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
  public void Client_instantiatesCorrectly_true() {
    Client testClient = new Client("George", 1);
    assertTrue(testClient instanceof Client);
  }

  @Test
  public void getName_instantiatesWithName_String() {
    Client testClient = new Client("George", 1);
    assertEquals("George", testClient.getName());
  }

  @Test
  public void getId_instantiatesWithId_true() {
    Client testClient = new Client("George", 1);
    testClient.save();
    assertTrue(testClient.getId() > 0);
  }

  @Test
  public void all_returnsAllInstancesOfClient_true() {
    Client firstClient = new Client("George", 1);
    firstClient.save();
    Client secondClient = new Client ("Tom", 2);
    secondClient.save();
    assertTrue(Client.all().get(0).equals(firstClient));
    assertTrue(Client.all().get(1).equals(secondClient));
  }

  @Test
  public void find_returnsClientWithSameId_secondClient() {
    Client firstClient = new Client("George", 1);
    firstClient.save();
    Client secondClient = new Client ("Tom", 2);
    secondClient.save();
    assertEquals(Client.find(secondClient.getId()), secondClient);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Client firstClient = new Client("George", 1);
    Client secondClient = new Client ("George", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_returnsTrueIfNamesAreTheSame() {
    Client testClient = new Client("George", 1);
    testClient.save();
    assertTrue(Client.all().get(0).equals(testClient));
  }

  @Test
  public void save_assignsIdToObject() {
    Client testClient = new Client("George", 1);
    testClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(testClient.getId(), savedClient.getId());
  }

  @Test
  public void save_savesStylistIdIntoDB_true() {
    Stylist testStylist = new Stylist("Fred", "Curly hair", 1);
    testStylist.save();
    Client testClient = new Client("George", testStylist.getId());
    testClient.save();
    Client savedClient = Client.find(testClient.getId());
    assertEquals(savedClient.getStylistId(), testStylist.getId());
  }
}
