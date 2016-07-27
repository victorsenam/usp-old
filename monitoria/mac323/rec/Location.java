public class Location{
	private final float latitude, longitude;
    public final int id; //mudei aqui

    
    //adicionei o campo id
	public Location (float x, float y, int identifier){
        this.id = identifier;
		this.latitude  = x;
		this.longitude = y;
	}

	public float latitude(){
		return this.latitude;
	}

	public float longitude(){
		return this.longitude;
	}

    public int id(){
        return this.id;
    }

	public double distanceTo(Location x){

		// calculate the diference between degrees than transform to kilometers
		double x1 = ((x.latitude()  - this.latitude)  * (double)Math.PI * (double)6371) / (double)180;
		double y1 = ((x.longitude() - this.longitude) * (double)Math.PI * (double)6371) / (double)180;

		double dist = Math.sqrt((x1 * x1) + (y1 * y1));

		return dist;
	}

	public boolean equals(Location x){
		if ((x.latitude() - this.latitude <= 0.001) && (x.longitude() - this.longitude <= 0.001)) return true;
		else return false;
	}

	public String toString(){
		return String.valueOf(latitude) + " " + String.valueOf(longitude) + " " + String.valueOf(id);
	}

	public static void main (String[] args){}
}
