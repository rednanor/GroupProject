package entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

//@NamedQueries( {
//	@NamedQuery(name = "UEModel.findAll", query = "select o from UEModel o"),
//	@NamedQuery(name = "UEModel.findByName", query = "select o from UEModel o where o.modelName=:modelName"),
////	@NamedQuery(name = "UEModel.findById", query = "select o from UEModel o where o.modelID=:modelId"),
//})

@Entity
public class UserEquipment {

	@Id
	private String tac;
	private int ueManufacturerId;
	private int ueModelId;
	private String ueType;
	private String ueOperatingSys;
	private String ueInputMode;
	
	
	public UserEquipment(){
		
	}
	
	public UserEquipment(String tac,int ueManufacturer, 
			int ueModelId, String ueType,String ueOperatingSys,String ueInputMode){
		
		super();
		this.tac=tac;
		this.ueManufacturerId=ueManufacturer;
		this.ueModelId=ueModelId;
		this.ueType=ueType;
		this.ueOperatingSys=ueOperatingSys;
		this.ueInputMode=ueInputMode;
	}

	public String getTac() {
		return tac;
	}

	public void setTac(String tac) {
		this.tac = tac;
	}

	public int getUeManufacturer() {
		return ueManufacturerId;
	}

	public void setUeManufacturer(int ueManufacturer) {
		this.ueManufacturerId = ueManufacturer;
	}

	public int getUeModel() {
		return ueModelId;
	}

	public void setUeModel(String ueModel) {
		this.ueModelId = ueModelId;
	}

	public String getUeTypeID() {
		return ueType;
	}

	public void setUeTypeID(String ueType) {
		this.ueType = ueType;
	}

	public String getUeOperatingSys() {
		return ueOperatingSys;
	}

	public void setUeOperatingSys(String ueOperatingSys) {
		this.ueOperatingSys = ueOperatingSys;
	}

	public String getUeInputMode() {
		return ueInputMode;
	}

	public void setUeInputMode(String ueInputMode) {
		this.ueInputMode = ueInputMode;
	}
	
	
	
	
	
}
