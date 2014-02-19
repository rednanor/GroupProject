package persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.AccessCapability;
import entity.BaseData;
import entity.CellTable;
import entity.Country;
import entity.Manufacturer;
import entity.MCCMNC;
import entity.UEModel;
import entity.UserEquipment;


public class PersistenceUtil implements Serializable {

	private static final long serialVersionUID = 1L;


	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("testdb"); 	


	public static void persist(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
	}

	public static void remove(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Object mergedEntity = em.merge(entity);
		em.remove(mergedEntity);
		em.getTransaction().commit();
		em.close();
	}

	public static Object merge(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		entity = em.merge(entity);
		em.getTransaction().commit();		
		em.close();
		return entity;
	}

	public static EntityManager createEM() {
		return emf.createEntityManager();
	}

	public static AccessCapability findAccessCapability(String accessCapability){

		EntityManager em = emf.createEntityManager();
		List<AccessCapability> accessCapabilities = (List<AccessCapability>) em.createNamedQuery("AccessCapability.findByAccessCapability").setParameter("accessCapability", accessCapability).getResultList();
		em.close();

		if (accessCapabilities.size() == 0)
			return null;
		else 
			return accessCapabilities.get(0);
	}

	public static Country findCountry(int mcc){

		EntityManager em = emf.createEntityManager();
		List<Country> countries = (List<Country>) em.createNamedQuery("Country.findByMCC").setParameter("mcc", mcc).getResultList();
		em.close();

		if (countries.size() == 0)
			return null;
		else 
			return countries.get(0);
	}


	public static List<UserEquipment> findAllUserEquipment(){
		EntityManager em = emf.createEntityManager();
		List<UserEquipment> subscribers = (List<UserEquipment>) em.createNamedQuery("UserEquipment.findAll").getResultList();
		em.close();

		return subscribers;

	}


	public static UserEquipment findUserEquipmentById(String id){

		EntityManager em = emf.createEntityManager();
		List<UserEquipment> userEquipments = (List<UserEquipment>) em.createNamedQuery("UserEquipment.findById").setParameter("tac", id).getResultList();
		em.close();

		if (userEquipments.size() == 0)
			return null;
		else 
			return userEquipments.get(0);
	}

	public static List<Manufacturer> findAllManufacturers(){
		EntityManager em = emf.createEntityManager();
		List<Manufacturer> manufacturers = (List<Manufacturer>) em.createNamedQuery("Manufacturer.findAll").getResultList();
		em.close();

		return manufacturers;

	}


	public static Manufacturer findManufacturerByName(String manufacturerName){

		EntityManager em = emf.createEntityManager();
		List<Manufacturer> manufacturers = (List<Manufacturer>) em.createNamedQuery("Manufacturer.findByName").setParameter("manufacturerName", manufacturerName).getResultList();
		em.close();

		if (manufacturers.size() == 0)
			return null;
		else 
			return manufacturers.get(0);
	}


	public static List<UEModel> findAllUEModels(){
		EntityManager em = emf.createEntityManager();
		List<UEModel> ueModels = (List<UEModel>) em.createNamedQuery("UEModel.findAll").getResultList();
		em.close();

		return ueModels;
	}	

	public static UEModel findUEModelById(int modelId){

		EntityManager em = emf.createEntityManager();
		List<UEModel> ueModels = (List<UEModel>) em.createNamedQuery("UEModel.findById").setParameter("modelId", modelId).getResultList();
		em.close();

		if (ueModels.size() == 0)
			return null;
		else 
			return ueModels.get(0);
	}

	public static UEModel findUEModelByName(String modelName){

		EntityManager em = emf.createEntityManager();
		List<UEModel> ueModels = (List<UEModel>) em.createNamedQuery("UEModel.findByName").setParameter("modelName", modelName).getResultList();
		em.close();

		if (ueModels.size() == 0)
			return null;
		else 
			return ueModels.get(0);
	}



	public static List<MCCMNC> findAllMCCMNC(){
		EntityManager em = emf.createEntityManager();
		List<MCCMNC> mccmncs = (List<MCCMNC>) em.createNamedQuery("MCCMNC.findAll").getResultList();
		em.close();

		return mccmncs;
	}	

	public static MCCMNC findMCCMNCById(int mccmncID){

		EntityManager em = emf.createEntityManager();
		List<MCCMNC> mccmncs = (List<MCCMNC>) em.createNamedQuery("MCCMNC.findMCCMNCById").setParameter("mccmncID", mccmncID).getResultList();
		em.close();

		if (mccmncs.size() == 0)
			return null;
		else 
			return mccmncs.get(0);
	}

	public static MCCMNC findMCCMNCByName(int mcc, int mnc){

		EntityManager em = emf.createEntityManager();
		List<MCCMNC> mccmncs = (List<MCCMNC>) em.createNamedQuery("MCCMNC.findMCCMNCByName").setParameter("mcc", mcc).setParameter("mnc", mnc).getResultList();
		em.close();

		if (mccmncs.size() == 0)
			return null;
		else 
			return mccmncs.get(0);
	}


}

