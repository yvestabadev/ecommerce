package br.com.yvestaba.myecommerce.outbound.jpa;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AttributeOverrides({
	@AttributeOverride(name = "state", column = @Column(name="delivery_state")),
	@AttributeOverride(name = "city", column = @Column(name="delivery_city")),
	@AttributeOverride(name = "zipcode", column = @Column(name="delivery_zipcode")),
	@AttributeOverride(name = "number", column = @Column(name="delivery_number")),
	@AttributeOverride(name = "additionalInfo", column = @Column(name="delivery_additional_info"))
})
public class EmbeddedAdress {
	
	private String state;
	private String city;
	private String zipcode;
	private String number;
	private String additionalInfo;

}
