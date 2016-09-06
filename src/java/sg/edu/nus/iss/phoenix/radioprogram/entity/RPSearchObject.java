package sg.edu.nus.iss.phoenix.radioprogram.entity;

public class RPSearchObject {
	  private String name;
	    private String description;
		public RPSearchObject() {
			super();
		}
		public RPSearchObject(String name, String description) {
			super();
			this.name = name;
			this.description = description;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
}
