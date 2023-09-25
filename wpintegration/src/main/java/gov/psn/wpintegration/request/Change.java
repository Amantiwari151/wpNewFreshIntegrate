package gov.psn.wpintegration.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Change {
	
	 public Value value;
	    public String field;
}
