package bloodbank.ifaces;

public interface BloodRetrievalLimitManager {
	
	public void setBloodRetrievalLimit(float limit);
	public float getBloodRetrievalLimit();
	public void modifyBloodRetrievalLimit(float limit);
}
