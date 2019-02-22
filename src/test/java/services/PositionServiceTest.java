
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Actor;
import domain.Position;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class PositionServiceTest extends AbstractTest {

	//Service ------------------------------
	@Autowired
	private PositionService	positionService;

	@Autowired
	private ActorService	actorService;


	//Test
	@Test
	public void testCreate() {
		System.out.println("========== testCreate() ==========");
		final int actorId = this.getEntityId("admin");
		final Actor actor = this.actorService.findOne(actorId);
		try {
			final Position position = this.positionService.create();

			position.setName("Nombre1");
			position.setLanguage("Espa�ol");

			Assert.notNull(position);

			System.out.println("�Exito!");

		} catch (final Exception e) {
			System.out.println("�Fallo," + e.getMessage() + "!");
		}

	}
	@Test
	public void testFindOne() {
		System.out.println("========== testFindOne() ==========");
		final int positionId = this.getEntityId("position1");

		try {
			final Position position = this.positionService.findOne(positionId);
			Assert.notNull(position);

			System.out.println("�Exito!");

		} catch (final Exception e) {
			System.out.println("�Fallo," + e.getMessage() + "!");
		}

	}
	@Test
	public void testSave() {
		System.out.println("========== testSave() ==========");
		Position position, saved;
		final Collection<Position> positions;
		try {
			position = this.positionService.create();
			position.setName("Antonia");
			position.setLanguage("Ingl�s");
			saved = this.positionService.save(position);

			positions = this.positionService.findAll();
			Assert.isTrue(positions.contains(saved));
			System.out.println("�Exito!");

		} catch (final Exception e) {
			System.out.println("�Fallo," + e.getMessage() + "!");
		}
		this.unauthenticate();
	}

	@Test
	public void testFindAll() {
		System.out.println("========== testFindAll() ==========");
		final int positionId = this.getEntityId("Position1");
		try {
			final Position position = this.positionService.findOne(positionId);
			final Collection<Position> positions = this.positionService.findAll();
			Assert.isTrue(positions.contains(position));
			System.out.println("�Exito!");

		} catch (final Exception e) {
			System.out.println("�Fallo," + e.getMessage() + "!");
		}

	}

	@Test
	public void testDelete() {
		System.out.println("========== testDelete() ==========");
		this.authenticate("referee1");
		final int positionId = this.getEntityId("Position1");

		try {
			final Position position = this.positionService.findOne(positionId);
			this.positionService.delete(position);
			final Collection<Position> positions = this.positionService.findAll();
			Assert.notNull(positions);
			Assert.isTrue(!positions.contains(position));

			System.out.println("�Exito!");

		} catch (final Exception e) {
			System.out.println("�Fallo," + e.getMessage() + "!");
		}
		this.unauthenticate();
	}

}
