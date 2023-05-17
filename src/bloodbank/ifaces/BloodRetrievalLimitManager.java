package bloodbank.ifaces;

public interface BloodRetrievalLimitManager {
	
	public void setBloodRetrievalLimit(float limit);
	public void modifyBloodRetrievalLimit(float limit);
	
	public float getBloodRetrievalLimit();
}
