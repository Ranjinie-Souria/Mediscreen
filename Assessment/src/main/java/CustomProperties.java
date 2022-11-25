



import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomProperties {

    private String apiPatient;
    private String apiNote;

	public String getApiPatient() {
		return apiPatient;
	}
	public void setApiPatient(String apiPatient) {
		this.apiPatient = apiPatient;
	}
	public String getApiNote() {
		return apiNote;
	}
	public void setApiNote(String apiNote) {
		this.apiNote = apiNote;
	}

    

}