
package services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AreaRepository;
import security.LoginService;
import domain.Actor;
import domain.Area;

@Service
@Transactional
public class AreaService {

	//Managed Repository

	@Autowired
	private AreaRepository	areaRepository;

	//Supporting Service
	@Autowired
	private ActorService	actorService;


	// Simple CRUD methods
	public Area create() {
		Area res;
		res = new Area();
		return res;
	}

	public Collection<Area> findAll() {
		return this.areaRepository.findAll();
	}

	public Area findOne(final int areaId) {
		return this.areaRepository.findOne(areaId);
	}

	public Area save(final Area area) {
		Area res = null;
		Assert.notNull(area);

		final Actor a;
		a = this.actorService.findByUserAccount(LoginService.getPrincipal());

		Area areaDB;
		Assert.isTrue(area.getId() > 0);
		//cogemos el customer de la base de datos
		areaDB = this.areaRepository.findOne(area.getId());
		//Si al guardar detecta que lo he puesto en final mode ,pues le meto la fecha 
		if ((area.getId() != 0 && area.getName().equals(areaDB.getName())) || a.getUserAccount().getAuthorities().contains("ADMIN") || area.getId() == 0)
			res = this.areaRepository.save(area);
		else
			throw new IllegalArgumentException("No se puede editar la plaza");
		return res;
	}

	public void delete(final Area area) {
		Assert.notNull(area);
		Assert.isTrue(area.getBrotherhood() == null);
		this.areaRepository.delete(area);
	}

	//--------------Other Methods-------------------------------

	public Map<String, Double> StatsBrotherhoodPerArea() {
		final Double[] statistics = this.areaRepository.queryC2AVG();
		final Map<String, Double> res = new HashMap<>();

		res.put("COUNT", statistics[0]);
		res.put("AVG", statistics[1]);
		res.put("MIN", statistics[2]);
		res.put("MAX", statistics[3]);
		res.put("STD", statistics[4]);
		return res;
	}
}
